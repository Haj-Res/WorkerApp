<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: resad
  Date: 6/14/19
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <div class="alert alert-danger mt-5">
        <div class="container d-flex flex-column">
            <div class="d-flex justify-content-center ">
                <h3>Error Code: 404</h3><br>
            </div>
        </div>
        <div class="container d-flex flex-column">
            <div class="d-flex justify-content-center ">
                <h4>Requested resource not found</h4>
            </div>
        </div>
        <div class="d-flex flex-column justify-content-between px-5 mt-5">
            <p><b>Request method:</b> ${method}</p>
            <p><b>Message:</b> The resource you are searching for either doesn't exist or is at another location.</p>
        </div>
    </div>
</div>
</body>
</html>
