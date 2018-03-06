package com.jobBridge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by SYunk on 2017/7/19.
 */
@Controller
public class FirstPageController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String firstPage(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return "public/index.html";
    }
}
