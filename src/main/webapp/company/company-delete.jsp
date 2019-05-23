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
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css"%>
    </style>
</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Delete Company
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="d-flex justify-content-center">
        <div class="container d-flex flex-column p-5 m-5">
            <div class="alert border-info bg-light p-5">
                <h2>Company info:</h2>
                <h4>${company.name}</h4>
                <p>Address: ${company.address.street} ${company.address.number}, ${company.address.city}</p>
            </div>
            <div class="alert alert-danger p-2 d-flex justify-content-around" role="alert">
        <span class="m-2">You're about to delete this company. This process is irreversible. Are you sure you want to delete the
        user?</span>
                <a class="btn btn-danger m-2"
                   href="${pageContext.request.contextPath}/company/delete?id=${company.idCompany}&confirm=true">DELETE</a>
                <a class="btn btn-light m-2" href="${pageContext.request.contextPath}/company">Cancel</a>
            </div>
        </div>
    </div>
</div>
</body>
