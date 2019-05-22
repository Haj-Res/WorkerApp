<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="/bootstrap/css/bootstrap.min.css"%>
    </style>
</head>
<body>
<div class="container d-flex justify-content-center">
    <div class="list-group mt-5">
        <a href="${pageContext.request.contextPath}/worker" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">Worker</h5>
            </div>
            <p class="mb-1">View all, add, update or delete worker.</p>
        </a>
        <a href="${pageContext.request.contextPath}/company" class="list-group-item list-group-item-action flex-column align-items-start">
            <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">Companies</h5>
            </div>
            <p class="mb-1">View all, add, update or delete companies.</p>
        </a>
    </div>

</div>
</body>
</html>