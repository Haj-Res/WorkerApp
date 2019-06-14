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
        <div class="d-flex justify-content-center ">
            <h3>Worker list</h3>
        </div>
    </div>
    <div class="d-flex flex-row justify-content-between px-2 py-0">
        <form class="form-inline align-self-start" method="get" action="list">
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
                <a href="${pageContext.request.contextPath}/worker/add" class="btn btn-success"><span
                        class="fas fa-plus"></span> Add Worker</a>
            </div>
        </security:authorize>
    </div>
    <div class="p-2 pt-0 d-flex justify-content-center">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th>#</th>
                <th>JMBG</th>
                <th>Name</th>
                <th>Birthday</th>
                <th>Address</th>
                <th>Company</th>
                <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                    <th>Actions</th>
                </security:authorize>
            </tr>
            </thead>

            <c:forEach var="worker" items="${workerList}" varStatus="loop">
                <tbody>
                <tr>

                    <!-- Defining edit worker URL -->
                    <c:url var="editWorker" value="/worker/edit">
                        <c:param name="workerId" value="${worker.id}"/>
                    </c:url>

                    <!-- Defining delete worker URL -->
                    <c:url var="deleteWorker" value="/worker/delete">
                        <c:param name="workerId" value="${worker.id}"/>
                    </c:url>
                    <td><c:out value="${loop.index + 1 + ( page - 1 ) * size}."/></td>
                    <td><c:out value="${worker.jmbg}"/></td>
                    <td><c:out value="${worker.firstName} ${worker.lastName}"/></td>
                    <td><c:out value="${worker.birthDate}"/></td>
                    <td><c:out
                            value="${worker.address.street} ${worker.address.number}, ${worker.address.city}"/></td>
                    <td><c:out value="${worker.company.name}"/></td>
                    <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
                        <td class="d-flex justify-content-around">
                            <a href="${editWorker}"><span class="fas fa-pen"></span></a>
                            <security:authorize access="hasAnyRole('ROLE_ADMIN')">
                                |
                                <a href="${deleteWorker}"><span class="fas fa-trash"></span></a>
                            </security:authorize>
                        </td>
                    </security:authorize>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <%@include file="../shared/pagination.jsp" %>
</div>
</div>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />" rel="script"></script>
</body>
</html>
