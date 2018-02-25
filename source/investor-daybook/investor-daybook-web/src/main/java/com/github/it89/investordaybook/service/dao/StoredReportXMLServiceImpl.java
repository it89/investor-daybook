package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.dao.StoredReportXMLDAO;
import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("storedReportXMLService")
@Transactional
public class StoredReportXMLServiceImpl implements StoredReportXMLService {
    /*@Autowired
    private StoredReportXMLDAO dao;

    @Override
    public StoredReportXML findById(long id) {
        return dao.findById(id);
    }

    @Override
    public List<StoredReportXML> getList(AppUser appUser) {
        return dao.getList(appUser);
    }

    @Override
    public void save(StoredReportXML storedReportXML) {
        dao.save(storedReportXML);
    }*/
}
