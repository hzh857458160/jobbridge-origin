package com.jobBridge.controller;

import com.jobBridge.Dao.*;
import com.jobBridge.model.*;
import com.jobBridge.service.*;
import com.jobBridge.util.MailUtil;
import com.jobBridge.util.ParseStringUtil;
import com.jobBridge.util.SendInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SYunk on 2017/7/22.
 */
@Controller
public class StudentPage {

    private IStudentDetailDao studentDetailService = new StudentDetailService();
    private IResumeDao resumeService = new ResumeService();
    private IDeliverDao deliverService = new DeliverService();
    private IEnterpriseDao enterpriseService = new EnterpriseService();
    private IRecruitInfoDao recruitInfoService = new RecruitInfoService();
    private ICollectEnterpriseDao collectEnterpriseService =new CollectEnterpriseService();
    private ICollectTagDao collectTagService = new CollectTagService();
    private ITagDao tagService = new TagService();
    private IResumeDetailDao resumeDetailService = new ResumeDetailService();

    /**
    * 学生退出操作
    * */
    @RequestMapping(value = "/studentcenter/exit",method = RequestMethod.GET)
    public void studentExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        System.out.println("进入退出操作");
        request.getSession().removeAttribute("loginUser");
    }

    /**
     * 请求学生简历页面
     * */
    @RequestMapping(value = "/studentcenter/resume",method = RequestMethod.GET)
    public String studentResume(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        return "/public/resume.html";
    }

    /**
     * 学生请求具体招聘信息页面
     * */
    @RequestMapping(value = "/studentcenter/recruitinfo",method = RequestMethod.GET)
    public String studentRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        return "/public/jobinfo.html";
    }

    /**
     * 学生请求验证页面
     * */
    @RequestMapping(value = "/studentcenter/requestvalidate",method = RequestMethod.GET)
    public void studentRequestValidate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        response.sendRedirect("/public/stuvalid.html");
    }

    /**
    * 请求学生信息页面
    * */
    @RequestMapping(value = "/studentcenter/info",method = RequestMethod.GET)
    public String studentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        return "/public/stuinfo.html";
    }

    /**
    * 请求学生投递信息页面
    * */
    @RequestMapping(value = "/studentcenter/deliveryinfo",method = RequestMethod.GET)
    public String studentDeliverInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        return "/public/studelivery.html";
    }

    /**
     * 请求学生收藏信息页面
     * */
    @RequestMapping(value = "/studentcenter/collectioninfo",method = RequestMethod.GET)
    public String studentCollection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        return "/public/stustar.html";
    }

    /**
    * 学生详细信息保存操作
    * */
    @RequestMapping(value = "/studentcenter/saveinfo",method = RequestMethod.POST)
    public void saveStudentInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        Student student = (Student)loginUser;
//        解析json
        String content = request.getParameter("content");
        System.out.println(content);
        JSONObject json = new JSONObject(content);
        JSONObject infoJson = json.getJSONObject("info");
        JSONObject studyJson = json.getJSONObject("study");
        JSONObject jobintentionJson = json.getJSONObject("jobintention");
        String phoneNum = infoJson.get("phone").toString();
        String universityName = studyJson.get("school").toString();
        String major = studyJson.get("major").toString();
        String grade = studyJson.get("grade").toString();
        String intentionCity = jobintentionJson.get("city").toString();
        String intentionIndustry = jobintentionJson.get("industry").toString();
        String intentionFunction = jobintentionJson.get("func").toString();

        StudentDetail studentDetail = studentDetailService.findStudentDetailByStudentId(student.getStudentId());
//        1.根据学生id号查询学生详细信息，如果为空，则创建一个新的学生详细信息并添入数据库；否则取出原来的，保留简历号和验证标志再更新
        if(studentDetail == null){
            StudentDetail newStudentDetail = new StudentDetail(student.getStudentId(),null,phoneNum,
                    universityName,major,grade,intentionCity,intentionIndustry,intentionFunction,false);
            studentDetailService.addStudentDetail(newStudentDetail);
        }else{
            studentDetail.setPhoneNum(phoneNum);
            studentDetail.setUniversityName(universityName);
            studentDetail.setMajor(major);
            studentDetail.setIntentionCity(intentionCity);
            studentDetail.setIntentionIndustry(intentionIndustry);
            studentDetail.setIntentionFunction(intentionFunction);
            studentDetailService.deleteStudentDetailByStudentId(student.getStudentId());
            studentDetailService.addStudentDetail(studentDetail);
        }
