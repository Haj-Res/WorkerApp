<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="/resources/bootstrap/css/bootstrap.min.css"%>
    </style>

</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <%@include file="../shared/alerts.jsp" %>
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center ">
            <h2>Worker list</h2>
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
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <div class="align-self-end">
                <a href="${pageContext.request.contextPath}/worker/add" class="btn btn-success">New Worker</a>
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
                <security:authorize access="hasRole('ROLE_ADMIN')">
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

                    <td><c:out value="${loop.index + 1}."/></td>

                    <td><c:out value="${worker.jmbg}"/></td>
                    <td><c:out value="${worker.firstName} ${worker.lastName}"/></td>
                    <td><c:out value="${worker.birthDate}"/></td>
                    <td><c:out
                            value="${worker.address.street} ${worker.address.number}, ${worker.address.city}"/></td>
                    <td><c:out value="${worker.company.name}"/></td>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <td>
                            <a class="mr-2" href="${editWorker}">Edit</a>
                            |
                            <a class="ml-2" href="${deleteWorker}">Delete</a>
                        </td>
                    </security:authorize>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </div>
    <div class="d-flex justify-content-center p-2 pt-0 mt-0">
        <nav aria-label="...">
            <ul class="pagination">
                <li class="page-item <c:if test="${page <= 1}">disabled</c:if>">
                    <a class="page-link" href="?page=${page - 1}" tabindex="-1">Previous</a>
                </li>
                <!-- Show page 1 and ... if selected page is 5 or higher -->
                <c:if test="${page >= 4}">
                    <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
                    <li class="page-item disabled"><span class="page-link">...</span></li>
                </c:if>

                <c:if test="${page > 2}">
                    <li class="page-item"><a class="page-link" href="?page=${page -2}">${page - 2}</a></li>
                </c:if>

                <c:if test="${page > 1}">
                    <li class="page-item"><a class="page-link" href="?page=${page -1}">${page - 1}</a></li>
                </c:if>

                <li class="page-item active">
                    <a class="page-link" href="?page=${page}">${page} <span class="sr-only">(current)</span></a>
                </li>

                <c:if test="${page < pageCount}">
                    <li class="page-item"><a class="page-link" href="?page=${page + 1}">${page + 1}</a></li>
                </c:if>
                <c:if test="${page + 1 < pageCount}">
                    <li class="page-item"><a class="page-link" href="?page=${page + 2}">${page + 2}</a></li>
                </c:if>

                <c:if test="${page + 2 < pageCount}">
                    <li class="page-item disabled"><span class="page-link">...</span></li>
                    <li class="page-item"><a class="page-link" href="?page=${pageCount}">${pageCount}</a></li>

                </c:if>
                <li class="page-item <c:if test="${page >= pageCount}">disabled</c:if>">
                    <a class="page-link"
                       href="?page=${page + 1}">Next</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</div>
</body>
</html>
