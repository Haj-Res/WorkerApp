package com.hajres.servlet;

import com.hajres.domain.dao.CompanyDao;
import com.hajres.domain.model.Company;
import sun.nio.fs.LinuxFileSystemProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/company", "/company/new", "/company/edit", "/company/delete"})
public class CompanyServlet extends HttpServlet {
    CompanyDao companyDao;

    public void init() {
        companyDao = new CompanyDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                System.out.println("In /company/edit");
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
}
