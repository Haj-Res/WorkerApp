<%--@elvariable id="action" type="java.lang.String"--%>
<%--@elvariable id="worker" type="com.hajres.domain.model.Worker"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.hajres.domain.model.Worker" %>
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
<div class="container d-flex flex-column">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <div class="d-flex justify-content-center p-2">
        <h4>
            <a href="${pageContext.request.contextPath}/">Main Menu</a>
            <a href="${pageContext.request.contextPath}/worker/new">Add New Worker</a>
            <a href="${pageContext.request.contextPath}/worker">List All Worker</a>
        </h4>
    </div>
    <div class="p-2">
        <form method="post" action="${pageContext.request.contextPath}/worker/${action}">
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <h5>Worker info:</h5>
                    <div class="form-group">
                        <label for="newJmbg">JMBG:</label>
                        <input class="form-control" type="text" id="newJmbg" name="newJmbg" value="${worker.jmbg}"/>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name:</label>
                        <input class="form-control" type="text" id="firstName" name="firstName"
                               value="${worker.firstName}"/>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name:</label>
                        <input class="form-control" type="text" id="lastName" name="lastName"
                               value="${worker.lastName}"/>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">Birthday:</label>
                        <input class="form-control" type="date" id="birthDate" name="birthDate"
                               value="${worker.localDateBirthDate}"/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <h5>Address:</h5>
                    <div class="form-group border-info rounded-lg">
                        <label for="city">City:</label>
                        <input class="form-control" type="text" id="city" name="city" value="${worker.address.city}"/>
                    </div>
                    <div class="form-group">
                        <label for="street">Street:</label>
                        <input class="form-control" type="text" id="street" name="street"
                               value="${worker.address.street}"/>
                    </div>
                    <div class="form-group">
                        <label for="number">Number:</label>
                        <input class="form-control" type="text" id="number" name="number"
                               value="${worker.address.number}"/>
                    </div>
                </div>
            </div>
            <div class="d-flex ml-2 mt-2"><h5>Company:</h5>
            </div>
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company">Company name:</label>
                        <input class="form-control" type="text" id="company" name="company"
                               value="${worker.company.name}"/>
                    </div>
                    <div class="form-group rounded-lg">
                        <label for="company-street">Street:</label>
                        <input class="form-control" type="text" id="company-street" name="company-street"
                               value="${worker.company.address.street}"/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company-city">City:</label>
                        <input class="form-control" type="text" id="company-city" name="company-city"
                               value="${worker.company.address.city}"/>
                    </div>
                    <div class="form-group">
                        <label for="company-number">Number:</label>
                        <input class="form-control" type="text" id="company-number" name="company-number"
                               value="${worker.company.address.number}"/>
                    </div>
                </div>
            </div>
            <div class="ml-2 mt-2">
                <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
