<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:genericpage pageName="Worker List"
               message="${message}"
               errorMessage="${errorMessage}">

    <jsp:attribute name="footer">
            <%@include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="d-flex flex-row justify-content-between px-2 py-0">
            <form class="form-inline align-self-start mb-0" method="get" action="list">
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
        <div class="d-flex justify-content-center p-2 pt-0 mt-0">
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
                        <td><c:out value="${loop.index + 1 + ( page - 1 ) * pageSize}."/></td>
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
    </jsp:body>
</t:genericpage>
