<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage pageName="Users">
    <jsp:attribute name="footer">
            <%@ include file="../shared/pagination.jsp" %>
        </jsp:attribute>
    <jsp:body>
        <div class="flex-row d-flex justify-content-between mb-2">
            <a href="${pageContext.request.contextPath}/admin/panel" class="btn btn-secondary">Back</a>
            <a href="/admin/user/add" class="btn btn-success">Add New User</a></div>
        <div class="flex-row">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th>#</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Roles</th>
                    <th>Category preference</th>
                    <th>Country preference</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="user" varStatus="loop">
                    <tr>
                        <c:url var="editUser" scope="page" value="/admin/user/edit/${user.username}"/>
                        <c:url var="deleteUser" scope="page" value="/admin/user/delete/${user.username}"/>
                        <td>${(page - 1) * pageSize + loop.index + 1}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.roles}</td>
                        <td>${user.category}</td>
                        <td>${user.country}</td>
                        <td class="d-flex justify-content-between">
                            <a href="${editUser}"><span class="fa fa-pen"></span></a>
                            |
                            <a href="${deleteUser}"><span class="fa fa-trash"></span></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </jsp:body>
</t:genericpage>