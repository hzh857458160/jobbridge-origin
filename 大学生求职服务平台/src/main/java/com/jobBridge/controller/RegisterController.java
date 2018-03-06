package com.jobBridge.controller;

import com.jobBridge.Dao.IEnterpriseDao;
import com.jobBridge.Dao.IManagerDao;
import com.jobBridge.Dao.IStudentDao;
import com.jobBridge.model.Enterprise;
import com.jobBridge.model.Manager;
import com.jobBridge.service.EnterpriseService;
import com.jobBridge.service.ManagerService;
import com.jobBridge.service.StudentService;
import com.jobBridge.model.Student;
import com.jobBridge.util.Crypto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by SYunk on 2017/7/19.
 */
@Controller
public class RegisterController {

    private IStudentDao studentService = new StudentService();
    private IManagerDao managerService = new ManagerService();
    private IEnterpriseDao enterpriseService = new EnterpriseService();

    /**
    * 请求学生注册页面
    * */
    @RequestMapping(value = "/forRegister",method = RequestMethod.GET)
    public String forRegister(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "/public/sturegister.html";
    }

    /**
    * 学生注册处理
    * */
    @RequestMapping(value = "/register/student",method = RequestMethod.POST)
    public void studentRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userName = request.getParameter("userName");
        String mailbox = request.getParameter("email");
        String password = request.getParameter("passWord");
        Student student = null;
        if((student = studentService.findStudentByUserName(userName)) != null){
            result = "{\"ok\":\"false\",\"reason\":\"该用户名已被其他人注册\"}";
        }else if((student = studentService.findStudentByMailbox(mailbox)) != null){
            result = "{\"ok\":\"false\",\"reason\":\"该邮箱已被其他人注册\"}";
        }else{
            String cryptoPassword = Crypto.getEncryptedPwd(password);
            Student newStudent = new Student(new Long(0),userName,mailbox,cryptoPassword);
            studentService.addStudent(newStudent);
            result = "{\"ok\":\"true\"}";
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
    * 管理员添加处理
    * */
    @RequestMapping(value = "/register/admin",method = RequestMethod.POST)
    public void adminRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        Manager manager = null;
        if((manager = managerService.findManagerByUserName(userName)) != null){
            result = "{\"ok\":\"false\",\"reason\":\"该用户名已被其他人注册\"}";
        }else{
            String cryptoPassword = Crypto.getEncryptedPwd(password);
            Manager newManager = new Manager(0,userName,cryptoPassword);
            managerService.addManager(newManager);
            result = "{\"ok\":\"true\"}";
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
    * 公司企业注册处理
    * */
    @RequestMapping(value = "/register/enterprise",method = RequestMethod.POST)
    public void enterpriseRegister(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        String userName = request.getParameter("userName");
        String name = request.getParameter("name");
        String mailbox = request.getParameter("mailbox");
        String phoneNum = request.getParameter("phoneNum");
        String password = request.getParameter("password");
        String enterpriseIntroduction = request.getParameter("enterpriseIntroduction");
        Enterprise enterprise = null;
        if((enterprise = enterpriseService.findEnterpriseByUserName(userName)) != null){
            result = "{\"ok\":\"false\",\"reason\":\"该用户名已被其他人注册\"}";
        }else if((enterprise = enterpriseService.findEnterpriseByMailbox(mailbox)) != null){
            result = "{\"ok\":\"false\",\"reason\":\"该邮箱已被其他人注册\"}";
        }else{
            String cryptoPassword = Crypto.getEncryptedPwd(password);
            Enterprise newEnterprise = new Enterprise(Long.parseLong("0"),userName,name,mailbox, phoneNum,cryptoPassword,enterpriseIntroduction,"");
            enterpriseService.addEnterprise(newEnterprise);
            result = "{\"ok\":\"true\"}";
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