//        删除学生所有的收藏
        collectTagService.deleteCollectTagByStudentId(student.getStudentId());
//        2.添加学生收藏的意向大类
        String[] tags = ParseStringUtil.parseString(intentionIndustry);
        for(int i = 0;i<tags.length;i++){
//                2.1 先看数据库里面有没有这个标签，没有则加进去
            Tag existTag = tagService.findTagByName(tags[i]);
            if(existTag == null){
                Tag tag = new Tag(0,tags[i]);
                tagService.addTag(tag);
            }
//                2.2 找到这个标签
            Tag justTag = tagService.findTagByName(tags[i]);
            if(justTag == null){
                return;
            }
//                2.3 再将tag添加学生收藏标签中
            CollectTag collectTag = new CollectTag(student.getStudentId(),justTag.getTagId());
            collectTagService.addCollectTag(collectTag);
        }
        String result = "{\"ok\":\"true\"}";
        SendInfo.render(result,"text/json",response);
    }

    /**
    * 请求学生投递过的招聘信息
    * */
    @RequestMapping(value = "/studentcenter/showdelivery",method = RequestMethod.GET)
    public void showStudentDelivery(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
//        先建立好要传回前端的数据结构
        Student student = (Student)loginUser;
        JSONObject json = new JSONObject();
        JSONArray deliveryDataJson = new JSONArray();
//        查询学生投递信息:
//        1.先找到学生对应的简历号
        Long resumeId = resumeService.findResumeIdByStudentId(student.getStudentId());
//        如果学生没写简历，则返回空数据
        if(resumeId == null){
            json.put("deliverydata",deliveryDataJson);
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
//        2.根据简历号查询该学生的所有投递记录的公司号和招聘信息号
        List<Deliver> deliverList = deliverService.findDeliverByResumeId(resumeId);
//        如果学生没投递简历到任意公司，则返回空数据
        if(deliverList == null || deliverList.isEmpty()){
            json.put("deliverydata",deliveryDataJson);
            SendInfo.render(json.toString(),"text/json",response);
            return;
        }
//        3.对每个投递记录，查找公司名（即查找公司），职位名称、描述（即招聘信息），然后传回前端
        for(int i = 0;i<deliverList.size();i++){
//            3.1 查找公司名
            Enterprise enterprise = enterpriseService.findEnterpriseById(deliverList.get(i).getEnterpriseId());
//            3.2 查找职位
            RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(deliverList.get(i).getRecruitInfoId());
            if(enterprise == null || recruitInfo == null){
                System.out.println("内部查询出错：没找到投递记录所对应的公司或招聘信息");
                json.put("deliverydata",deliveryDataJson);
                SendInfo.render(json.toString(),"text/json",response);
                return;
            }
//            3.3 添加到json数组中
            JSONObject deliveryJson = new JSONObject();
            deliveryJson.put("time",deliverList.get(i).getDateTime());
            deliveryJson.put("comname",enterprise.getName());
            deliveryJson.put("jobtitle",recruitInfo.getJobName());
            deliveryJson.put("jobdesc",recruitInfo.getJobDescribe());
            deliveryJson.put("jobhref","../public/jobinfo.html?id=" + recruitInfo.getRecruitInfoId());
            deliveryJson.put("havedel",recruitInfo.getHaveDelete());
            deliveryDataJson.put(deliveryJson);
        }
//        4.将json数组添加到json对象里面,然后发回前端
        json.put("deliverydata",deliveryDataJson);
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 学生收藏公司操作
     */
    @RequestMapping(value = "/studentcenter/collectenterprise",method = RequestMethod.POST)
    public void collectEnterprise(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        Student student = (Student)loginUser;
        String enterpriseId = request.getParameter("companyid");
        CollectEnterprise collectEnterprise = new CollectEnterprise(Long.parseLong(enterpriseId),student.getStudentId());
        collectEnterpriseService.addCollectEnterprise(collectEnterprise);
        String result = "{\"ok\":\"true\"}";
        SendInfo.render(result,"text/json",response);
    }

    /**
     * 请求收藏的公司以及收藏的标签
     */
    @RequestMapping(value = "/studentcenter/showcollection",method = RequestMethod.GET)
    public void showStudentCollection(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        //定义
        Student student = (Student)loginUser;
        JSONObject json = new JSONObject();
        JSONArray enterpriseJsonArray = new JSONArray();
        JSONArray tagJsonArray =new JSONArray();

        //通过学生ID获取到收藏公司的List
        List<CollectEnterprise> collectEnterpriseList = collectEnterpriseService
                .findCollectEnterpriseByStudentId(student.getStudentId());
        //通过学生ID获取到收藏标签的List
        List<CollectTag> collectTagList = collectTagService
                .findCollectTagByStudentId(student.getStudentId());
        //空值判定
        if(collectEnterpriseList == null || collectEnterpriseList.isEmpty() ||
                (collectEnterpriseList.size() == 1 && collectEnterpriseList.get(0) == null)){
            json.put("company",enterpriseJsonArray);
            //SendInfo.render(json.toString(),"text/json",response);
            //return;

        }else{
            for (CollectEnterprise tempCollectEnterprise : collectEnterpriseList) {
                JSONObject enterpriseJson = new JSONObject();
                Enterprise enterprise = enterpriseService
                        .findEnterpriseById(tempCollectEnterprise.getEnterpriseId());
                enterpriseJson.put("starcomid",enterprise.getEnterpriseId());
                enterpriseJson.put("comname",enterprise.getName());
                enterpriseJson.put("email",enterprise.getMailbox());
                enterpriseJson.put("phonenum",enterprise.getPhoneNum());
                enterpriseJson.put("comdesc",enterprise.getEnterpriseIntroduction());
                enterpriseJson.put("iconaddress",enterprise.getIconAddress());
                enterpriseJsonArray.put(enterpriseJson);
            }
            json.put("company",enterpriseJsonArray);

        }

        if(collectTagList == null || collectTagList.isEmpty()
                || (collectTagList.size() == 1 && collectTagList.get(0) == null)){
            json.put("job",tagJsonArray);
        }else{
            for (CollectTag tempCollectTag : collectTagList){
                JSONObject tagJson = new JSONObject();
                Tag tag = tagService.findTagByTagId(tempCollectTag.getTagId());
                tagJson.put("jobtitle",tag.getName());
                tagJsonArray.put(tagJson);
            }
            json.put("job",tagJsonArray);
        }
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 请求学生用户详细信息
     * 若第一次进入页面则都为空
     */
    @RequestMapping(value = "/studentcenter/showinfo",method = RequestMethod.GET)
    public void showUsedStudentInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        //        先建立好要传回前端的数据结构
        Student student = (Student)loginUser;
        JSONObject json = new JSONObject();
        JSONObject infoJson = new JSONObject();
        JSONObject studyJson = new JSONObject();
        JSONObject jobIntentionJson = new JSONObject();

        infoJson.put("email",student.getMailbox());
        //通过studentId获取student中的email
        //通过studentID获取studentDetail中的其他信息
        StudentDetail studentDetail = studentDetailService.findStudentDetailByStudentId(student.getStudentId());
        if (studentDetail == null){
            json.put("info",infoJson);
        }else{
            infoJson.put("phone",studentDetail.getPhoneNum());
            studyJson.put("school",studentDetail.getUniversityName());
            studyJson.put("major",studentDetail.getMajor());
            studyJson.put("grade",studentDetail.getGrade());
            String[] intentionCitys = ParseStringUtil.parseString(studentDetail.getIntentionCity());
            String[] intentionIndustry = ParseStringUtil.parseString(studentDetail.getIntentionIndustry());
            String[] intentionFunc = ParseStringUtil.parseString(studentDetail.getIntentionFunction());
            jobIntentionJson.put("city",intentionCitys);
            jobIntentionJson.put("industry",intentionIndustry);
            jobIntentionJson.put("func",intentionFunc);

            json.put("info",infoJson);
            json.put("study",studyJson);
            json.put("jobintention",jobIntentionJson);
        }
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 学生查看具体的招聘信息
     */
    @RequestMapping(value = "/studentcenter/showrecruitinfo",method = RequestMethod.GET)
    public void studentShowRecruitInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Student)) {
            response.sendRedirect("/");
        }
        Student student = (Student) loginUser;
        Long recruitInfoId;
        if (request.getParameter("id") == null){
            System.out.println("getParameter error");
        }else{
            recruitInfoId = Long.parseLong(request.getParameter("id"));
            JSONObject json = new JSONObject();
            JSONObject contentJson = new JSONObject();
            String havecollect = "false";
            RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(recruitInfoId);
            Enterprise enterprise =enterpriseService
                    .findEnterpriseById(recruitInfo.getEnterpriseId());


            List<CollectEnterprise> collectEnterpriseList = collectEnterpriseService
                    .findCollectEnterpriseByStudentId(student.getStudentId());
            //List空值判定
            if (collectEnterpriseList == null || collectEnterpriseList.isEmpty()
                    ||(collectEnterpriseList.size()==1 && collectEnterpriseList.get(0)==null)){
                System.out.println("collectEnterpriseList is error");
            }else {
                for (CollectEnterprise collectEnterprise : collectEnterpriseList){
                    if (collectEnterprise.getEnterpriseId() == recruitInfo.getEnterpriseId()){
                        havecollect = "true";
                        break;
                    }
                }
            }

            //haveCollectJson.put("havecollect",havecollect);
            //enterpriseIdJson.put("companyid",recruitInfo.getEnterpriseId());
            contentJson.put("jobName",recruitInfo.getJobName());
            contentJson.put("name",enterprise.getName());
            contentJson.put("location",recruitInfo.getLocation());
            contentJson.put("lowSalary",recruitInfo.getLowSalary());
            contentJson.put("highSalary",recruitInfo.getHighSalary());
            contentJson.put("enterpriseIntroduction",enterprise.getEnterpriseIntroduction());
            contentJson.put("deadline",recruitInfo.getDeadline());
            contentJson.put("jobDescribe",recruitInfo.getJobDescribe());
            contentJson.put("jobRequire",recruitInfo.getJobRequire());

            json.put("havecollect",havecollect);
            json.put("companyid",recruitInfo.getEnterpriseId());
            json.put("content",contentJson);
            json.put("jobid",recruitInfoId);

            System.out.println(json);
            SendInfo.render(json.toString(),"text/json",response);

        }
    }

    /**
     * 删除学生收藏的公司
     */
    @RequestMapping(value = "/studentcenter/collection/delete",method = RequestMethod.GET)
    public void studentDeleteCollectionEnterprise(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        Student student = (Student)loginUser;
        String enterpriseId = request.getParameter("id");
        JSONObject json = new JSONObject();
        Map<String,Object> map = new HashMap<String,Object>(){
            {
                put("studentId",student.getStudentId());
                put("enterpriseId",enterpriseId);
            }
        };
        collectEnterpriseService.deleteCollectEnterpriseById(map);
        json.put("ok","true");
        SendInfo.render(json.toString(),"text/json",response);
    }

    /**
     * 学生投递简历操作
     */
    @RequestMapping(value = "/studentcenter/deliver",method = RequestMethod.POST)
    public void studentDeliver(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        request.setCharacterEncoding("UTF-8");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
            return;
        }
        Student student = (Student)loginUser;
        String result = null;
        String recruitInfoId = request.getParameter("jobid");
