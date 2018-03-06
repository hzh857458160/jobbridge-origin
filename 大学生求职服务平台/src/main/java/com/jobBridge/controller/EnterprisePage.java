package com.jobBridge.controller;

import com.jobBridge.Dao.*;
import com.jobBridge.model.*;
import com.jobBridge.service.*;
import com.jobBridge.util.ParseStringUtil;
import com.jobBridge.util.SendInfo;
import jdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SYunk on 2017/7/24.
 */
@Controller
public class EnterprisePage {

    private IRecruitInfoDao recruitInfoService = new RecruitInfoService();
    private IDeliverDao deliverService = new DeliverService();
    private ITagDao tagService = new TagService();
    private IRecruitInfoTagDao recruitInfoTagService = new RecruitInfoTagService();
    private IResumeDao resumeService = new ResumeService();
    private IStudentDao studentService = new StudentService();
    private IResumeDetailDao resumeDetailService = new ResumeDetailService();


    /**
    * 公司企业退出操作
    * */
    @RequestMapping(value = "/enterprise/exit",method = RequestMethod.GET)
    public String enterpriseExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
        }
        request.getSession().removeAttribute("loginUser");
        return "/public/index.html";
    }

    /**
     * 请求公司新发布招聘信息页面
     * */
    @RequestMapping(value = "/enterprise/newrecruitinfopage",method = RequestMethod.GET)
    public String enterpriseNewRecruitInfoPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
        }
        return "/public/compubjob.html";
    }

    /**
     * 请求公司信息页面
     * */
    @RequestMapping(value = "/enterprise/info",method = RequestMethod.GET)
    public String enterpriseInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
        }
        return "/public/cominfo.html";
    }

    /**
     * 请求公司已发布的招聘信息页面
     * */
    @RequestMapping(value = "/enterprise/recruitinfo",method = RequestMethod.GET)
    public String enterpriseRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
        }
        return "/public/compubedjob.html";
    }

    /**
     * 请求公司查看投递信息页面
     * */
    @RequestMapping(value = "/enterprise/deliverinfo",method = RequestMethod.GET)
    public String enterpriseDeliverInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
        }
        return "/public/commessage.html";
    }

    /**
    * 请求公司已发布的招聘信息列表
    * */
    @RequestMapping(value = "/enterprise/publishedjobs",method = RequestMethod.GET)
    public void enterpriseForRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        //定义数据结构
        JSONObject json = new JSONObject();
        JSONArray dataJson = new JSONArray();
//        根据公司号查询所有招聘信息
        List<RecruitInfo> recruitInfoList = recruitInfoService.findRecruitInfoByEnterpriseId(enterprise.getEnterpriseId());
