package com.github.it89.investordaybook.service.dao;

import com.github.it89.investordaybook.analytics.CashFlow;
import com.github.it89.investordaybook.analytics.SecurityStage;
import com.github.it89.investordaybook.model.daybook.Deal;
import com.github.it89.investordaybook.model.daybook.DealStock;
import com.github.it89.investordaybook.model.daybook.TradeAccount;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final DealStockService dealStockService;

    @Autowired
    public SecurityStageServiceImpl(DealStockService dealStockService) {
        this.dealStockService = dealStockService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<SecurityStage> findAllByTradeAccount(TradeAccount account) {
        List<SecurityStage> result = em.createQuery("select new com.github.it89.investordaybook.analytics.SecurityStage(" +
                "d.security, d.stage, min(d.dateTime), max(d.dateTime)) " +
                "from Deal d where d.account = :account group by d.security, d.stage order by 3").setParameter("account", account).getResultList();
        for (SecurityStage securityStage : result) {
            List<CashFlow> cashFlow = securityStage.getCashFlow();
            List<DealStock> dealStocks= dealStockService.getAllBySecurityAndStage(securityStage.getSecurity(), securityStage.getStage(), account);
            long amountSum = Deal.getAmountSum(dealStocks);
            cashFlow.addAll(dealStocks);
            securityStage.setAmountSum(amountSum);
        }

        return result;
    }
}
