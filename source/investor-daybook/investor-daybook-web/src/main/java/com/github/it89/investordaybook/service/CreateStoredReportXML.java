package com.github.it89.investordaybook.service;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.service.xml.ImportXML;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("createStoredReportXML")
public class CreateStoredReportXML {
    private final ImportXML importXML;

    @Autowired
    public CreateStoredReportXML(ImportXML importXML) {
        this.importXML = importXML;
    }

    public String upload(MultipartFile file, AppUser appUser) {
        if (!file.isEmpty()) {
            try {
                if ("text/xml".equals(file.getContentType())) {
                    String fileString = IOUtils.toString(file.getInputStream());
                    create(file.getOriginalFilename(), fileString, appUser);
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

    private void create(String filename, String text, AppUser appUser) {
        StoredReportXML reportXML = new StoredReportXML();
        reportXML.setFilename(filename);
        reportXML.setText(text);
        reportXML.setAppUser(appUser);
        importXML.importXML(reportXML);
    }
}