//        如果没有招聘信息，则返回空数据
        if(recruitInfoList == null || recruitInfoList.isEmpty() ||
                (recruitInfoList.size() == 1 && recruitInfoList.get(0) == null)){
            json.put("data",dataJson);
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
//        对于每个未删除的招聘信息，查询招聘信息号、职位名称和发布时间作为json对象添加进入json数组中
        for(RecruitInfo recruitInfo:recruitInfoList){
            if(recruitInfo.getHaveDelete()){
                continue;
            }
            JSONObject recruitInfoJson = new JSONObject();
            recruitInfoJson.put("publishedid",recruitInfo.getRecruitInfoId());
            recruitInfoJson.put("pubtime",recruitInfo.getDateTime().toString());
            recruitInfoJson.put("jobtitle",recruitInfo.getJobName());
            dataJson.put(recruitInfoJson);
        }
        json.put("data",dataJson);
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 公司删除已发布的招聘信息操作
     * */
    @RequestMapping(value = "/enterprise/recruit/delete",method = RequestMethod.GET)
    public void enterpriseDeleteRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        String recruitInfoId = request.getParameter("id");
        RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(Long.parseLong(recruitInfoId));
        String result;
        if(!enterprise.getEnterpriseId().equals(recruitInfo.getEnterpriseId())){
            result = "{\"ok\":\"false\",\"reason\":\"非法删除其他公司招聘信息\"}";
        }else{
            recruitInfoService.updateHaveDeleteById(Long.parseLong(recruitInfoId));
            result = "{\"ok\":\"true\"}";
        }
        SendInfo.render(result,"text/json",response);
    }

    /**
     * 公司删除学生投递信息操作
     * */
    @RequestMapping(value = "/enterprise/delivery/delete",method = RequestMethod.GET)
    public void enterpriseDeleteDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        String deliveryId = request.getParameter("id");
        Deliver deliver = deliverService.findDeliverById(Long.parseLong(deliveryId));
        String result;
        if(!enterprise.getEnterpriseId().equals(deliver.getEnterpriseId())){
            result = "{\"ok\":\"false\",\"reason\":\"非法删除其他公司收到的信息\"}";
        }else{
            deliverService.updateHaveDeleteByDeliverId(Long.parseLong(deliveryId));
            result = "{\"ok\":\"true\"}";
        }
        SendInfo.render(result,"text/json",response);
    }

    /**
     * 公司发布或修改招聘信息操作
     * */
    @RequestMapping(value = "/enterprise/recruitinfo/save",method = RequestMethod.POST)
    public void enterpriseNewRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        System.out.println("已经进入公司发布或修改招聘信息");
        Enterprise enterprise = (Enterprise)loginUser;
        String recruitInfoId = request.getParameter("id");
        String jobName = request.getParameter("jobName");
        String location = request.getParameter("location");
        String lowSalary = request.getParameter("lowSalary");
        String highSalary = request.getParameter("highSalary");
        String deadline = request.getParameter("deadline");
        String jobDescribe = request.getParameter("jobDescribe");
        String jobRequire = request.getParameter("jobRequire");
        String tagName = request.getParameter("industry");
        System.out.println("id:" + recruitInfoId);
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());
        String[] tags = ParseStringUtil.parseString(tagName);
//        1.如果是新发布招聘信息
        if(recruitInfoId == null || recruitInfoId.equals("")){
            System.out.println("发布新招聘信息");
//            1.1 添加招聘信息
            RecruitInfo recruitInfo = new RecruitInfo(Long.parseLong("0"),enterprise.getEnterpriseId(),jobName,jobDescribe,jobRequire,location,
                    Integer.parseInt(lowSalary),Integer.parseInt(highSalary),dateTime,deadline,false);
            recruitInfoService.addRecruitInfo(recruitInfo);
            RecruitInfo justRecruitInfo = recruitInfoService.findLastRecruitInfoByEnterpriseId(enterprise.getEnterpriseId());
            if (justRecruitInfo == null) {
                System.out.println("内部错误");
                return;
            }
//            1.2 处理标签问题
            for(int i = 0;i<tags.length;i++){
//                1.2.1 先看数据库里面有没有这个标签，没有则加进去
                Tag existTag = tagService.findTagByName(tags[i]);
                if(existTag == null){
                    Tag tag = new Tag(0,tags[i]);
                    tagService.addTag(tag);
                }
//                1.2.2 找到这个标签
                Tag justTag = tagService.findTagByName(tags[i]);
                if(justTag == null){
                    return;
                }
//                1.2.3 再将tag添加到招聘信息所属标签表中
                RecruitInfoTag recruitInfoTag = new RecruitInfoTag(justRecruitInfo.getRecruitInfoId(),justTag.getTagId());
                recruitInfoTagService.addRecruitInfoTag(recruitInfoTag);
            }
            String result = "{\"ok\":\"true\"}";
            SendInfo.render(result,"text/json",response);
//            2.如果是修改招聘信息
        }else{
            String result = null;
            RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(Long.parseLong(recruitInfoId));
            if(recruitInfo == null){
                result = "{\"ok\":\"false\",\"reason\":\"你要修改的招聘信息不存在\"}";
                SendInfo.render(result,"text/json",response);
                return;
            }else if(recruitInfo.getHaveDelete()){
                result = "{\"ok\":\"false\",\"reason\":\"你要修改的招聘信息已经被删除\"}";
                SendInfo.render(result,"text/json",response);
                return;
            }
//            2.1 修改招聘信息内容
            Map<String,Object> map = new HashMap<>();
            map.put("recruitInfoId",Long.parseLong(recruitInfoId));
            map.put("jobName",jobName);
            map.put("jobDescribe",jobDescribe);
            map.put("jobRequire",jobRequire);
            map.put("location",location);
            map.put("lowSalary",Integer.parseInt(lowSalary));
            map.put("highSalary",Integer.parseInt(highSalary));
            map.put("deadline",deadline);
            recruitInfoService.updateRecruitInfoById(map);
//            2.2 删除原来的所有tag所属
            recruitInfoTagService.deleteRecruitInfoTagByRecruitInfoId(Long.parseLong(recruitInfoId));
//            2.3 对每个tag,添加新的tag所属
            for(int i = 0;i<tags.length;i++){
//                2.2.1 找到这个标签
                Tag existTag = tagService.findTagByName(tags[i]);
                if(existTag == null){
                    Tag tag = new Tag(0,tags[i]);
                    tagService.addTag(tag);
                }
                Tag justTag = tagService.findTagByName(tags[i]);
                if(justTag == null){
                    System.out.println("内部错误");
                    return;
                }
//                2.2.2 再将tag添加到招聘信息所属标签表中
                RecruitInfoTag recruitInfoTag = new RecruitInfoTag(Long.parseLong(recruitInfoId),justTag.getTagId());
                recruitInfoTagService.addRecruitInfoTag(recruitInfoTag);
            }
            result = "{\"ok\":\"true\"}";
            SendInfo.render(result,"text/json",response);
        }
    }

    /**
     * 请求公司信息数据
     * */
    @RequestMapping(value = "/enterprise/showinfo",method = RequestMethod.GET)
    public void enterpriseShowInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        JSONObject json = new JSONObject();
        json.put("name",enterprise.getName());
        json.put("userName",enterprise.getUserName());
        json.put("mailbox",enterprise.getMailbox());
        json.put("phoneNum",enterprise.getPhoneNum());
        json.put("enterpriseIntroduction",enterprise.getEnterpriseIntroduction());
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     *请求公司收到的投递信息
     * */
    @RequestMapping(value = "/enterprise/showdeliveryinfo", method = RequestMethod.GET)
    public void enterpriseForDeliverInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        System.out.println("已经进入公司投递信息显示操作");
        //定义数据结构
        JSONObject json = new JSONObject();
        JSONArray dataJsonArray = new JSONArray();
