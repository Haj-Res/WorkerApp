package com.hajres.controller;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    private final CompanyDao companyDao;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @Autowired
    public CompanyController(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    @RequestMapping("/list")
    public String showCompanyList(@ModelAttribute("filter") String filter,
                                  Model model) {
        List<Company> companyList;
        if (filter == null) {
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
    public String postEdit(@Valid @ModelAttribute("company") Company company,
                                 BindingResult bindingResult,
                                 @ModelAttribute("id") String stringId,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "company/company-add-edit";
        }
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        company.setIdCompany(id);
        int result = companyDao.update(company);
        prepareModel(model, result, "updated");
        return "company/company-list";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "company/company-add-edit";
    }

    @PostMapping("/add")
    public String postAdd(@Valid @ModelAttribute("company") Company company,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            return "company/company-add-edit";
        }
        int result = companyDao.add(company);
        prepareModel(model, result, "added");
        return "company/company-list";
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
    public String postDelete(@ModelAttribute("id") String stringId, Model model) {
        int id = 0;
        try {
            id = Integer.parseInt(stringId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int result = companyDao.delete(id);
        prepareModel(model, result, "deleted");
        return "company/company-list";
    }

    private void prepareModel(Model model, int result, String action) {
        if (result == 0) {
            model.addAttribute("errorMessage", "No company " + action + ".");
        } else {
            model.addAttribute("message", "Company " + action + ".");
        }
        List<Company> companyList = companyDao.findAll();
        model.addAttribute("companyList", companyList);
    }
}
