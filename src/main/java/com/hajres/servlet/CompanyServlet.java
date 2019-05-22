package com.hajres.servlet;

import com.hajres.domain.dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
                System.out.println("In /company path");
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
}