//        根据公司号查询所有招聘信息
        List<Deliver> deliverList = deliverService
                .findDeliverByEnterpriseId(enterprise.getEnterpriseId());
        //空值判定
        if (deliverList == null || deliverList.isEmpty() ||
                (deliverList.size() == 1 && deliverList.get(0) == null)){
            json.put("data",dataJsonArray);
            //SendInfo.render(json.toString(),"text/json",response);
        }else{
            for (Deliver tempDeliver:deliverList){
                if (tempDeliver.getHaveDelete()) {
                    continue;
                }else{
                    JSONObject dataJson = new JSONObject();
                    //deliveryid
                    dataJson.put("deliveryid",tempDeliver.getDeliverId());
                    //deliverytime
                    dataJson.put("deliverytime",tempDeliver.getDateTime());
                    //jobtitle
                    RecruitInfo recruitInfo = recruitInfoService
                            .findRecruitInfoById(tempDeliver.getRecruitInfoId());
                    dataJson.put("jobtitle",recruitInfo.getJobName());
                    //username
                    Resume resume = resumeService
                            .findResumeByResumeId(tempDeliver.getResumeId());
                    Student student = studentService
                            .findStudentByStudentId(resume.getStudentId());
                    dataJson.put("username",student.getUserName());
                    //status
                    if(tempDeliver.getHaveRead()){
                        dataJson.put("status","已读");
                    }else {
                        dataJson.put("status","未读");
                    }

                    dataJsonArray.put(dataJson);

                }
            }
            json.put("data",dataJsonArray);
        }
        System.out.println(json);
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 请求公司已发布的招聘信息详细
     * */
    @RequestMapping(value = "/enterprise/showrecruitinfo",method = RequestMethod.GET)
    public void enterpriseShowRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        Enterprise enterprise = (Enterprise)loginUser;
        String recruitInfoId = request.getParameter("id");
        if(recruitInfoId == null){
            return;
        }
//        定义数据结构
        JSONObject json = new JSONObject();
