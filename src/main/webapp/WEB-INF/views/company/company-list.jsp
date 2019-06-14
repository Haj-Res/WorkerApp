<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/fontawesome/css/all.css" />" rel="stylesheet">
</head>
<body class="bg-dark">
<div class="container d-flex flex-column bg-light h-100">
    <%@include file="../shared/navigation.jsp" %>
    <%@include file="../shared/alerts.jsp" %>
    <div class="container d-flex flex-column mt-5">
        <div class="d-flex justify-content-center">
            <h3>Company List</h3>
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
            <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                <div class="align-self-end">
                    <a href="${pageContext.request.contextPath}/company/add" class="btn btn-success"><span
                            class="fas fa-plus"></span> Add Company</a>
                </div>
            </security:authorize>
        </div>
        <div class="d-flex justify-content-center p-2 pt-0 mt-0">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>#</th>
                    <th>Company name</th>
                    <th>Address</th>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                        <th>Action</th>
                    </security:authorize>
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
                        <td><c:out value="${loop.index + 1 + ( page - 1 ) * size}."/></td>
                        <td><c:out value="${company.name}"/></td>
                        <td><c:out
                                value="${company.address.street} ${company.address.number}, ${company.address.city}"/></td>
                        <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                            <td class="d-flex justify-content-around">
                                <a class="mr-2" href="${editCompany}"><span class="fas fa-pen"></span></a>
                                <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                    |
                                    <a class="ml-2" href="${deleteCompany}"><span class="fas fa-trash"></span></a>
                                </security:authorize>
                            </td>
                        </security:authorize>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%@include file="../shared/pagination.jsp" %>
    </div>
    <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />" rel="script"></script>
</body>
</html>
