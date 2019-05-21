package com.hajres.servlet;

import com.hajres.domain.dao.WorkerDao;
import com.hajres.domain.model.Worker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class WorkerServlet extends HttpServlet {

    private WorkerDao workerDao;

    public void init() {
        workerDao = new WorkerDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        PrintWriter out = response.getWriter();
        try {
            switch (action) {
                case "/worker":
                case "/worker/list":
                    listUser(request, response);
                    break;
                case "/old/test":
                    out.println("Selected old/test");
                    break;
                case "/old":
                    out.println("Selected old");
                    break;
                default:
                    listUser(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Worker> listWorker = workerDao.findAll();
        request.setAttribute("workerList", listWorker);
        RequestDispatcher dispatcher = request.getRequestDispatcher("worker-list.jsp");
        dispatcher.forward(request, response);
    }
}
