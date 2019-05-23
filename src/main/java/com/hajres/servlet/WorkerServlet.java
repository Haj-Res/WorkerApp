package com.hajres.servlet;

import com.hajres.domain.dao.CompanyDao;
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
    private CompanyDao companyDao;

    public void init() {
        workerDao = new WorkerDao();
        companyDao = new CompanyDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        PrintWriter out = resp.getWriter();
        try {
            switch (action) {
                case "/worker":
                    listWorker(req, resp);
                    break;
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
        String filter = req.getParameter("filter");
        List<Worker> workerList;
        if (filter == null) {
            workerList = workerDao.findAll();
        } else {
            workerList = workerDao.findByName(filter);
            req.setAttribute("filter", filter);

        }
        req.setAttribute("workerList", workerList);
        req.getRequestDispatcher("worker/worker-list.jsp").forward(req, resp);

    }

    private void addWorker(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String add = req.getParameter("add");
        req.setAttribute("action", "new?add=true");
        if (add == null) {
            List<Company> companyList = companyDao.findAll();
            req.setAttribute("companyList", companyList);
            req.getRequestDispatcher("worker-add-edit.jsp").forward(req, resp);
        } else {
            Worker worker = getWorkerData(req);
            if (worker != null) {
                workerDao.add(worker);
                req.setAttribute("message", "Worker added to database.");
            } else {
                req.setAttribute("errorMessage", "Worker not added to database.");
            }
            req.getRequestDispatcher("/worker").forward(req, resp);
        }
    }

    private void editWorker(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String jmbg = req.getParameter("jmbg");
        if (jmbg == null) {
            listWorker(req, resp);
        } else {
            if (req.getParameter("firstName") == null) {
                Worker worker = workerDao.findById(jmbg);
                List<Company> companyList = companyDao.findAll();
                req.setAttribute("worker", worker);
                req.setAttribute("companyList", companyList);
                req.setAttribute("action", "edit?jmbg=" + worker.getJmbg());
                RequestDispatcher dispatcher = req.getRequestDispatcher("worker-add-edit.jsp");
                dispatcher.forward(req, resp);
            } else {
                Worker worker = getWorkerData(req);
                if (worker != null) {
                    String oldJmbg = req.getParameter("jmbg");
                    workerDao.update(worker, oldJmbg);
                    req.setAttribute("message", "Worker updated!");
                } else {
                    req.setAttribute("errorMessage", "No worker was updated.");
                }
                req.getRequestDispatcher("/worker").forward(req, resp);
            }
        }
    }

    private void deleteWorker(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String jmbg = request.getParameter("jmbg");
        String confirmed = request.getParameter("confirm");
        if (confirmed == null || !confirmed.equals("true")) {
            Worker worker = workerDao.findById(jmbg);
            request.setAttribute("worker", worker);
            request.getRequestDispatcher("worker-delete.jsp").forward(request, response);
        } else {
            workerDao.delete(jmbg);
            request.setAttribute("message", "Worker deleted.");
            request.getRequestDispatcher("/worker").forward(request, response);
        }
    }

    private Worker getWorkerData(HttpServletRequest req) {
        Worker worker = new Worker();
        worker.setJmbg(req.getParameter("newJmbg").trim());
        if (worker.getJmbg().equals("") || worker.getJmbg().length() > 13) return null;
        worker.setFirstName(req.getParameter("firstName").trim());
        if (worker.getFirstName().equals("")) return null;
        worker.setLastName(req.getParameter("lastName").trim());
        if (worker.getLastName().equals("")) return null;
        worker.setBirthDate(LocalDate.parse(req.getParameter("birthDate").trim()));
        if (worker.getBirthDate() == null) return null;

        Address address = new Address();
        address.setCity(req.getParameter("city").trim());
        if (address.getCity().equals("")) return null;
        address.setStreet(req.getParameter("street").trim());
        if (address.getStreet().equals("")) return null;
        address.setNumber(req.getParameter("number").trim());
        if (address.getNumber().equals("")) return null;
        worker.setAddress(address);

        Company company = new Company();
        company.setName(req.getParameter("company").trim());
        if (!address.getNumber().equals("")) {
            address = new Address();
            address.setCity(req.getParameter("company-city").trim());
            if (address.getCity().equals("")) return null;
            address.setStreet(req.getParameter("company-street").trim());
            if (address.getStreet().equals("")) return null;
            address.setNumber(req.getParameter("company-number").trim());
            if (address.getNumber().equals("")) return null;
            company.setAddress(address);
        } else {
            company = null;
        }

        worker.setCompany(company);

        return worker;
    }
}
