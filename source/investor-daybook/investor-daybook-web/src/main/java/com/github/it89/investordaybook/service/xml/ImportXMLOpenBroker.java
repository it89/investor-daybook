package com.github.it89.investordaybook.service.xml;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.SecurityType;
import com.github.it89.investordaybook.model.daybook.StoredReportXML;
import com.github.it89.investordaybook.service.dao.DealBondService;
import com.github.it89.investordaybook.service.dao.DealStockService;
import com.github.it89.investordaybook.service.dao.SecurityService;
import com.github.it89.investordaybook.service.dao.StoredReportXMLService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.time.LocalDate;

@Service
@Repository
@Transactional
public class ImportXMLOpenBroker implements ImportXML {
    private final SecurityService securityService;
    private final DealBondService dealBondService;
    private final DealStockService dealStockService;
    private final StoredReportXMLService storedReportXMLService;

    private XPathFactory pathFactory = XPathFactory.newInstance();

    @Autowired
    public ImportXMLOpenBroker(SecurityService securityService, DealBondService dealBondService, DealStockService dealStockService, StoredReportXMLService storedReportXMLService) {
        this.securityService = securityService;
        this.dealBondService = dealBondService;
        this.dealStockService = dealStockService;
        this.storedReportXMLService = storedReportXMLService;
    }

    @Override
    public void importXML(StoredReportXML storedReportXML) {
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(IOUtils.toInputStream(storedReportXML.getText()));
            importSecurity(document, storedReportXML.getAppUser());
            //importMaidDeals(document, storedReportXML.getAppUser());
            importInfo(document, storedReportXML);
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private void importInfo(Document document, StoredReportXML storedReportXML) throws XPathExpressionException {
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile("//broker_report");
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        if (nodes.getLength() > 0) {
            Node n = nodes.item(0);
            NamedNodeMap nodeMap = n.getAttributes();
            LocalDate dateFrom = LocalDate.parse(nodeMap.getNamedItem("date_from").getTextContent().substring(0, 10));
            LocalDate dateTo = LocalDate.parse(nodeMap.getNamedItem("date_to").getTextContent().substring(0, 10));
            storedReportXML.setDateFrom(dateFrom);
            storedReportXML.setDateTo(dateTo);

            storedReportXMLService.save(storedReportXML);
        }
    }

    private void importSecurity(Document document, AppUser appUser) throws XPathExpressionException {
        XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile("//spot_portfolio_security_params/item");
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            NamedNodeMap nodeMap = n.getAttributes();

            String isin = nodeMap.getNamedItem("isin").getTextContent();
            String textSecurityType = nodeMap.getNamedItem("security_type").getTextContent();
            String ticker = nodeMap.getNamedItem("ticker").getTextContent();
            String caption = nodeMap.getNamedItem("security_name").getTextContent().trim();
            SecurityType securityType;
            if (textSecurityType.equalsIgnoreCase("Акции")) {
                securityType = SecurityType.STOCK;
            } else if (textSecurityType.equalsIgnoreCase("Облигации")) {
                securityType = SecurityType.BOND;
            } else if (textSecurityType.equalsIgnoreCase("GDR")) {
                securityType = SecurityType.GDR;
            } else {
                throw new XPathExpressionException("Not valid XML");
            }

            Node nodeCodeGRN = nodeMap.getNamedItem("security_grn_code");
            String codeGRN = null;
            if (nodeCodeGRN != null) {
                codeGRN = nodeCodeGRN.getTextContent();
            }

            Security security = securityService.findByIsin(isin, appUser);
            if (security == null) {
                security = new Security();
                security.setIsin(isin);
                security.setAppUser(appUser);
            }
            security.setTicker(ticker);
            security.setCaption(caption);
            security.setCodeGRN(codeGRN);
            security.setType(securityType);

            securityService.save(security);
        }
    }

    private void importMaidDeals(Document document, AppUser appUser) throws XPathExpressionException {
        /*XPath xpath = pathFactory.newXPath();
        XPathExpression expr = xpath.compile("//spot_main_deals_conclusion/item");
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            NamedNodeMap nodeMap = n.getAttributes();

            Security security;
            Node securityGRNCode = nodeMap.getNamedItem("security_grn_code");
            if (securityGRNCode != null) {
                security = securityService.findByCodeGRN(securityGRNCode.getTextContent(), appUser);
            } else {
                String securityCaption = nodeMap.getNamedItem("security_name").getTextContent().trim();
                security = securityService.findByCaption(securityCaption, appUser);
            }

            String dealNumber = nodeMap.getNamedItem("deal_no").getTextContent();
            LocalDateTime dateTime = LocalDateTime.parse(nodeMap.getNamedItem("conclusion_time").getTextContent());

            long amount;
            TradeOperation operation;
            Node buyQnty = nodeMap.getNamedItem("buy_qnty");
            if (buyQnty != null) {
                operation = TradeOperation.BUY;
                amount = Double.valueOf(buyQnty.getTextContent()).longValue();
            } else {
                operation = TradeOperation.SELL;
                amount = Double.valueOf(nodeMap.getNamedItem("sell_qnty").getTextContent()).longValue();
            }

            BigDecimal volume = new BigDecimal(nodeMap.getNamedItem("volume_currency").getTextContent());
            BigDecimal commission = new BigDecimal(nodeMap.getNamedItem("broker_commission").getTextContent());

            if (security.getType() == SecurityType.STOCK) {
                DealStock dealStock = new DealStock.Builder(dealNumber)
                        .security((SecurityStock) security)
                        .dateTime(dateTime)
                        .operation(operation)
                        .amount(amount)
                        .volume(volume)
                        .commission(commission)
                        .appUser(appUser)
                        .price(new BigDecimal(nodeMap.getNamedItem("price").getTextContent()))
                        .build();
                dealStockService.save(dealStock);
            } else if (security.getType() == SecurityType.BOND){
                DealBond dealBond = new DealBond.Builder(dealNumber)
                        .security((SecurityBond) security)
                        .dateTime(dateTime)
                        .operation(operation)
                        .amount(amount)
                        .volume(volume)
                        .commission(commission)
                        .appUser(appUser)
                        .pricePct(new BigDecimal(nodeMap.getNamedItem("price").getTextContent()))
                        .accumulatedCouponYield(new BigDecimal(nodeMap.getNamedItem("nkd").getTextContent()))
                        .build();
                dealBondService.save(dealBond);
            } else {
                throw new AssertionError("Unknown security type:" + security.getType().name());
            }
        }*/
        throw new AssertionError("Not implement");
    }
    // TODO: add import of cashflow
}
