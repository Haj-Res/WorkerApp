<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="/resources/bootstrap/css/bootstrap.min.css" %>
    </style>

</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <%@include file="../shared/alerts.jsp" %>
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Company
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2>Company List</h2>
        </div>
        <div class="d-flex flex-row justify-content-between px-2 py-0">
            <form class="align-self-start form-inline" method="get"
                  action="${pageContext.request.contextPath}/company/list">
                <div class="input-group">
                    <input type="text" class="form-control form-control-sm" name="filter" id="filter" value="${filter}"
                           placeholder="Filter"
                           aria-label="Filter" aria-describedby="Filter box">
                    <div class="input-group-append">
                        <button class="btn btn-sm btn-secondary" type="submit">Go!</button>
                    </div>
                </div>
            </form>
            <div class="align-self-end">
                <a href="${pageContext.request.contextPath}/company/add" class="btn btn-success">New Company</a>
            </div>
        </div>
        <div class="d-flex justify-content-center p-2 pt-0 mt-0">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>#</th>
                    <th>Company name</th>
                    <th>Address</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <%--@elvariable id="companyList" type="java.util.List<com.hajres.domain.model.Company>"--%>
                <c:forEach var="company" items="${companyList}" varStatus="loop">
                    <c:url value="/company/edit" var="editCompany">
                        <c:param name="companyId" value="${company.idCompany}"/>
                    </c:url>
                    <c:url value="/company/delete" var="deleteCompany">
                        <c:param name="companyId" value="${company.idCompany}"/>
                    </c:url>
                    <tr>
                        <td><c:out value="${loop.index + 1}."/></td>
                        <td><c:out value="${company.name}"/></td>
                        <td><c:out
                                value="${company.address.street} ${company.address.number}, ${company.address.city}"/></td>
                        <td>
                            <a class="mr-4"
                               href="${editCompany}">Edit</a>
                            <a href="${deleteCompany}">Delete</a>
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
