package com.hajres.controller;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyDao companyDao;

    @Autowired
    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @RequestMapping("/list")
    public String showCompanyList(@ModelAttribute("filter") String filter,
                                  Model model) {
        List<Company> companyList;
        if (filter.equals("")) {
            companyList = companyDao.findAll();
        } else {
            companyList = companyDao.findByAll(filter);
        }
        model.addAttribute("companyList", companyList);
        return "company/company-list";
    }

    @GetMapping("/edit")
    public String getEdit(@ModelAttribute("id") String stringId, Model model) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Company company = companyDao.findById(id);
        model.addAttribute("company", company);
        String formAction = "?id=" + id;
        model.addAttribute("formAction", formAction);
        return "company/company-add-edit";
    }

    @PostMapping("/edit")
    public ModelAndView postEdit(@ModelAttribute("company") Company company,
                                 @ModelAttribute("id") String stringId,
                                 ModelMap model) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        company.setIdCompany(id);
        int result = companyDao.update(company);
        prepareModel(model, result, "updated");
        return new ModelAndView("company/company-list", model);
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "company/company-add-edit";
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@ModelAttribute("company") Company company, ModelMap model) {
        int result = companyDao.add(company);
        prepareModel(model, result, "added");
        return new ModelAndView("company/company-list", model);
    }

    @GetMapping("/delete")
    public String getDelete(@ModelAttribute("id") String stringId, Model model) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Company company = companyDao.findById(id);
        model.addAttribute("company", company);
        return "company/company-delete";
    }

    @PostMapping("/delete")
    public ModelAndView postDelete(@ModelAttribute("id") String stringId, ModelMap model) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int result = companyDao.delete(id);
        prepareModel(model, result, "deleted");
        return new ModelAndView("company/company-list", model);
    }

    private void prepareModel(ModelMap model, int result, String action) {
        if (result == 0) {
            model.addAttribute("errorMessage", "No company " + action + ".");
        } else {
            model.addAttribute("message", "Company " + action + ".");
        }
        List<Company> companyList = companyDao.findAll();
        model.addAttribute("companyList", companyList);
    }
}
