package com.github.it89.investordaybook.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;

import java.util.List;

public interface StoredReportXMLDAO {
    public StoredReportXML findById(long id);
    public List<StoredReportXML> getList(AppUser appUser);
    public void save(StoredReportXML storedReportXML);
}
