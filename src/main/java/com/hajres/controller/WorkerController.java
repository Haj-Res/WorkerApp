package com.hajres.controller;

import com.hajres.PaginatedResult;
import com.hajres.config.Const;
import com.hajres.domain.entity.Worker;

import com.hajres.service.WorkerService;
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
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @GetMapping("/list")
    public String showWorkerList(@ModelAttribute(Const.FILTER_PARAM_NAME) String filter,
                                 @RequestParam(value = Const.PAGE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_FIRST_PAGE_STRING) int page,
                                 @RequestParam(value = Const.PAGE_SIZE_PARAM_NAME, required = false, defaultValue = Const.DEFAULT_PAGE_SIZE_STRING) int pageSize,
                                 Model model) {
        PaginatedResult<Worker> paginatedResult;
        if (pageSize > Const.MAX_PAGE_SIZE) {
            pageSize = Const.DEFAULT_PAGE_SIZE;
        }
        if (filter == null) {
            paginatedResult = workerService.getPaginatedWorkerList(page, pageSize);
        } else {
            paginatedResult = workerService.getPaginatedWorkerList(page, pageSize, filter);
        }
        model.addAttribute("workerList", paginatedResult.getResultList());
        model.addAttribute(Const.PAGE_COUNT_PARAM_NAME, paginatedResult.getPageCount());
        model.addAttribute(Const.PAGE_PARAM_NAME, page);
        model.addAttribute(Const.PAGE_SIZE_PARAM_NAME, pageSize);
        return "worker/worker-list";
    }

    @GetMapping("/edit")
    public String getEdit(@RequestParam("workerId") int workerId,
                          Model model) {
        Worker worker = workerService.getWorker(workerId);
        model.addAttribute("worker", worker);
        return "worker/worker-add-edit";
    }

    @PostMapping("/save")
    public String postEdit(@Valid @ModelAttribute("worker") Worker worker,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "worker/worker-add-edit";
        }
        System.out.println(worker);
        workerService.saveWorker(worker);
        return "redirect:/worker/list";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        return "worker/worker-add-edit";
    }

    @GetMapping("/delete")
    public String getDelete(@RequestParam("workerId") int workerId,
                            Model model) {
        Worker worker = workerService.getWorker(workerId);
        model.addAttribute("worker", worker);
        return "worker/worker-delete";
    }

    @PostMapping("/delete")
    public String postDelete(@RequestParam("workerId") int workerId) {
        workerService.deleteWorker(workerId);
        return "redirect:/worker/list";
    }
}
