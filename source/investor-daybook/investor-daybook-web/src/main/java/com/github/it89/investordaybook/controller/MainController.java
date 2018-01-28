package com.github.it89.investordaybook.controller;

import com.github.it89.investordaybook.DoSomething;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;

import java.io.InputStream;

@Controller
public class MainController {
    @Autowired
    private DoSomething doSomething;

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

        if (!file.isEmpty()) {
            try {
                String fileString = null;
                if ("text/xml".equals(file.getContentType())) {
                    InputStream fileInputStream = file.getInputStream();
                    fileString = fileInputStream.toString();
                }
                return "You successfully uploaded file " + file.getName();
            } catch (Exception e) {
                return "You failed to upload " + file.getName() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getName()
                    + " because the file was empty.";
        }
    }

    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    @ResponseBody
    public String doPost() {
        return "post!";
    }

    @RequestMapping(value = "/doPost", method = RequestMethod.GET)
    @ResponseBody
    public String doGET() {
        return "GET!";
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


