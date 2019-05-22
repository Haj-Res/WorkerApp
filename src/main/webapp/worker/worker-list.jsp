<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.hajres.domain.model.Worker" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css"%>
    </style>
</head>
<body>
<div style="text-align: center;">
    <h1>
        Worker Management
    </h1>
    <h4>
        <a href="${pageContext.request.contextPath}/worker/new">Add New Worker</a>
        <a href="${pageContext.request.contextPath}/worker">List All Worker</a>
    </h4>
</div>
<div class="container d-flex flex-column">
    <div class="d-flex justify-content-center">
        <h2>Worker list</h2>
    </div>
    <div class="p-2 d-flex justify-content-center">

        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>JMBG</th>
                <th>Name</th>
                <th>Birthday</th>
                <th>Address</th>
                <th>Company</th>
                <th>Actions</th>
            </tr>
            </thead>

            <%--@elvariable id="workerList" type="java.util.List<com.hajres.domain.model.Worker>"--%>
            <c:forEach var="worker" items="${workerList}">
                <tbody>
                <tr>
                    <td><c:out value="${worker.jmbg}"/></td>
                    <td><c:out value="${worker.firstName} ${worker.lastName}"/></td>
                    <td><c:out value="${worker.birthDate}"/></td>
                    <td><c:out value="${worker.address.street} ${worker.address.number}, ${worker.address.city}"/></td>
                    <td><c:out value="${worker.company.name}"/></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/worker/edit?jmbg=<c:out value="${worker.jmbg}"/>">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/worker/delete?jmbg=<c:out value="${worker.jmbg}"/>">Delete</a>

                    </td>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
