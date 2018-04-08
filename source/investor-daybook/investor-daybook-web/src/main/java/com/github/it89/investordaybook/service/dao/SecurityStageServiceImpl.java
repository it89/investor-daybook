package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.analytics.SecurityStage;
import com.github.it89.investordaybook.model.daybook.TradeAccount;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Repository
@Transactional
public class SecurityStageServiceImpl implements SecurityStageService {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly=true)
    @Override
    public List<SecurityStage> findAllByTradeAccount(TradeAccount account) {
        return em.createQuery("select new com.github.it89.investordaybook.analytics.SecurityStage(" +
                "d.security, d.stage, min(d.dateTime), max(d.dateTime)) " +
                "from Deal d where d.account = :account group by d.security, d.stage order by 3").setParameter("account", account).getResultList();
    }
}
