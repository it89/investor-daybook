package com.github.it89.investordaybook.repository;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoredReportXMLRepository extends CrudRepository<StoredReportXML, Long> {
    List<StoredReportXML> findAllByAppUser(AppUser appUser);
}
