package com.hajres.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import com.hajres.config.Const;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.SortOrder;
import com.hajres.news.service.RestNewsService;
import com.hajres.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdministrationController {

    private UserService userService;
    private RestNewsService restNewsService;

    private Map<String, String> categories;
    private Map<String, String> languages;
    private Map<String, String> sortOrders;
    private List<Country> countries;

    @Autowired
    public AdministrationController(@Qualifier("userServiceImpl") UserService userService, RestNewsService restNewsService) {
        this.userService = userService;
        this.restNewsService = restNewsService;

        this.categories = restNewsService.getCategories();
        this.languages = restNewsService.getLanguages();
        this.sortOrders = restNewsService.getSortOrders();
        this.countries = restNewsService.getCountries();
    }

    private Logger logger = Logger.getLogger(getClass().getName());

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/panel")
    public String showAdminPanel() {
        return "admin/panel";
    }

    @GetMapping("/sort-order-list")
    public String showSortOrderList(@RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) int page,
                                    @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) int pageSize,
                                    Model model) {
        List<SortOrder> list = restNewsService.getSortOrderList();

        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("type", "sort-order");
        model.addAttribute("pageTitle", "Sort Order List");
        model.addAttribute("columnOne", "Parameter name");
        model.addAttribute("columnTwo", "Display name");

        return "admin/two-column-list-view";
    }

    @GetMapping("/sort-order/edit/{id}")
    public String editSortOrder(@PathVariable String id,
                                Model model) {
        SortOrder sortOrder = restNewsService.getSortOrder(id);

        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("pageTitle", "Edit Sort Order");
        model.addAttribute("action", "sort-order/save");

        return "admin/add-edit-model";
    }

    @GetMapping("/sort-order/add")
    public String getNewSortOrder(Model model) {
        SortOrder sortOrder = new SortOrder();
        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("pageTitle", "Edit Sort Order");
        model.addAttribute("action", "sort-order/save");
        return "admin/add-edit-model";
    }

    @PostMapping("/sort-order/save")
    public String postEditSortOrder(@Valid @ModelAttribute SortOrder sortOrder,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-edit-model";
        }
        restNewsService.saveSortOrder(sortOrder);
        return "redirect: ../sort-order-list";
    }

    @GetMapping("/sort-order/delete/{id}")
    public String getDeleteSortOrder(@PathVariable String id,
                                     Model model) {
        SortOrder sortOrder = restNewsService.getSortOrder(id);
        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("type", "sort-order");
        return "admin/delete-model";
    }

    @PostMapping("/sort-order/delete/{id}")
    public String postDeleteSortOrder(@PathVariable String id) {
        restNewsService.deleteSortOrder(id);
        return "redirect: ../../sort-order-list";
    }
}
