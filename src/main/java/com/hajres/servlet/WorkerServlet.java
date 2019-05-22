package com.hajres.servlet;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import com.hajres.domain.model.Worker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = {"/worker", "/worker/new", "/worker/edit", "/worker/delete"})
public class WorkerServlet extends HttpServlet {
    private WorkerDao workerDao;

    public void init() {
        workerDao = new WorkerDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        PrintWriter out = resp.getWriter();
        try {
            switch (action) {
                case "/worker/new":
                    addWorker(req, resp);
                    break;
                case "/worker/edit":
                    editWorker(req, resp);
                    break;
                case "/worker/delete":
                    deleteWorker(req, resp);
                    break;
                default:
                    resp.sendError(405, "Wrong method used.");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        PrintWriter out = resp.getWriter();
        System.out.println(action);
        try {
            switch (action) {
                case "/worker/new":
                    addWorker(req, resp);
                    break;
                case "/worker/edit":
                    editWorker(req, resp);
                    break;
                case "/worker/delete":
                    deleteWorker(req, resp);
                    break;
                case "/worker":
                default:
                    listWorker(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        PrintWriter out = resp.getWriter();
        try {
            switch (action) {
                case "/worker/edit":
                    out.println("Selected edit (put)");
                    break;
                default:
                    resp.sendError(405, "Wrong method used.");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    private void listWorker(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        List<Worker> workerList = workerDao.findAll();
        req.setAttribute("workerList", workerList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("worker/worker-list.jsp");
        System.out.println(req.getContextPath());
        System.out.println(req.getServletPath());
        dispatcher.forward(req, resp);
    }

    private void addWorker(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String add = req.getParameter("add");
        req.setAttribute("action", "new?add=true");
        if (add == null) {
            req.getRequestDispatcher("worker-add-edit.jsp").forward(req, resp);
        } else {
            Worker worker = getWorkerData(req);
            workerDao.add(worker);
            resp.sendRedirect(req.getContextPath() + "/worker");
        }
    }

    private void editWorker(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String jmbg = req.getParameter("jmbg");
        if (jmbg == null) {
            listWorker(req, resp);
        } else {
            System.out.println(req.getContextPath());
            System.out.println(req.getServletPath());
            if (req.getParameter("firstName") == null) {
                Worker worker = workerDao.findById(jmbg);
                req.setAttribute("worker", worker);
                req.setAttribute("action", "edit?jmbg=" + worker.getJmbg());
                RequestDispatcher dispatcher = req.getRequestDispatcher("worker-add-edit.jsp");
                dispatcher.forward(req, resp);
            } else {
                Worker worker = getWorkerData(req);

                String oldJmbg = req.getParameter("jmbg");
                workerDao.update(worker, oldJmbg);
                resp.sendRedirect(req.getContextPath() + "/worker");
            }
        }
    }

    private void deleteWorker(HttpServletRequest request, HttpServletResponse response)
        throws  IOException, ServletException {
        String jmbg = request.getParameter("jmbg");
        String confirmed = request.getParameter("confirm");
        if (confirmed == null || !confirmed.equals("true")) {
            Worker worker = workerDao.findById(jmbg);
            request.setAttribute("worker", worker);
            request.getRequestDispatcher("worker-delete.jsp").forward(request, response);
        } else {
            workerDao.delete(jmbg);
            response.sendRedirect(request.getContextPath() + "/worker");
        }
    }

    private Worker getWorkerData(HttpServletRequest req) {
        Worker worker = new Worker();
        worker.setJmbg(req.getParameter("newJmbg"));
        worker.setFirstName(req.getParameter("firstName"));
        worker.setLastName(req.getParameter("lastName"));
        worker.setBirthDate(LocalDate.parse(req.getParameter("birthDate")));

        Address address = new Address();
        address.setCity(req.getParameter("city"));
        address.setStreet(req.getParameter("street"));
        address.setNumber(req.getParameter("number"));
        worker.setAddress(address);

        Company company = new Company();
        company.setName(req.getParameter("company"));
        address = new Address();
        address.setCity(req.getParameter("company-city"));
        address.setStreet(req.getParameter("company-street"));
        address.setNumber(req.getParameter("company-number"));
        company.setAddress(address);

        worker.setCompany(company);
        return worker;
    }
}
