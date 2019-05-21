<%@ page import="com.hajres.domain.model.Worker" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
</head>
<body>
<center>
    <h1>
        Worker Management
    </h1>
    <h2>
        <a href="new">Add New Worker</a>
        <a href="worker">List All Worker</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Worker</h2></caption>
        <tr>
            <th>JMBG</th>
            <th>Name</th>
            <th>Birthdate</th>
            <th>Address</th>
            <th>Company</th>
            <th>Actions</th>
        </tr>

        <% List<Worker> list = (List<Worker>) request.getAttribute("workerList");
        for (Worker worker : list) {
            out.println("<tr>");
            out.println("<td>" + worker.getJmbg() + "</td>");
            out.println("<td>" + worker.getFirstName() + " " + worker.getLastName() + "</td>");
            out.println("<td>" + worker.getBirthDate() + "</td>");
            out.print("<td>" + worker.getAddress().getStreet() + " ");
            out.print(worker.getAddress().getNumber() + ", " + worker.getAddress().getCity() +"</td>");
            out.println("<td>" + worker.getCompany().getName() + "</td>");
            out.print("<td><a href=\"edit?jmbg=" + worker.getJmbg()+"\" /> Edit</a>");
            out.print("&nbsp;&nbsp;&nbsp;");
            out.println("<a href=\"delete?jmbg="+worker.getJmbg()+"\" />Delete</a></td>");
            out.println("</tr>");
        }
        %>



    </table>
</div>

</body>
</html>
