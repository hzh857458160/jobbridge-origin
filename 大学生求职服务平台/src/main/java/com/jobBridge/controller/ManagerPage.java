package com.jobBridge.controller;

import com.jobBridge.model.Manager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SYunk on 2017/7/21.
 */
@Controller
public class ManagerPage {
    /*
    * 管理员添加公司企业界面
    * */
    @RequestMapping(value = "/adminPage/addEnterprise",method = RequestMethod.GET)
    public String adminPageForEnterprise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Manager)){
            response.sendRedirect("/");
        }
        Manager manager = (Manager) loginUser;
        return "/public/indexcom.html";
    }
    /*
    * 管理员添加新管理员界面
    * */
    @RequestMapping(value = "/adminPage/addManager",method = RequestMethod.GET)
    public String adminPageForManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Manager)){
            response.sendRedirect("/");
        }
        Manager manager = (Manager) loginUser;
        return "/public/indexadm.html";
    }
    /*
    * 管理员退出操作
    * */
    @RequestMapping(value = "/adminPage/exit",method = RequestMethod.GET)
    public String adminPageForExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null || !(loginUser instanceof Manager)){
            response.sendRedirect("/");
        }
        Manager manager = (Manager) loginUser;
        request.getSession().removeAttribute("loginUser");
        return "/public/index.html";
    }
}
