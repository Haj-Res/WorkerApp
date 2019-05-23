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
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Company
        </h1>
    </div>
    <%@include file="../navigation.jsp" %>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2>Company List</h2>
        </div>
        <div class="d-flex justify-content-start pl-2 pb-0 mb-0">
            <form class="form-inline" method="get" action="${pageContext.request.contextPath}/company">
                <label class="sr-only" for="filter">Name</label>
                <input class="form-control mr-sm-2" type="text" name="filter" id="filter" placeholder="Filter"/>
                <button type="submit" class="btn btn-secondary">Filter</button>
            </form>
        </div>
        <div class="d-flex justify-content-center p-2 pt-0 mt-0">
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
