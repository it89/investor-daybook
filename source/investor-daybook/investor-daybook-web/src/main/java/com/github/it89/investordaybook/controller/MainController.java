package com.github.it89.investordaybook.controller;

import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.model.imp.xml.ImportXML;
import com.github.it89.investordaybook.model.imp.xml.ImportXMLOpenBroker;
import com.github.it89.investordaybook.service.CreateStoredReportXML;
import com.github.it89.investordaybook.service.DoSomething;
import com.github.it89.investordaybook.service.dao.AppUserService;
import com.github.it89.investordaybook.service.dao.StoredReportXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private DoSomething doSomething;
    @Autowired
    private CreateStoredReportXML createStoredReportXML;
    @Autowired
    private StoredReportXMLService storedReportXMLService;
    @Autowired
    AppUserService appUserService;
    @Autowired
    ImportXML importXML;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getUserName());
        return "accessDenied";
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

    private String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @RequestMapping(value = {"/import" }, method = RequestMethod.GET)
    public String listStoredXML(ModelMap model) {
        List<StoredReportXML> reports = storedReportXMLService.getList(appUserService.findByLogin(getUserName()));
        model.addAttribute("reports", reports);
        return "importReportsList";
    }

    @RequestMapping(value = { "/import-report-{repId}" }, method = RequestMethod.GET)
    public String importReport(@PathVariable String repId) {
        StoredReportXML storedReportXML = storedReportXMLService.findById(Long.parseLong(repId));
        if (storedReportXML.getAppUser().getLogin().equalsIgnoreCase(getUserName())) {
            importXML.importXML(storedReportXML);
        }
        return "redirect:/import";
    }

    /////////------TEST-------------////////////////////////////////////////
    // TODO: For test only!

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseBody
    public String doSomething() {
        // TODO: For test only!
        return doSomething.doIt();
    }

    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    @ResponseBody
    public String doPost() {
        return "post!";
    }
}


