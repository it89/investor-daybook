package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.service.dao.AppUserService;
import com.github.it89.investordaybook.service.dao.StoredReportXMLService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("createStoredReportXML")
public class CreateStoredReportXML {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private StoredReportXMLService storedReportXMLService;

    public String upload(MultipartFile file, String userName) {
        if (!file.isEmpty()) {
            try {
                if ("text/xml".equals(file.getContentType())) {
                    String fileString = IOUtils.toString(file.getInputStream());
                    create(file.getOriginalFilename(), fileString, userName);
                }
                return "You successfully uploaded file " + file.getOriginalFilename();
            } catch (Exception e) {
                return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.";
        }
    }

    private void create(String filename, String text, String appUserName) {
        AppUser appUser = appUserService.findByLogin(appUserName);
        if (appUser == null) {
            throw new AssertionError("User " + appUserName + " not found");
        }
        StoredReportXML reportXML = new StoredReportXML(appUser);
        reportXML.setFilename(filename);
        reportXML.setText(text);
        storedReportXMLService.save(reportXML);
    }
}
