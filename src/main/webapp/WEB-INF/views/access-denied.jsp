<%--
  Created by IntelliJ IDEA.
  User: resad
  Date: 6/10/19
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization issue</title>
    <style type="text/css">
        <%@include file="/resources/bootstrap/css/bootstrap.min.css" %>
    </style>
</head>
<body>
<div class="container d-flex flex-column bg-light h-100 p-0">
    <div class="alert alert-warning mt-5 w-100">
        <div class="d-flex justify-content-center p-2">
            <h2>
                HTTP Status 403 - Access Denied
            </h2>
        </div>
        <div class="container d-flex flex-column">
            <h4 class="d-flex justify-content-center p-2">You do not have the authorization level needed to access this
                resource.</h4>
            <p class="d-flex justify-content-center p-2"><a href="${pageContext.request.contextPath}/">Back to Home
                Page</a></p>
        </div>
    </div>
</body>
</html>
