package com.hajres.controller;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Company;

import com.hajres.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @RequestMapping("/list")
    public String showCompanyList(@ModelAttribute("filter") String filter,
                                  Model model) {
        List<Company> companyList;
        if (filter == null) {
            companyList = companyService.getCompanyList();
        } else {
            companyList = companyService.getCompanyList(filter);
        }
        model.addAttribute("companyList", companyList);
        return "company/company-list";
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam("companyId") int id, Model model) {
        Company company = companyService.getCompany(id);
        model.addAttribute("company", company);
        return "company/company-add-edit";
    }

    @PostMapping("/edit")
    public String postEdit(@Valid @ModelAttribute("company") Company company,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company/company-add-edit";
        }
        companyService.saveCompany(company);

        return "redirect:/company/list";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Company company = new Company();
        model.addAttribute("company", company);
        return "company/company-add-edit";
    }

    @PostMapping("/add")
    public String postAdd(@Valid @ModelAttribute("company") Company company,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "company/company-add-edit";
        }
        companyService.saveCompany(company);
        return "redirect:/company/list";
    }

    @GetMapping("/delete")
    public String getDelete(@RequestParam("companyId") int id, Model model) {
        Company company = companyService.getCompany(id);
        model.addAttribute("company", company);
        return "company/company-delete";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("companyId") int id) {
        companyService.deleteCompany(id);
        return "redirect:/company/list";
    }
}