//        判断传输正误
        if(recruitInfoId == null || recruitInfoId.equals("")){
            result = "{\"ok\":\"false\",\"reason\":\"传到后台的招聘信息号为空\"}";
            SendInfo.render(result,"text/json",response);
            return;
        }
//        查询简历表，判断是否已经填写简历
        Long resumeId = resumeService.findResumeIdByStudentId(student.getStudentId());
        if(resumeId == null){
            result = "{\"ok\":\"false\",\"reason\":\"你还没有填写简历\"}";
            SendInfo.render(result,"text/json",response);
            return;
        }
//        查询发布招聘信息的公司
        RecruitInfo recruitInfo = recruitInfoService.findRecruitInfoById(Long.parseLong(recruitInfoId));
        if(recruitInfo == null){
            System.out.println("内部错误:查询招聘信息出错");
            result = "{\"ok\":\"false\",\"reason\":\"我们的数据出了点差错\"}";
            SendInfo.render(result,"text/json",response);
            return;
        }
        Enterprise enterprise = enterpriseService.findEnterpriseById(recruitInfo.getEnterpriseId());
        if(enterprise == null){
            System.out.println("内部错误:查询公司出错");
            result = "{\"ok\":\"false\",\"reason\":\"我们的数据出了点差错\"}";
            SendInfo.render(result,"text/json",response);
            return;
        }
        System.out.println("recruitinfoid:" + recruitInfoId);
        Timestamp dateTime = new Timestamp(System.currentTimeMillis());
        Deliver deliver = new Deliver(Long.parseLong("0"),resumeId,enterprise.getEnterpriseId(),
                Long.parseLong(recruitInfoId),dateTime,false,false);
        deliverService.addDeliver(deliver);
        result = "{\"ok\":\"true\"}";
        SendInfo.render(result,"text/json",response);
    }

    /**
     * 学生请求邮箱验证
     * */
    @RequestMapping(value = "/studentcenter/valid",method = RequestMethod.POST)
    public void studentValidate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Student)){
            response.sendRedirect("/");
        }
        MailUtil mailUtil = new MailUtil();
        String email = request.getParameter("stuemail");
        JSONObject SendStatusJson = new JSONObject();
        boolean isSuccess = true;
        System.out.println("email ="+email);
        if (email == null ||"".equals(email)){
            System.out.println("email error");
            SendStatusJson.put("ok",false);
        }else{
            try {
                mailUtil.Send(email);
                isSuccess = true;
            } catch (MessagingException e) {
                e.printStackTrace();
                isSuccess = false;

            }finally {
                SendStatusJson.put("ok",isSuccess);
            }

        }
        SendInfo.render(SendStatusJson.toString(),"text/json",response);
    }

    /**
     * 请求学生邮箱验证状态
     * */
    @RequestMapping(value = "/studentcenter/validationstate",method = RequestMethod.GET)
    public void studentValidationState(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Student)) {
            response.sendRedirect("/");
        }
        Student student = (Student) loginUser;
        StudentDetail studentDetail = studentDetailService.findStudentDetailByStudentId(student.getStudentId());
        String result;
        if (studentDetail.getValidation()) {
            System.out.println("已验证成功");
            result = "{\"validation\":\"true\"}";
            SendInfo.render(result, "text/json", response);
            return;
        }
        result = "{\"validation\":\"false\"}";
        SendInfo.render(result, "text/json", response);
    }

    /**
     * 学生在邮箱里点击了确认链接
     * */
    @RequestMapping(value = "/student/confirm",method = RequestMethod.GET)
    public void studentComfirm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Student)) {
            return;
        }
        Student student = (Student) loginUser;
        //TODO:取出get请求参数，进行解密，排除非法验证
