<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div class="container d-flex flex-column">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Company
        </h1>
    </div>
    <div class="d-flex justify-content-center p-2 btn-group btn-group-lg" role="group">
        <h4>
            <ul class="list-group list-group-horizontal">
                <a href="${pageContext.request.contextPath}/">
                    <li class="list-group-item">Main Menu</li>
                </a>
                <a href="${pageContext.request.contextPath}/company/new">
                    <li class="list-group-item">Add New Company</li>
                </a>
                <a href="${pageContext.request.contextPath}/company">
                    <li class="list-group-item">List All Companies
                </a>
            </ul>
        </h4>
    </div>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2>Company List</h2>
        </div>
        <div class="d-flex justify-content-center p-2">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>Company name</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="companyList" type="java.util.List<com.hajres.domain.model.Company>"--%>
                <c:forEach var="company" items="${companyList}">
                    <tr>
                        <td><c:out value="${company.name}"/></td>
                        <td><c:out
                                value="${company.address.street} ${company.address.number}, ${company.address.city}"/></td>
                        <td>
                            <a class="mr-4"
                               href="${pageContext.request.contextPath}/company/edit?id=${company.idCompany}">Edit</a>
                            <a href="${pageContext.request.contextPath}/company/delete?id=${company.idCompany}">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