//        根据招聘信息号查询招聘信息
        RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(Long.parseLong(recruitInfoId));
        if(recruitInfo == null){
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
//        将招聘信息号、职位名称和发布时间添加进入json中
        json.put("jobName",recruitInfo.getJobName());
        json.put("location",recruitInfo.getLocation());
        json.put("lowSalary",recruitInfo.getLowSalary());
        json.put("highSalary",recruitInfo.getHighSalary());
        json.put("deadline",recruitInfo.getDeadline());
        json.put("jobDescribe",recruitInfo.getJobDescribe());
        json.put("jobRequire",recruitInfo.getJobRequire());
//        查询招聘信息对应的标签
        List<RecruitInfoTag> recruitInfoTagList = recruitInfoTagService.findRecruitInfoTagByRecruitInfoId(Long.parseLong(recruitInfoId));
        if(recruitInfoTagList == null || recruitInfoTagList.isEmpty() || (recruitInfoTagList.size() == 1 && recruitInfoTagList.get(0) == null)){
            json.put("industry","[]");
        }else{
            String[] industry = new String[recruitInfoTagList.size()];
//            对于每一个标签号，查询对应的标签名
            for(int i = 0;i<recruitInfoTagList.size();i++){
                Tag tag = tagService.findTagByTagId(recruitInfoTagList.get(i).getTagId());
                if(tag != null){
                    industry[i] = tag.getName();
                }
            }
            json.put("industry",industry);
        }
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 公司请求投递信息对应得简历信息
     */
    @RequestMapping(value = "/enterprise/showreview",method = RequestMethod.POST)
    public void enterpriseShowResume(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Enterprise)){
            response.sendRedirect("/");
            return;
        }
        System.out.println("公司请求投递信息对应得简历信息");
        Enterprise enterprise = (Enterprise)loginUser;
        String deliverIdStr = request.getParameter("deliverid");
        if (deliverIdStr == null){
            System.out.println("deliverIdStr is null");
        }
        Long deliverId = Long.parseLong(deliverIdStr);
        Deliver deliver = deliverService.findDeliverById(deliverId);
        System.out.println(deliver);
        Long resumeId = deliver.getResumeId();

        //定义大的变量
        JSONObject json = new JSONObject();
        JSONArray edusJsonArray = new JSONArray();
        JSONArray worksJsonArray = new JSONArray();
        JSONObject leaderJson = new JSONObject();
        JSONObject skillJson = new JSONObject();

        //装载简历头
        System.out.println("resumeId = "+ resumeId);
        ResumeHead resumeHead = resumeDetailService.findResumeHeadByResumeId(resumeId);
        if (resumeHead == null){
            System.out.println("resumeHead = null");
        }
        json.put("name",resumeHead.getName());
        json.put("address",resumeHead.getAddress());
        json.put("phone",resumeHead.getPhoneNum());
        json.put("mail",resumeHead.getMailbox());

        //装载教育背景
        List<ResumeEducation> resumeEducationList = resumeDetailService
                .findResumeEducationByResumeId(resumeId);
        if (resumeEducationList == null || resumeEducationList.isEmpty()
                ||(resumeEducationList.size()==1 && resumeEducationList.get(0)==null)){
            System.out.println("resumeEducationList is null");
            return;
        }
        for (ResumeEducation resumeEducation : resumeEducationList){
            JSONObject tempJson = new JSONObject();
            tempJson.put("school",resumeEducation.getUniversityName());
            tempJson.put("city",resumeEducation.getCity());
            tempJson.put("province",resumeEducation.getProvince());
            tempJson.put("college",resumeEducation.getMajor());
            System.out.println("end time = "+ParseStringUtil.parseDateToString(resumeEducation.getGraduateDate()));
            tempJson.put("end_time",ParseStringUtil.parseDateToString(resumeEducation.getGraduateDate()));
            tempJson.put("grade",resumeEducation.getGrade());
            tempJson.put("honors",resumeEducation.getHonors());
            System.out.println("RelatedCourse = "+resumeEducation.getRelatedCourse());
            tempJson.put("related_course",resumeEducation.getRelatedCourse());
            edusJsonArray.put(tempJson);
        }
        System.out.println(edusJsonArray);

        //装载工作经历
        List<ResumeWorks> resumeWorksList = resumeDetailService
                .findResumeWorksByResumeId(resumeId);
        if (resumeWorksList == null || resumeWorksList.isEmpty()
                ||(resumeWorksList.size()==1 && resumeWorksList.get(0)==null)){
            System.out.println("resumeWorksList is null");
            return;
        }
        for (ResumeWorks resumeWorks : resumeWorksList){
            JSONObject tempJson = new JSONObject();
            tempJson.put("company",resumeWorks.getCompany());
            tempJson.put("city",resumeWorks.getCity());
            tempJson.put("province",resumeWorks.getProvince());
            tempJson.put("position",resumeWorks.getJobName());
            tempJson.put("project",resumeWorks.getProjectName());
            tempJson.put("start_time",ParseStringUtil.parseDateToString(resumeWorks.getStartDate()));
            tempJson.put("end_time",ParseStringUtil.parseDateToString(resumeWorks.getEndDate()));
            tempJson.put("sentence_1",resumeWorks.getSentence1());
            tempJson.put("sentence_2",resumeWorks.getSentence2());
            tempJson.put("sentence_3",resumeWorks.getSentence3());
            tempJson.put("sentence_4",resumeWorks.getSentence4());
            worksJsonArray.put(tempJson);
        }
        System.out.println(worksJsonArray);

        //装载领导经验
        //Organization 部分
        JSONArray organizationJsonArray = new JSONArray();
        List<ResumeOrganization> resumeOrganizationList = resumeDetailService
                .findResumeOrganizationByResumeId(resumeId);
        if (resumeOrganizationList == null || resumeOrganizationList.isEmpty()
                ||(resumeOrganizationList.size()==1 && resumeOrganizationList.get(0)==null)){
            System.out.println("resumeOrganizationList is null");
            return;
        }
        for (ResumeOrganization resumeOrganization : resumeOrganizationList){
            if (resumeOrganization != null){
                JSONObject tempJson = new JSONObject();
                tempJson.put("name",resumeOrganization.getName());
                tempJson.put("position",resumeOrganization.getJobName());
                tempJson.put("start_time",ParseStringUtil.parseDateToString(resumeOrganization.getStartDate()));
                tempJson.put("end_time",ParseStringUtil.parseDateToString(resumeOrganization.getEndDate()));
                tempJson.put("sentence_1",resumeOrganization.getSentence1());
                tempJson.put("sentence_2",resumeOrganization.getSentence2());
                tempJson.put("sentence_3",resumeOrganization.getSentence3());
                organizationJsonArray.put(tempJson);
            }

        }
        System.out.println(organizationJsonArray);

        //Club 部分
        JSONArray clubJsonArray = new JSONArray();
        List<ResumeClub> resumeClubList = resumeDetailService
                .findResumeClubByResumeId(resumeId);
        if (resumeClubList == null || resumeClubList.isEmpty()
                ||(resumeClubList.size()==1 && resumeClubList.get(0)==null)){
            System.out.println("resumeClubList is null");
            return;
        }
        for (ResumeClub resumeClub : resumeClubList){
            JSONObject tempJson = new JSONObject();
            tempJson.put("name",resumeClub.getName());
            tempJson.put("position",resumeClub.getJobName());
            tempJson.put("start_time",ParseStringUtil.parseDateToString(resumeClub.getStartDate()));
            tempJson.put("end_time",ParseStringUtil.parseDateToString(resumeClub.getEndDate()));
            tempJson.put("sentence_1",resumeClub.getSentence1());
            tempJson.put("sentence_2",resumeClub.getSentence2());
            clubJsonArray.put(tempJson);
        }
        System.out.println(clubJsonArray);

        leaderJson.put("organization",organizationJsonArray);
        leaderJson.put("club",clubJsonArray);

        //装载技能兴趣
        ResumeSkill resumeSkill = resumeDetailService.findResumeSkillByResumeId(resumeId);
        if (resumeSkill == null){
            System.out.println("resumeSkill is null");
            return;
        }

        skillJson.put("language",resumeSkill.getLanguage());
        skillJson.put("computer",resumeSkill.getComputer());
        skillJson.put("hobby",resumeSkill.getHobby());

        //最终装载到一个json
        json.put("edus",edusJsonArray);
        json.put("works",worksJsonArray);
        json.put("leader",leaderJson);
        json.put("skill",skillJson);

        System.out.println(json);

        SendInfo.render(json.toString(),"text/json",response);


    }

}
