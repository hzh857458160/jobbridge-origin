package com.jobBridge.controller;

import com.jobBridge.Dao.IEnterpriseDao;
import com.jobBridge.Dao.IRecruitInfoDao;
import com.jobBridge.Dao.IRecruitInfoTagDao;
import com.jobBridge.Dao.ITagDao;
import com.jobBridge.model.Enterprise;
import com.jobBridge.model.RecruitInfo;
import com.jobBridge.model.RecruitInfoTag;
import com.jobBridge.model.Tag;
import com.jobBridge.service.EnterpriseService;
import com.jobBridge.service.RecruitInfoService;
import com.jobBridge.service.RecruitInfoTagService;
import com.jobBridge.service.TagService;
import com.jobBridge.util.ParseStringUtil;
import com.jobBridge.util.RecruitInfoUtil;
import com.jobBridge.util.SendInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SYunk on 2017/7/26.
 */
@Controller
public class RecruitInfoController {

    private IRecruitInfoDao recruitInfoService = new RecruitInfoService();
    private IEnterpriseDao enterpriseService = new EnterpriseService();
    private ITagDao tagService = new TagService();
    private IRecruitInfoTagDao  recruitInfoTagService = new RecruitInfoTagService();

    /**
     * 这里可以无登录访问
     * 请求职海页面
     * */
    @RequestMapping(value = "/recruitinfo",method = RequestMethod.GET)
    public String recruitInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "/public/jobs.html";
    }

    /**
     * 这里可以无登录访问
     * 职海之showinfo
     * */
    @RequestMapping(value = "/recruit/showinfo",method = RequestMethod.GET)
    public void showRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
