package com.hajres.servlet;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/company", "/company/new", "/company/edit", "/company/delete"})
public class CompanyServlet extends HttpServlet {
    private CompanyDao companyDao;

    public void init() {
        companyDao = new CompanyDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/company":
                listCompanies(request, response);
                break;
            case "/company/new":
                addCompany(request, response);
                break;
            case "/company/edit":
                updateCompany(request, response);
                break;
            case "/company/delete":
                System.out.println("In /company/delete");
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/company":
                listCompanies(request, response);
                break;
            case "/company/new":
                addCompany(request, response);
                break;
            case "/company/edit":
                updateCompany(request, response);
                break;
            case "/company/delete":
                deleteCompany(request, response);
                break;
        }
    }

    private void listCompanies(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Company> companyList;
        String filter = request.getParameter("filter");
        if (filter == null) {
            companyList = companyDao.findAll();
        } else {
            companyList = companyDao.findByAll(filter);
            request.setAttribute("filter", filter);
        }
        request.setAttribute("companyList", companyList);
        request.getRequestDispatcher("/company/company-list.jsp").forward(request, response);
    }

    private void updateCompany(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String stringId = request.getParameter("id");
        String name = request.getParameter("name");
        if (name == null) {
            int id = 0;
            try {
                id = Integer.parseInt(stringId);
            } catch (Exception e) {
                response.sendRedirect("/company");
            }
            Company company = companyDao.findById(id);
            request.setAttribute("action", "edit?id=" + id);
            request.setAttribute("company", company);
            request.getRequestDispatcher("company-add-edit.jsp").forward(request, response);
        } else {
            Company company = getCompanyData(request);
            if (company != null) {
                companyDao.update(company);
                request.setAttribute("message", "Company updated.");

            } else {
                request.setAttribute("errorMessage", "No company not updated.");
            }
            request.getRequestDispatcher("/company").forward(request, response);
        }
    }

    private void addCompany(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String add = request.getParameter("add");
        if (add == null) {
            request.setAttribute("action", "new?add=true");
            request.getRequestDispatcher("company-add-edit.jsp").forward(request, response);
        } else {
            Company company = getCompanyData(request);
            if (company != null) {
                companyDao.add(company);
                request.setAttribute("message", "Company added to database.");
            } else {
                request.setAttribute("errorMessage", "Company not added to database.");
            }
            request.getRequestDispatcher("/company").forward(request, response);
        }
    }

    private void deleteCompany(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String companyId = request.getParameter("id");
        String confirm = request.getParameter("confirm");
        int id = 0;
        try {
            id = Integer.parseInt(companyId);
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/company");
        }
        if (confirm == null || !confirm.equals("true")) {
            Company company = companyDao.findById(id);
            request.setAttribute("company", company);
            request.getRequestDispatcher("company-delete.jsp").forward(request, response);
        } else {
            companyDao.delete(id);
            request.setAttribute("message", "Company deleted.");
            request.getRequestDispatcher("/company").forward(request, response);
        }
    }

    private Company getCompanyData(HttpServletRequest request) {
        Company company = new Company();
        int id = 0;
        String stringId = request.getParameter("id");
        try {
            id = Integer.parseInt(stringId);
        } catch (Exception e) {
            id = 0;
        }
        company.setIdCompany(id);
        company.setName(request.getParameter("name"));
        if (company.getName().equals("")) return null;

        Address address = new Address();
        address.setCity(request.getParameter("city"));
        if (address.getCity().equals("")) return null;

        address.setStreet(request.getParameter("street"));
        if (address.getStreet().equals("")) return null;

        address.setNumber(request.getParameter("number"));
        if (address.getNumber().equals("")) return null;

        company.setAddress(address);
        System.out.println(company);
        return company;
    }
}
