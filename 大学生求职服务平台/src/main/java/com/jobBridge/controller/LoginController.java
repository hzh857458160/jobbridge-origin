package com.jobBridge.controller;

import com.jobBridge.Dao.IManagerDao;
import com.jobBridge.model.Manager;
import com.jobBridge.service.EnterpriseService;
import com.jobBridge.Dao.IEnterpriseDao;
import com.jobBridge.Dao.IStudentDao;
import com.jobBridge.service.ManagerService;
import com.jobBridge.service.StudentService;
import com.jobBridge.model.Enterprise;
import com.jobBridge.model.Student;
import com.jobBridge.util.Crypto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by SYunk on 2017/7/19.
 */
@Controller
public class LoginController {

    private IStudentDao studentService = new StudentService();
    private IEnterpriseDao enterpriseService = new EnterpriseService();
    private IManagerDao managerService = new ManagerService();

    @RequestMapping(value = "/forLogin/com",method = RequestMethod.GET)
    public String comForLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String timestamp = Long.toString(System.currentTimeMillis());
        request.getSession().setAttribute("timestamp",timestamp);
        return "/public/comlogin.html";
    }

    @RequestMapping(value = "/forLogin/stu",method = RequestMethod.GET)
    public String stuForLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String timestamp = Long.toString(System.currentTimeMillis());
        request.getSession().setAttribute("timestamp",timestamp);
        return "/public/stulogin.html";
    }

    @RequestMapping(value = "/forLogin/admin",method = RequestMethod.GET)
    public String adminForLogin(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String timestamp = Long.toString(System.currentTimeMillis());
        request.getSession().setAttribute("timestamp",timestamp);
        return "/public/admlogin.html";
    }

    /**
    * 学生登录处理
    * */
    @RequestMapping(value = "/login/student",method = RequestMethod.POST)
    public void stuLogin(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String,String> map) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        /*String loginName = map.get("userName");
        String password = map.get("passWord");*/
        String loginName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        System.out.println("这里是学生在登录");
        Student student = null;
        if(loginName.contains("@")){
            student = studentService.findStudentByMailbox(loginName);
        }else{
            student = studentService.findStudentByUserName(loginName);
        }
        if(student == null){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //用户不存在
        }else if(!Crypto.validPassword(password,student.getPassword())){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //密码错误
        }else {
            result = "{\"ok\":\"true\"}";
            request.getSession().setAttribute("loginUser",student);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
    * 公司登录处理
    * */
    @RequestMapping(value = "/login/enterprise",method = RequestMethod.POST)
    public void comLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        String loginName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        System.out.println("这里是公司在登录");
        Enterprise enterprise = null;
        if(loginName.contains("@")){
            enterprise = enterpriseService.findEnterpriseByMailbox(loginName);
        }else{
            enterprise = enterpriseService.findEnterpriseByUserName(loginName);
        }
        if(enterprise == null){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //用户不存在
        }else if(!Crypto.validPassword(password,enterprise.getPassword())){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //密码错误
        }else {
            result = "{\"ok\":\"true\"}";
            request.getSession().setAttribute("loginUser",enterprise);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }

    /**
    * 管理员登录处理
    * */
    @RequestMapping(value = "/login/admin",method = RequestMethod.POST)
    public void managerLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String result = "";
        response.setHeader("Access-Control-Allow-Origin", "*");
        String loginName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        System.out.println("这里是管理员在登录");
        Manager manager = null;
        if(loginName.contains("@")){
            manager = managerService.findManagerByUserName(loginName);
        }else{
            manager = managerService.findManagerByUserName(loginName);
        }
        if(manager == null){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //用户不存在
        }else if(!Crypto.validPassword(password,manager.getPassword())){
            result = "{\"ok\":\"false\",\"reason\":\"用户不存在或密码错误\"}";     //密码错误
        }else {
            result = "{\"ok\":\"true\"}";
            request.getSession().setAttribute("loginUser",manager);
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
    }
}
