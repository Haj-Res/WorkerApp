package com.hajres.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.domain.dto.UserDisplayDTO;
import com.hajres.domain.entity.User;
import com.hajres.domain.entity.news.Country;
import com.hajres.domain.entity.news.Language;
import com.hajres.domain.entity.news.NewsCategory;
import com.hajres.domain.entity.news.SortOrder;
import com.hajres.service.NewsParameterService;

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

    private NewsParameterService newsParameterService;
    private UserService userService;

    @Autowired
    public AdministrationController(@Qualifier("userServiceImpl") UserService userService,
                                    NewsParameterService newsParameterService) {
        this.userService = userService;
        this.newsParameterService = newsParameterService;
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
    public String showSortOrderList(Model model) {
        List<SortOrder> list = newsParameterService.getSortOrderList();
        model.addAttribute("list", list);
        model.addAttribute("type", "sort-order");
        model.addAttribute("pageTitle", "Sort Order List");
        return "admin/two-column-list-view";
    }

    @GetMapping("/sort-order/edit/{id}")
    public String editSortOrder(@PathVariable String id,
                                Model model) {
        SortOrder sortOrder = newsParameterService.getSortOrder(id);
        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("type", "sort-order");
        model.addAttribute("pageTitle", "Edit Sort Order");
        model.addAttribute("action", "sort-order/save");
        return "admin/add-edit-model";
    }

    @GetMapping("/sort-order/add")
    public String getNewSortOrder(Model model) {
        SortOrder sortOrder = new SortOrder();
        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("type", "sort-order");
        model.addAttribute("pageTitle", "Edit Sort Order");
        model.addAttribute("action", "sort-order/save");
        return "admin/add-edit-model";
    }

    @PostMapping("/sort-order/save")
    public String postEditSortOrder(@RequestParam("oldId") String oldId,
                                    @Valid @ModelAttribute("dataModel") SortOrder sortOrder,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-edit-model";
        }
        if (oldId == null || oldId.equals(sortOrder.getId())) {
            newsParameterService.saveSortOrder(sortOrder);
        } else {
            newsParameterService.updateSortOrder(sortOrder, oldId);
        }
        return "redirect: ../sort-order-list";
    }

    @GetMapping("/sort-order/delete/{id}")
    public String getDeleteSortOrder(@PathVariable String id,
                                     Model model) {
        SortOrder sortOrder = newsParameterService.getSortOrder(id);
        model.addAttribute("dataModel", sortOrder);
        model.addAttribute("type", "sort-order");
        return "admin/delete-model";
    }

    @PostMapping("/sort-order/delete/{id}")
    public String postDeleteSortOrder(@PathVariable String id) {
        newsParameterService.deleteSortOrder(id);
        return "redirect: ../../sort-order-list";
    }

    @GetMapping("/language-list")
    public String showLanguageList(Model model) {
        List<Language> list = newsParameterService.getLanguageList();
        model.addAttribute("list", list);
        model.addAttribute("type", "language");
        model.addAttribute("pageTitle", "Language List");
        return "admin/two-column-list-view";
    }

    @GetMapping("/language/edit/{id}")
    public String getEditLanguage(@PathVariable String id,
                                  Model model) {
        Language language = newsParameterService.getLanguageById(id);
        model.addAttribute("dataModel", language);
        model.addAttribute("type", "language");
        model.addAttribute("pageTitle", "Edit Language");
        model.addAttribute("action", "language/save");
        return "admin/add-edit-model";
    }

    @GetMapping("/language/add")
    public String getAddLanguage(Model model) {
        Language language = new Language();
        model.addAttribute("dataModel", language);
        model.addAttribute("type", "language");
        model.addAttribute("pageTitle", "Add New Language");
        model.addAttribute("action", "language/save");
        return "admin/add-edit-model";
    }

    @PostMapping("/language/save")
    public String postSaveLanguage(@RequestParam("oldId") String oldId,
                                   @Valid @ModelAttribute("dataModel") Language language,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-edit-model";
        }
        if (oldId == null || oldId.equals(language.getId())) {
            newsParameterService.saveLanguage(language);
        } else {
            newsParameterService.updateLanguage(language, oldId);
        }
        return "redirect: ../language-list";
    }

    @GetMapping("/language/delete/{id}")
    public String getDeleteLanguage(@PathVariable String id,
                                    Model model) {
        Language language = newsParameterService.getLanguageById(id);
        model.addAttribute("dataModel", language);
        model.addAttribute("type", "language");
        return "admin/delete-model";
    }

    @PostMapping("/language/delete/{id}")
    public String postDeleteLanguage(@PathVariable String id) {
        newsParameterService.deleteLanguage(id);
        return "redirect: ../../language-list";
    }

    @GetMapping("/category-list")
    public String showCategoryList(Model model) {
        List<NewsCategory> list = newsParameterService.getNewsCategory();
        model.addAttribute("list", list);
        model.addAttribute("type", "category");
        model.addAttribute("pageTitle", "News Category List");
        return "admin/two-column-list-view";
    }

    @GetMapping("/category/edit/{id}")
    public String getEditCategory(@PathVariable String id,
                                  Model model) {
        NewsCategory category = newsParameterService.getNewsCategoryById(id);
        model.addAttribute("dataModel", category);
        model.addAttribute("type", "category");
        model.addAttribute("pageTitle", "Edit News Category");
        model.addAttribute("action", "category/save");
        return "admin/add-edit-model";
    }

    @GetMapping("/category/add")
    public String getAddCategory(Model model) {
        NewsCategory category = new NewsCategory();
        model.addAttribute("dataModel", category);
        model.addAttribute("type", "category");
        model.addAttribute("pageTitle", "Add New News Category");
        model.addAttribute("action", "category/save");
        return "admin/add-edit-model";
    }

    @PostMapping("/category/save")
    public String postSaveCategory(@RequestParam("oldId") String oldId,
                                   @Valid @ModelAttribute("dataModel") NewsCategory category,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-edit-model";
        }
        if (oldId == null || oldId.equals(category.getId())) {
            newsParameterService.saveNewsCategory(category);
        } else {
            newsParameterService.updateNewsCategory(category, oldId);
        }
        return "redirect: ../category-list";
    }

    @GetMapping("/category/delete/{id}")
    public String getDeleteCategory(@PathVariable String id,
                                    Model model) {
        NewsCategory category = newsParameterService.getNewsCategoryById(id);
        model.addAttribute("dataModel", category);
        model.addAttribute("type", "category");
        return "admin/delete-model";
    }

    @PostMapping("/category/delete/{id}")
    public String postDeleteCategory(@PathVariable String id) {
        newsParameterService.deleteNewsCategory(id);
        return "redirect: ../../category-list";
    }

    @GetMapping("/country-list")
    public String showCountryList(Model model) {
        List<Country> list = newsParameterService.getCountries();
        model.addAttribute("list", list);
        return "admin/country-list";
    }

    @GetMapping("/country/edit/{id}")
    public String getEditCountry(@PathVariable String id,
                                 Model model) {
        Country country = newsParameterService.getCountryById(id);
        model.addAttribute("dataModel", country);
        model.addAttribute("pageTitle", "Edit Country");
        return "admin/add-edit-country";
    }

    @GetMapping("/country/add")
    public String getAddCountry(Model model) {
        Country country = new Country();
        model.addAttribute("dataModel", country);
        model.addAttribute("pageTitle", "Add New Country");
        return "admin/add-edit-country";
    }

    @PostMapping("/country/save")
    public String postSaveCountry(@RequestParam("oldId") String oldId,
                                  @Valid @ModelAttribute("dataModel") Country country,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-edit-country";
        }
        if (oldId == null || oldId.equals(country.getCountryId())) {
            newsParameterService.saveCountry(country);
        } else {
            newsParameterService.updateCountry(country, oldId);
        }
        return "redirect: ../country-list";
    }

    @GetMapping("/country/delete/{id}")
    public String getDeleteCountry(@PathVariable String id,
                                   Model model) {
        Country country = newsParameterService.getCountryById(id);
        model.addAttribute("dataModel", country);
        return "admin/delete-country";
    }

    @PostMapping("/country/delete/{id}")
    public String postDeleteCountry(@PathVariable String id) {
        newsParameterService.deleteCountry(id);
        return "redirect: ../../country-list";
    }

    @GetMapping("/user")
    public String showUserList(@RequestParam(name = Const.PAGE_SIZE_PARAM_NAME, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING, required = false) int pageSize,
                               @RequestParam(name = Const.PAGE_PARAM_NAME, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING, required = false) int page,
                               Model model) {
        PaginatedResult<UserDisplayDTO> users = userService.findAllPaginatedUser(pageSize, page);
        model.addAttribute("users", users.getResultList());
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, users.getPageCount());
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);

        return "admin/user-list";
    }
}