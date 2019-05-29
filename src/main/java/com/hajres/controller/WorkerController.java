package com.hajres.controller;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

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
@RequestMapping("/worker")
public class WorkerController {
    private final WorkerDao workerDao;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, trimmerEditor);
    }

    @Autowired
    public WorkerController(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    @GetMapping("/list")
    public String showWorkerList(@ModelAttribute("filter") String filter,
                                 Model model) {
        List<Worker> workerList;
        if (filter == null) {
            workerList = workerDao.findAll();
        } else {
            workerList = workerDao.findByName(filter);
        }
        model.addAttribute("workerList", workerList);
        return "worker/worker-list";
    }

    @GetMapping("/edit")
    public String getEdit(@ModelAttribute("workerJmbg") String workerJmbg,
                          Model model) {
        Worker worker = workerDao.findById(workerJmbg);
        model.addAttribute("worker", worker);
        String formAction = "?workerJmbg=" + workerJmbg;
        model.addAttribute("formAction", formAction);
        return "worker/worker-add-edit";
    }

    @PostMapping("/edit")
    public String postEdit(@Valid @ModelAttribute("worker") Worker worker,
                           BindingResult bindingResult,
                           @ModelAttribute("workerJmbg") String workerJmbg,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "worker/worker-add-edit";
        }
        int result = workerDao.update(worker, workerJmbg);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker added.");
        } else {
            model.addAttribute("message", "Worker added.");
        }
        prepareModel(model);
        return "worker/worker-list";
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        return "worker/worker-add-edit";
    }

    @PostMapping("/add")
    public String postAdd(@Valid @ModelAttribute("worker") Worker worker,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "worker/worker-add-edit";
        }
        int result = workerDao.add(worker);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker added.");
        } else {
            model.addAttribute("message", "Worker added.");
        }
        prepareModel(model);
        return "worker/worker-list";
    }

    @GetMapping("/delete")
    public String getDelete(@ModelAttribute("workerJmbg") String workerJmbg,
                            Model model) {
        Worker worker = workerDao.findById(workerJmbg);
        model.addAttribute("worker", worker);
        return "worker/worker-delete";
    }

    @PostMapping("/delete")
    public String postDelete(@ModelAttribute("workerJmbg") String workerJmbg,
                                   Model model) {
        int result = workerDao.delete(workerJmbg);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker deleted.");
        } else {
            model.addAttribute("message", "Worker deleted");
        }
        prepareModel(model);
        return "worker/worker-list";
    }

    private void prepareModel(Model model) {
        List<Worker> workerList = workerDao.findAll();
        model.addAttribute("workerList", workerList);
    }
}
