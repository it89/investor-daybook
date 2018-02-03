package com.github.it89.investordaybook.controller;

import com.github.it89.investordaybook.service.CreateStoredReportXML;
import com.github.it89.investordaybook.service.DoSomething;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {
    @Autowired
    private DoSomething doSomething;
    @Autowired
    private CreateStoredReportXML createStoredReportXML;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getUserName());
        return "accessDenied";
    }

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseBody
    public String doSomething() {
        // TODO: For test only!
        return doSomething.doIt();
    }

    @RequestMapping(value = "/uploadXML", method = RequestMethod.GET)
    public String uploadXML() {
        return "uploadXML";
    }

    @RequestMapping(value = "/uploadReportXML", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        return createStoredReportXML.upload(file, getUserName());
    }

    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    @ResponseBody
    public String doPost() {
        return "post!";
    }

    private String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}


