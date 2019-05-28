package com.hajres.controller;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {
    private WorkerDao workerDao;

    public WorkerController() {
        workerDao = new WorkerDao();
    }
    @RequestMapping("/list")
    public String showWorkerList(@ModelAttribute("filter") String filter, Model m) {
        List<Worker> workerList;
        if (filter.equals("")) {
            workerList = workerDao.findAll();
        } else {
            workerList = workerDao.findByName(filter);
        }
        m.addAttribute("workerList", workerList);
        return "worker/worker-list";
    }
}