//        定义数据结构
        JSONObject json = new JSONObject();
        JSONArray dataJson = new JSONArray();
        int recruitInfoNum = recruitInfoService.findNumOfRecruitInfo();
        if(recruitInfoNum == 0){
            json.put("numberofpage",0);
            json.put("data",dataJson);
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("offset",0);
        if(recruitInfoNum < 10){
            map.put("limit",recruitInfoNum);
        }else{
            map.put("limit",10);
        }
        List<RecruitInfo> recruitInfoList = recruitInfoService.findRecruitInfoOrderByTime(map);
        if(recruitInfoList == null || recruitInfoList.isEmpty() || (recruitInfoList.size() == 1 && recruitInfoList.get(0) == null)){
            json.put("numberofpage",0);
            json.put("data",dataJson);
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
//        对这10个（可能少于10个）招聘信息进行处理
        for(RecruitInfo recruitInfo:recruitInfoList){
            Enterprise enterprise = enterpriseService.findEnterpriseById(recruitInfo.getEnterpriseId());
            if(enterprise == null){
                System.out.println("内部错误");
                json.put("numberofpage",0);
                json.put("data",new JSONArray());
                SendInfo.render(json.toString(),"text/json",response);
                return;
            }
            JSONObject recruitInfoJson = new JSONObject();
            recruitInfoJson.put("jobtitle",recruitInfo.getJobName());
            recruitInfoJson.put("jobid",recruitInfo.getRecruitInfoId());
            recruitInfoJson.put("companyname",enterprise.getName());
            recruitInfoJson.put("location",recruitInfo.getLocation());
            recruitInfoJson.put("time",recruitInfo.getDateTime());
            recruitInfoJson.put("companydesc",enterprise.getEnterpriseIntroduction());
            recruitInfoJson.put("iconaddress",enterprise.getIconAddress());
            dataJson.put(recruitInfoJson);
        }
        int pageNum = 0;
        if(recruitInfoNum % 10 == 0){
            pageNum = recruitInfoNum/10;
        }
        else{
            pageNum = recruitInfoNum/10 + 1;
        }
        json.put("numberofpage",pageNum);
        json.put("data",dataJson);
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 这里可以无登录访问
     * 职海之showinfobycondition
     * */
    @RequestMapping(value = "/recruit/showinfobycondition",method = RequestMethod.POST)
    public void showRecruitInfoByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String content = request.getParameter("content");
//        定义收到的数据结构
        JSONObject receiveJson = new JSONObject(content);
        JSONObject receiveOptionJson = null;
        int pageNum = receiveJson.getInt("numberofpage");
        receiveOptionJson = receiveJson.getJSONObject("optionlist");
        String locations = receiveOptionJson.get("citylist").toString();
        String industries = receiveOptionJson.get("industrylist").toString();
        String[] locationList = ParseStringUtil.parseString(locations);
        String[] tags = ParseStringUtil.parseString(industries);
        if(pageNum < 10 || pageNum % 10 != 0){
            System.out.println("前台项数错误");
            return;
        }
//        定义发送的数据结构
        JSONObject sendJson = new JSONObject();
        JSONArray sendDataJson = new JSONArray();
        List<RecruitInfo> recruitInfoList = new ArrayList<>();
        int recruitInfoNum = 0;
        if(tags.length == 1 && tags[0].equals("不限")){
            if(locationList.length == 1 && locationList[0].equals("不限")){
//                情况一：城市和行业都不限
                recruitInfoNum = recruitInfoService.findNumOfRecruitInfo();
                if(recruitInfoNum != 0){
                    Map<String,Object> map = new HashMap<>();
                    map.put("offset",pageNum - 10);
                    int limit = recruitInfoNum - pageNum + 10;
                    if(limit < 10){
                        map.put("limit",limit);
                    }else{
                        map.put("limit",10);
                    }
                    recruitInfoList = recruitInfoService.findRecruitInfoOrderByTime(map);
                }
            }else{
//                情况二：行业不限，限制城市
                List<RecruitInfo> recruitInfoCityCondition = new ArrayList<>();
                for(int i = 0;i<locationList.length;i++){
                    List<RecruitInfo> tempRecruitInfoList = recruitInfoService.findRecruitInfoByLocation(locationList[i]);
                    if(tempRecruitInfoList == null || tempRecruitInfoList.isEmpty() ||
                            (tempRecruitInfoList.size() == 1 && tempRecruitInfoList.get(0) == null)){
//                        这里不做处理
                    }else{
                        for(RecruitInfo recruitInfo:tempRecruitInfoList){
                            if(recruitInfo != null){
                                recruitInfoCityCondition.add(recruitInfo);
                            }
                        }
                    }
                }
                recruitInfoNum = recruitInfoCityCondition.size();
                if(recruitInfoNum != 0){
                    int limit = recruitInfoNum - pageNum + 10;
                    if(limit < 10){
                        for(int i = pageNum - 10;i<pageNum - 10 + limit;i++){
                            recruitInfoList.add(recruitInfoCityCondition.get(i));
                        }
                    }else{
                        for(int i = pageNum - 10;i<pageNum;i++){
                            recruitInfoList.add(recruitInfoCityCondition.get(i));
                        }
                    }
                }
            }
        }else{
            if(locationList.length == 1 && locationList[0].equals("不限")){
//                情况三：城市不限，限制行业
                boolean flag = true;
                List<RecruitInfo> recruitInfoCondition = new ArrayList<>();
                for(int i = 0;i< tags.length;i++){
                    Tag tag = tagService.findTagByName(tags[i]);
                    if(tag == null){
                        continue;
                    }
                    List<RecruitInfoTag> recruitInfoTagList = recruitInfoTagService.findRecruitInfoTagByTagId(tag.getTagId());
                    if(!(recruitInfoTagList == null || recruitInfoTagList.isEmpty() ||
                            (recruitInfoTagList.size() == 1 && recruitInfoTagList.get(0) == null))){
//                        还没有则加新的，有则在里面找还满足新条件的
                        if(recruitInfoCondition.isEmpty()){
                            for(RecruitInfoTag recruitInfoTag:recruitInfoTagList){
                                if(recruitInfoTag != null){
                                    RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(recruitInfoTag.getRecruitInfoId());
                                    if(recruitInfo != null){
                                        recruitInfoCondition.add(recruitInfo);
                                    }
                                }
                            }
//                            找还满足新条件的
                        }else{
                            List<RecruitInfo> temp = new ArrayList<>();
                            for(RecruitInfoTag recruitInfoTag:recruitInfoTagList){
                                if(recruitInfoTag != null){
                                    for(RecruitInfo recruitInfo:recruitInfoCondition){
                                        if(recruitInfo.getRecruitInfoId().equals(recruitInfoTag.getRecruitInfoId())){
                                            temp.add(recruitInfo);
                                        }
                                    }
                                }
                            }
                            if(temp.isEmpty()){
//                                这时没有满足交集条件的招聘信息了
                                flag = false;
                            }
                            recruitInfoCondition.clear();
                            recruitInfoCondition.addAll(temp);
                        }
                    }
                }
                if(flag){
                    recruitInfoNum = recruitInfoCondition.size();
                }else{
                    recruitInfoNum = 0;
                    recruitInfoCondition.clear();
                }
                if(recruitInfoNum != 0){
                    int limit = recruitInfoNum - pageNum + 10;
                    if(limit < 10){
                        for(int i = pageNum - 10;i<pageNum - 10 + limit;i++){
                            recruitInfoList.add(recruitInfoCondition.get(i));
                        }
                    }else{
                        for(int i = pageNum - 10;i<pageNum;i++){
                            recruitInfoList.add(recruitInfoCondition.get(i));
                        }
                    }
                }
            }else {
//                情况四：城市和行业都限制
//                先限制城市
                List<RecruitInfo> recruitInfoCityCondition = new ArrayList<>();
                for(int i = 0;i<locationList.length;i++){
                    List<RecruitInfo> tempRecruitInfoList = recruitInfoService.findRecruitInfoByLocation(locationList[i]);
                    if(!(tempRecruitInfoList == null || tempRecruitInfoList.isEmpty() ||
                            (tempRecruitInfoList.size() == 1 && tempRecruitInfoList.get(0) == null))){
                        for(RecruitInfo recruitInfo:tempRecruitInfoList){
                            if(recruitInfo != null){
                                recruitInfoCityCondition.add(recruitInfo);
                            }
                        }
                    }
                }
//                再限制行业
                boolean flag = true;
                boolean anyTag = false;
                for(int i = 0;i< tags.length;i++){
                    Tag tag = tagService.findTagByName(tags[i]);
                    if(tag == null){
                        continue;
                    }else{
                        anyTag = true;
                    }
                    List<RecruitInfoTag> recruitInfoTagList = recruitInfoTagService.findRecruitInfoTagByTagId(tag.getTagId());
                    if(!(recruitInfoTagList == null || recruitInfoTagList.isEmpty() ||
                            (recruitInfoTagList.size() == 1 && recruitInfoTagList.get(0) == null))){
//                        还没有则加新的，有则在里面找还满足新条件的
                        if(recruitInfoCityCondition.isEmpty() && i != 0){
                            for(RecruitInfoTag recruitInfoTag:recruitInfoTagList){
                                if(recruitInfoTag != null){
                                    RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(recruitInfoTag.getRecruitInfoId());
                                    if(recruitInfo != null){
                                        recruitInfoCityCondition.add(recruitInfo);
                                    }
                                }
                            }
//                            找还满足新条件的
                        }else{
                            List<RecruitInfo> temp = new ArrayList<>();
                            for(RecruitInfoTag recruitInfoTag:recruitInfoTagList){
                                if(recruitInfoTag != null){
                                    for(RecruitInfo recruitInfo:recruitInfoCityCondition){
                                        if(recruitInfo.getRecruitInfoId().equals(recruitInfoTag.getRecruitInfoId())){
                                            temp.add(recruitInfo);
                                        }
                                    }
                                }
                            }
                            if(temp.isEmpty()){
//                                这时没有满足交集条件的招聘信息了
                                flag = false;
                            }
                            recruitInfoCityCondition.clear();
                            recruitInfoCityCondition.addAll(temp);
                        }
                    }
                }
                if(flag && anyTag){
                    recruitInfoNum = recruitInfoCityCondition.size();
                }else{
                    recruitInfoNum = 0;
                    recruitInfoCityCondition.clear();
                }
                if(recruitInfoNum != 0){
                    int limit = recruitInfoNum - pageNum + 10;
                    if(limit < 10){
                        for(int i = pageNum - 10;i<pageNum - 10 + limit;i++){
                            recruitInfoList.add(recruitInfoCityCondition.get(i));
                        }
                    }else{
                        for(int i = pageNum - 10;i<pageNum;i++){
                            recruitInfoList.add(recruitInfoCityCondition.get(i));
                        }
                    }
                }
            }
        }
//        处理最终要发送的数据
        int page = 0;
        if(recruitInfoNum != 0 && recruitInfoNum % 10 == 0){
            page = recruitInfoNum / 10;
        }else if(recruitInfoNum % 10 != 0){
            page = recruitInfoNum / 10 + 1;
        }
        sendJson.put("numberofpage",page);
        if(!recruitInfoList.isEmpty()){
            List<RecruitInfo> resultList = RecruitInfoUtil.orderByTime(recruitInfoList);
            for(RecruitInfo recruitInfo:resultList){
                Enterprise enterprise = enterpriseService.findEnterpriseById(recruitInfo.getEnterpriseId());
                if(enterprise == null){
                    System.out.println("内部错误:找不到公司");
                    return;
                }
                JSONObject sendRecruitInfoJson = new JSONObject();
                sendRecruitInfoJson.put("jobtitle",recruitInfo.getJobName());
                sendRecruitInfoJson.put("jobid",recruitInfo.getRecruitInfoId());
                sendRecruitInfoJson.put("location",recruitInfo.getLocation());
                sendRecruitInfoJson.put("time",recruitInfo.getDateTime());
                sendRecruitInfoJson.put("companydesc",enterprise.getEnterpriseIntroduction());
                sendRecruitInfoJson.put("iconaddress",enterprise.getIconAddress());
                sendRecruitInfoJson.put("companyname",enterprise.getName());
                sendDataJson.put(sendRecruitInfoJson);
            }
        }
        sendJson.put("data",sendDataJson);
        SendInfo.render(sendJson.toString(),"text/json",response);
    }
}