//        将学生验证标志改为已验证
        studentDetailService.updateStudentDetailValidationByStudentId(student.getStudentId());
        String result = "Congratulations! You have validated your account successfully.";
        SendInfo.render(result,"text",response);
    }

    /**
     * 保存简历
     */
    @RequestMapping(value = "/studentcenter/saveresume",method = RequestMethod.POST)
    public void studentSaveResume(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Student)) {
            response.sendRedirect("/");
        }
        Student student = (Student) loginUser;
        Long resumeId = resumeService.findResumeIdByStudentId(student.getStudentId());
        String content = request.getParameter("content");

        JSONObject json = new JSONObject(content);
        JSONArray edusJsonArray = json.getJSONArray("edus");
        JSONArray worksJsonArray = json.getJSONArray("works");
        JSONObject leaderJson = json.getJSONObject("leader");
        JSONObject skillJson = json.getJSONObject("skill");

        //header
        ResumeHead resumeHead = resumeDetailService.findResumeHeadByResumeId(resumeId);
        if (resumeHead != null){
            System.out.println("resumeHead != null");
            resumeDetailService.deleteResumeHeadByResumeId(resumeId);
        }
        String name =(String) json.get("name");
        String address = (String) json.get("address");
        String phoneNum = (String) json.get("phone");
        String mailbox = (String) json.get("mail");
        ResumeHead resumeHeadTemp = new ResumeHead(resumeId,name,address,phoneNum,mailbox);
        resumeDetailService.addResumeHead(resumeHeadTemp);


        //edus
        List<ResumeEducation> resumeEducationList = resumeDetailService
                .findResumeEducationByResumeId(resumeId);
        if (resumeEducationList == null || resumeEducationList.isEmpty()
                || (resumeEducationList.size()==1 && resumeEducationList.get(0) == null)){
            System.out.println("resumeEducationList is null");
        }else{
            resumeDetailService.deleteResumeEducationByResumeId(resumeId);
        }

        for (int i = 0; i < edusJsonArray.length(); i++){
            JSONObject tempJson = edusJsonArray.getJSONObject(i);
            String university = (String) tempJson.get("school");
            String city = (String) tempJson.get("city");
            String province = (String) tempJson.get("province");
            String major = (String) tempJson.get("college");
            System.out.println("end_time = "+ tempJson.get("end_time"));
            Date end_time = ParseStringUtil.parseStringToDate((String) tempJson.get("end_time")) ;
            Integer grade;
            if (tempJson.get("grade")==null || "".equals(tempJson.get("grade"))){
                grade = null;
            }else{
                grade = Integer.parseInt((String) tempJson.get("grade"));
            }
            System.out.println("0");
            String honors = (String) tempJson.get("honors");
            System.out.println("1");
            String related_course = (String) tempJson.get("related_course");
            System.out.println("2");
            ResumeEducation resumeEducation = new ResumeEducation(new Long(0),resumeId,university
                    ,city,province,major,end_time,grade,honors,related_course);
            System.out.println("3");
            resumeDetailService.addResumeEducation(resumeEducation);
        }

        //works
        List<ResumeWorks> resumeWorksList = resumeDetailService
                .findResumeWorksByResumeId(resumeId);
        if (resumeWorksList == null || resumeWorksList.isEmpty()
                || (resumeWorksList.size()==1 && resumeWorksList.get(0) == null)){
            System.out.println("resumeEducationList is null");
        }else{
            resumeDetailService.deleteResumeWorksByResumeId(resumeId);
        }
        for (int i = 0; i < worksJsonArray.length(); i++){
            JSONObject tempJson = worksJsonArray.getJSONObject(i);
            String company = (String) tempJson.get("company");
            String city = (String) tempJson.get("city");
            String province = (String) tempJson.get("province");
            String jobName = (String) tempJson.get("position");
            String projectName = (String) tempJson.get("project");
            Date start_time = ParseStringUtil.parseStringToDate((String) tempJson.get("start_time"));
            Date end_time = ParseStringUtil.parseStringToDate((String) tempJson.get("end_time"));
            String sentence_1 = (String) tempJson.get("sentence_1");
            String sentence_2 = (String) tempJson.get("sentence_2");
            String sentence_3 = (String) tempJson.get("sentence_3");
            String sentence_4 = (String) tempJson.get("sentence_4");

            ResumeWorks resumeWorks = new ResumeWorks(new Long(0),resumeId,company,city,province
                    ,jobName,projectName,start_time,end_time,sentence_1,sentence_2,sentence_3,sentence_4);
            resumeDetailService.addResumeWorks(resumeWorks);
        }

        //leader
        //organization
        List<ResumeOrganization> resumeOrganizationList = resumeDetailService
                .findResumeOrganizationByResumeId(resumeId);
        if (resumeOrganizationList == null || resumeOrganizationList.isEmpty()
                || (resumeOrganizationList.size()==1 && resumeOrganizationList.get(0) == null)){
            System.out.println("resumeOrganizationList is null");
        }else{
            resumeDetailService.deleteResumeOrganizationByResumeId(resumeId);
        }
        JSONArray organizationJsonArray = leaderJson.getJSONArray("organization");
        for (int i = 0; i < organizationJsonArray.length(); i++){
            JSONObject tempJson = organizationJsonArray.getJSONObject(i);
            String tempName = (String) tempJson.get("name");
            String jobName = (String) tempJson.get("position");
            Date start_time = ParseStringUtil.parseStringToDate((String) tempJson.get("start_time"));
            Date end_time = ParseStringUtil.parseStringToDate((String) tempJson.get("end_time"));
            String sentence_1 = (String) tempJson.get("sentence_1");
            String sentence_2 = (String) tempJson.get("sentence_2");
            String sentence_3 = (String) tempJson.get("sentence_3");

            ResumeOrganization resumeOrganization = new ResumeOrganization(new Long(0),resumeId,tempName,jobName
                    ,start_time,end_time,sentence_1,sentence_2,sentence_3);
            resumeDetailService.addResumeOrganization(resumeOrganization);
        }

        //club
        List<ResumeClub> resumeClubList = resumeDetailService
                .findResumeClubByResumeId(resumeId);
        if (resumeClubList == null || resumeClubList.isEmpty()
                || (resumeClubList.size()==1 && resumeClubList.get(0) == null)){
            System.out.println("resumeClubList is null");
        }else{
            resumeDetailService.deleteResumeClubByResumeId(resumeId);
        }
        JSONArray clubJsonArray = leaderJson.getJSONArray("organization");
        for (int i = 0; i < clubJsonArray.length(); i++){
            JSONObject tempJson = clubJsonArray.getJSONObject(i);
            String tempName = (String) tempJson.get("name");
            String jobName = (String) tempJson.get("position");
            Date start_time = ParseStringUtil.parseStringToDate((String) tempJson.get("start_time"));
            Date end_time = ParseStringUtil.parseStringToDate((String) tempJson.get("end_time"));
            String sentence_1 = (String) tempJson.get("sentence_1");
            String sentence_2 = (String) tempJson.get("sentence_2");


            ResumeClub resumeClub = new ResumeClub(new Long(0),resumeId,tempName,jobName
                    ,start_time,end_time,sentence_1,sentence_2);
            resumeDetailService.addResumeClub(resumeClub);
        }

        //skill
        ResumeSkill resumeSkill = resumeDetailService.findResumeSkillByResumeId(resumeId);
        if (resumeSkill != null){
            resumeDetailService.deleteResumeSkillByResumeId(resumeId);
        }
        String language =(String) skillJson.get("language");
        String computer = (String) skillJson.get("computer");
        String hobby = (String) skillJson.get("hobby");

        ResumeSkill resumeSkillTemp = new ResumeSkill(new Long(0),resumeId,language
                ,computer,hobby);
        resumeDetailService.addResumeSkill(resumeSkillTemp);
    }

    /**
     * 加载简历
     */
    @RequestMapping(value = "/studentcenter/showresume",method = RequestMethod.GET)
    public void studentShowResume(HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser == null || !(loginUser instanceof Student)) {
            response.sendRedirect("/");
        }
        //String resumeid = request.getParameter("resumeid");
        Student student = (Student) loginUser;

        StudentDetail studentDetail = studentDetailService.findStudentDetailByStudentId(student.getStudentId());
        if (studentDetail == null){
            System.out.println("student = null");
        }else{
            System.out.println("student != null");
        }
        Long resumeId = resumeService.findResumeIdByStudentId(student.getStudentId());
        /*if ( resumeid == null||"".equals(resumeid)){
            System.out.println("resumeid error");
            return;
        }*/
        //Long resumeId = Long.parseLong(request.getParameter("resumeid"));

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
            //System.out.println("end time = "+resumeEducation.getGraduateDate());
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
