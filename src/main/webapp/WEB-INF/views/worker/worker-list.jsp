<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="../../../bootstrap/css/bootstrap.min.css"%>
    </style>

</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <%@include file="../../../shared/alerts.jsp"%>
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <%@include file="../../../shared/navigation.jsp" %>
    <div class="container d-flex flex-column">
        <div class="d-flex justify-content-center">
            <h2>Worker list</h2>
        </div>
        <div class="d-flex justify-content-start pl-2 py-0">
            <form class="form-inline" method="get" action="list">
                <div class="input-group">
                    <input type="text" class="form-control form-control-sm" name="filter" id="filter" value="${filter}" placeholder="Filter"
                           aria-label="Filter" aria-describedby="Filter box">
                    <div class="input-group-append">
                        <button class="btn btn-sm btn-secondary" type="submit">Go!</button>
                    </div>
                </div>
            </form>
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
                    <th>Actions</th>
                </tr>
                </thead>

                <c:forEach var="worker" items="${workerList}" varStatus="loop">
                    <tbody>
                    <tr>
                        <td><c:out value="${loop.index + 1}." /></td>

                        <td><c:out value="${worker.jmbg}"/></td>
                        <td><c:out value="${worker.firstName} ${worker.lastName}"/></td>
                        <td><c:out value="${worker.birthDate}"/></td>
                        <td><c:out
                                value="${worker.address.street} ${worker.address.number}, ${worker.address.city}"/></td>
                        <td><c:out value="${worker.company.name}"/></td>
                        <td>
                            <a class="mr-4"
                               href="${pageContext.request.contextPath}/worker/edit?jmbg=<c:out value="${worker.jmbg}"/>">
                                Edit
                            </a>

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
