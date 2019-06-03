package com.hajres.controller;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

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
import java.util.List;

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
    public String showWorkerList(@ModelAttribute("filter") String filter,
                                 Model model) {
        List<Worker> workerList;
        if (filter == null) {
            workerList = workerService.getWorkerList();
        } else {
            workerList = workerService.getWorkerList(filter);
        }
        model.addAttribute("workerList", workerList);
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

    @PostMapping("/add")
    public String postAdd(@Valid @ModelAttribute("worker") Worker worker,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "worker/worker-add-edit";
        }
        workerService.saveWorker(worker);
        return "redirect:/worker/list";
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
