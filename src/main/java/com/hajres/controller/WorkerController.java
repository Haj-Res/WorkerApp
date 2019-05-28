package com.hajres.controller;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    private WorkerDao workerDao;

    public WorkerController() {
        workerDao = new WorkerDao();
    }

    @GetMapping("/list")
    public String showWorkerList(@ModelAttribute("filter") String filter, Model model) {
        List<Worker> workerList;
        if (filter.equals("")) {
            workerList = workerDao.findAll();
        } else {
            workerList = workerDao.findByName(filter);
        }
        model.addAttribute("workerList", workerList);
        return "worker/worker-list";
    }

    @GetMapping("/edit")
    public String getEdit(@ModelAttribute("workerJmbg") String workerJmbg, Model model) {
        Worker worker = workerDao.findById(workerJmbg);
        model.addAttribute("worker", worker);
        String formAction = "?workerJmbg=" + workerJmbg;
        model.addAttribute("formAction", formAction);
        return "worker/worker-add-edit";
    }

    @PostMapping("/edit")
    public ModelAndView postEdit(@ModelAttribute("worker") Worker worker,
                                 @ModelAttribute("workerJmbg") String workerJmbg,
                                 ModelMap model) {
        int result = workerDao.update(worker, workerJmbg);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker added.");
        } else {
            model.addAttribute("message", "Worker added.");
        }
        model = prepareModel(model);
        return new ModelAndView("worker/worker-list", model);
    }

    @GetMapping("/add")
    public String getAdd(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker", worker);
        return "worker/worker-add-edit";
    }

    @PostMapping("/add")
    public ModelAndView postAdd(@ModelAttribute("worker") Worker worker, ModelMap model) {
        int result = workerDao.add(worker);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker added.");
        } else {
            model.addAttribute("message", "Worker added.");
        }
        model = prepareModel(model);
        return new ModelAndView("worker/worker-list", model);
    }

    @GetMapping("/delete")
    public String getDelete(@ModelAttribute("workerJmbg") String workerJmbg,
                            Model model) {
        Worker worker = workerDao.findById(workerJmbg);
        model.addAttribute("worker", worker);
        return "worker/worker-delete";
    }

    @PostMapping("/delete")
    public ModelAndView postDelete(@ModelAttribute("workerJmbg") String workerJmbg,
                                   ModelMap model) {
        int result = workerDao.delete(workerJmbg);
        if (result == 0) {
            model.addAttribute("errorMessage", "No worker deleted.");
        } else {
            model.addAttribute("message", "Worker deleted");
        }
        model = prepareModel(model);
        return new ModelAndView("worker/worker-list", model);
    }

    private ModelMap prepareModel(ModelMap model) {
        List<Worker> workerList = workerDao.findAll();
        model.addAttribute("workerList", workerList);
        return model;
    }
}
