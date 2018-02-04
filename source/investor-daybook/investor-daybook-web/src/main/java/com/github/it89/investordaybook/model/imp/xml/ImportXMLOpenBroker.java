package com.github.it89.investordaybook.model.imp.xml;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.Security;
import com.github.it89.investordaybook.model.daybook.SecurityBond;
import com.github.it89.investordaybook.model.daybook.SecurityStock;
import com.github.it89.investordaybook.model.daybook.SecurityType;
import com.github.it89.investordaybook.service.dao.SecurityService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import javax.xml.xpath.*;

public class ImportXMLOpenBroker implements ImportXML {
    @Autowired
    private SecurityService securityService;
    private XPathFactory pathFactory = XPathFactory.newInstance();

    @Override
    public void importXML(String xml, AppUser appUser) {
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(IOUtils.toInputStream(xml));
            importSecurity(document, appUser);
        } catch (ParserConfigurationException | XPathExpressionException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
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
            if (textSecurityType.equalsIgnoreCase("Акции") || textSecurityType.equalsIgnoreCase("GDR")) {
                securityType = SecurityType.STOCK;
            } else if (textSecurityType.equalsIgnoreCase("Облигации")) {
                securityType = SecurityType.BOND;
            } else {
                throw new XPathExpressionException("Not valid XML");
            }
            Node nodeCodeGRN = nodeMap.getNamedItem("security_grn_code");
            String codeGRN = null;
            if (nodeCodeGRN != null) {
                codeGRN = nodeCodeGRN.getTextContent();
            }

            Security security = null;
            switch (securityType) {
                case STOCK:
                    security = new SecurityStock.Builder(isin)
                        .ticker(ticker)
                        .caption(caption)
                        .codeGRN(codeGRN)
                        .appUser(appUser)
                        .build();
                    break;
                case BOND:
                    security = new SecurityBond.Builder(isin)
                            .ticker(ticker)
                            .caption(caption)
                            .codeGRN(codeGRN)
                            .appUser(appUser)
                            .build();
                    break;
                default:
                    throw new AssertionError("Unknown security type:" + securityType.name());
            }
            securityService.save(security);
        }
    }
}
