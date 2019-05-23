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
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <%@include file="../navigation.jsp" %>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2>Worker list</h2>
        </div>
        <div class="d-flex justify-content-start pl-2">
            <form class="form-inline" method="get" action="${pageContext.request.contextPath}/worker">
                <label class="sr-only" for="filter">Name</label>
                <input class="form-control mr-sm-2" type="text" name="filter" id="filter" placeholder="Filter"/>
                <button type="submit" class="btn btn-secondary">Filter</button>
            </form>
        </div>
        <div class="p-2 pt-0 d-flex justify-content-center">
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
                        <td><c:out
                                value="${worker.address.street} ${worker.address.number}, ${worker.address.city}"/></td>
                        <td><c:out value="${worker.company.name}"/></td>
                        <td>
                            <a class="mr-4"
                               href="${pageContext.request.contextPath}/worker/edit?jmbg=<c:out value="${worker.jmbg}"/>">Edit</a>

                            <a href="${pageContext.request.contextPath}/worker/delete?jmbg=<c:out value="${worker.jmbg}"/>">Delete</a>

                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
