<%--@elvariable id="company" type="com.hajres.domain.model.Company"--%>
<%--@elvariable id="action" type="java.lang.String"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
            Edit Company
        </h1>
    </div>
    <%@include file="../navigation.jsp" %>
    <div class="container p-2 pl-5 pr-5">
        <form method="post" action="${pageContext.request.contextPath}/company/${action}">
            <div class="form-group">
                <label for="name">Company name</label>
                <input type="text" class="form-control" id="name" name="name" value="${company.name}" placeholder="Company name">
            </div>

            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" id="city" name="city" value="${company.address.city}" placeholder="City">
            </div>

            <div class="form-group">
                <label for="street">Street</label>
                <input type="text" class="form-control" id="street" name="street" value="${company.address.street}" placeholder="Street">
            </div>

            <div class="form-group">
                <label for="number">Number</label>
                <input type="text" class="form-control" id="number" name="number" value="${company.address.number}" placeholder="Number">
            </div>
            <div class="ml-2 mt-2">
                <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
