package com.github.it89.investordaybook.controller;

import com.github.it89.investordaybook.model.AppUser;
import com.github.it89.investordaybook.model.daybook.*;
import com.github.it89.investordaybook.reports.TradeReportXLS;
import com.github.it89.investordaybook.service.CreateStoredReportXML;
import com.github.it89.investordaybook.service.DoSomething;
import com.github.it89.investordaybook.service.dao.*;
import com.github.it89.investordaybook.service.xml.ImportXML;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    private final DoSomething doSomething;
    private final AppUserService appUserService;
    private final SecurityService securityService;
    private final DealStockService dealStockService;
    private final DealBondService dealBondService;
    private final CreateStoredReportXML createStoredReportXML;
    private final StoredReportXMLService storedReportXMLService;
    private final ImportXML importXML;
    private final TradeAccountService tradeAccountService;

    @Autowired
    public MainController(DoSomething doSomething, AppUserService appUserService, SecurityService securityService, DealStockService dealStockService, DealBondService dealBondService, CreateStoredReportXML createStoredReportXML, StoredReportXMLService storedReportXMLService, ImportXML importXML, TradeAccountService tradeAccountService) {
        this.doSomething = doSomething;
        this.appUserService = appUserService;
        this.securityService = securityService;
        this.dealStockService = dealStockService;
        this.dealBondService = dealBondService;
        this.createStoredReportXML = createStoredReportXML;
        this.storedReportXMLService = storedReportXMLService;
        this.importXML = importXML;
        this.tradeAccountService = tradeAccountService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getUserName());
        return "accessDenied";
    }

    @RequestMapping(value = "/uploadXML", method = RequestMethod.GET)
    public String uploadXML() {
        return "uploadXML";
    }

    @RequestMapping(value = "/uploadReportXML", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        return createStoredReportXML.upload(file, getAppUser());
    }

    private String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    private AppUser getAppUser() {
        return appUserService.findByLogin(getUserName());
    }

    @RequestMapping(value = {"/import"}, method = RequestMethod.GET)
    public String listStoredXML(ModelMap model) {
        List<StoredReportXML> reports = storedReportXMLService.getList(appUserService.findByLogin(getUserName()));
        model.addAttribute("reports", reports);
        return "importReportsList";
    }

    @RequestMapping(value = {"/import-report-{repId}"}, method = RequestMethod.GET)
    public String importReport(@PathVariable String repId) {
        StoredReportXML storedReportXML = storedReportXMLService.findById(Long.parseLong(repId));
        if (storedReportXML.getAppUser().getLogin().equalsIgnoreCase(getUserName())) {
            importXML.importXML(storedReportXML);
        }
        return "redirect:/import";
    }

    @RequestMapping(value = {"/import-report-all"}, method = RequestMethod.GET)
    public String importReportAll() {
        List<StoredReportXML> list = storedReportXMLService.getList(getAppUser());
        for (StoredReportXML storedReportXML : list) {
            importXML.importXML(storedReportXML);
        }
        return "redirect:/import";
    }

    @RequestMapping(value = {"/securities"}, method = RequestMethod.GET)
    public String securities(ModelMap model) {
        List<Security> securities = securityService.getList(getAppUser());
        model.addAttribute("securities", securities);
        return "securities";
    }

    @RequestMapping(value = {"/deals"}, method = RequestMethod.GET)
    public String deals(ModelMap model) {
        List<DealStock> stockDeals = dealStockService.getList(appUserService.findByLogin(getUserName()));
        List<DealBond> bondDeals = dealBondService.getList(appUserService.findByLogin(getUserName()));
        model.addAttribute("stockDeals", stockDeals);
        model.addAttribute("bondDeals", bondDeals);
        return "deals";
    }

    @RequestMapping(value = "/report/TradeReport-{accountCode}", method = RequestMethod.GET)
    public void getTradeReportXML(@PathVariable String accountCode, HttpServletResponse response) {
        AppUser appUser = getAppUser();
        if (appUser != null) {
            TradeAccount account = tradeAccountService.findByCode(accountCode, appUser);
            if (account == null) {
                throw new RuntimeException("Account \"" + accountCode + "\" not found!");
            }
            List<DealStock> dealStocks = dealStockService.getAllByAccount(account);

            try (Workbook wb = TradeReportXLS.createWorkbook(dealStocks)) {
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-disposition", "attachment; filename=TradeReport.xlsx");
                wb.write(response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException ioe) {
                throw new RuntimeException("Error writing file to output stream");
            }
        }



    }

    /////////------TEST-------------////////////////////////////////////////
    // TODO: For test only!

    @RequestMapping(value = "/do", method = RequestMethod.GET)
    @ResponseBody
    public String doSomething() {
        // TODO: For test only!
        return doSomething.doIt();
    }

    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    @ResponseBody
    public String doPost() {
        return "post!";
    }
}


