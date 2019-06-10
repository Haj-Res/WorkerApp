<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="company" type="com.hajres.domain.model.Company"--%>
<%--
  Created by IntelliJ IDEA.
  User: resad
  Date: 5/23/19
  Time: 9:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Worker Management App</title>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <%@include file="../shared/navigation.jsp" %>
    <div class="d-flex justify-content-center">
        <h2>Delete Company</h2>
    </div>
    <div class="d-flex justify-content-center">
        <div class="container d-flex flex-column p-5 m-5">
            <div class="alert border-info bg-light p-5">
                <h2>Company info:</h2>
                <h4>${company.name}</h4>
                <p>Address: ${company.address.street} ${company.address.number}, ${company.address.city}</p>
            </div>
            <div class="alert alert-danger p-2 d-flex justify-content-around" role="alert">

                <c:url value="/company/delete" var="formAction">
                    <c:param name="companyId" value="${company.idCompany}"/>
                </c:url>
                <form:form method="post"
                           action="${formAction}">
        <span class="m-2">You're about to delete this company. This process is irreversible. Are you sure you want to delete the
        user?</span>
                    <button type="submit" class="btn btn-danger m-2">DELETE</button>
                    <a class="btn btn-light m-2" href="${pageContext.request.contextPath}/company/list">Cancel</a>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />" rel="script"></script>
</body>
</html>
