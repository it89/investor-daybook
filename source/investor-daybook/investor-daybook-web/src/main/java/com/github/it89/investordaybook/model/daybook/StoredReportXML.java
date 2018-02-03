package com.github.it89.investordaybook.model.daybook;

import com.github.it89.investordaybook.model.AppUser;

public class StoredReportXML {
    private Long id;
    private AppUser appUser;
    private String filename;
    private String text;

    public StoredReportXML(AppUser appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
