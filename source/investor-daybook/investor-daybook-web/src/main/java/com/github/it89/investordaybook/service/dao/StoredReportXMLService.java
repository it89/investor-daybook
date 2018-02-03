package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;

import java.util.List;

public interface StoredReportXMLService {
    public StoredReportXML findById(long id);
    public List<StoredReportXML> getList(AppUser appUser);
    public void save(StoredReportXML storedReportXML);
}
