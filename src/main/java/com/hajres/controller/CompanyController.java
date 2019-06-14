package com.hajres.controller;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
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
    public String showCompanyList(@ModelAttribute(Const.FILTER_PARAM_NAME) String filter,
                                  @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) int page,
                                  @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) int pageSize,
                                  Model model) {
        PaginatedResult<Company> paginatedResults;
        if (pageSize > Const.MAX_PAGE_SIZE) {
            pageSize = Const.DEFAULT_PAGE_SIZE;
        }
        if (filter == null) {
            paginatedResults = companyService.getPaginatedCompanyList(page, pageSize);
        } else {
            paginatedResults = companyService.getPaginatedCompanyList(page, pageSize, filter);
        }
        model.addAttribute("companyList", paginatedResults.getResultList());
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, paginatedResults.getPageCount());
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        return "company/company-list";
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam("companyId") int id, Model model) {
        Company company = companyService.getCompany(id);
        model.addAttribute("company", company);
        return "company/company-add-edit";
    }

    @PostMapping("/save")
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
