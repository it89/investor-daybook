package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.repository.StoredReportXMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Repository
@Transactional
public class StoredReportXMLServiceImpl implements StoredReportXMLService {
    private final StoredReportXMLRepository storedReportXMLRepository;

    @Autowired
    public StoredReportXMLServiceImpl(StoredReportXMLRepository storedReportXMLRepository) {
        this.storedReportXMLRepository = storedReportXMLRepository;
    }

    @Override
    public StoredReportXML findById(long id) {
        return storedReportXMLRepository.findById(id).get();
    }

    @Override
    public List<StoredReportXML> getList(AppUser appUser) {
        return storedReportXMLRepository.findAllByAppUser(appUser);
    }

    @Override
    public void save(StoredReportXML storedReportXML) {
        storedReportXMLRepository.save(storedReportXML);
    }
}
