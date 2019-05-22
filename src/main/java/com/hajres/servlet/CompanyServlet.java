package com.hajres.servlet;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Address;
import com.hajres.domain.model.Company;
import sun.nio.fs.LinuxFileSystemProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

@WebServlet(urlPatterns = {"/company", "/company/new", "/company/edit", "/company/delete"})
public class CompanyServlet extends HttpServlet {
    CompanyDao companyDao;

    public void init() {
        companyDao = new CompanyDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action) {
            case "/company/new":
                System.out.println("In /company/new (post)");
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
                System.out.println("In /company/new");
                break;
            case "/company/edit":
                updateCompany(request, response);
                break;
            case "/company/delete":
                System.out.println("In /company/delete");
                break;
        }
    }

    private void listCompanies(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        List<Company> companyList = companyDao.findAll();
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
            companyDao.update(company);
            response.sendRedirect(request.getContextPath() + "/company");
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
        Address address = new Address();
        address.setCity(request.getParameter("city"));
        address.setStreet(request.getParameter("street"));
        address.setNumber(request.getParameter("number"));
        company.setAddress(address);
        System.out.println(company);
        return company;
    }
}
