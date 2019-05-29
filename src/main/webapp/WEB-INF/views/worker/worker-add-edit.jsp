<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="/resources/bootstrap/css/bootstrap.min.css"%>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="p-2">
        <form:form method="post" action="${formAction}" modelAttribute="worker">
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <h5>Worker info:</h5>
                    <div class="form-group">
                        <label for="jmbg">JMBG:</label>
                        <form:input class="form-control" type="text" id="jmbg" path="jmbg" required="true"/>
                        <form:errors path="jmbg" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name:</label>
                        <form:input class="form-control" type="text" id="firstName"
                                    path="firstName" required="true"/>
                        <form:errors path="firstName" cssClass="error"/>

                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name:</label>
                        <form:input class="form-control" type="text" id="lastName" path="lastName" required="true"/>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">Birthday:</label>
                        <form:input class="form-control" type="date" id="birthDate" path="birthDate" required="true"/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <h5>Address:</h5>
                    <div class="form-group border-info rounded-lg">
                        <label for="city">City:</label>
                        <form:input class="form-control" type="text" id="city" path="address.city" required="true"/>
                    </div>
                    <div class="form-group">
                        <label for="street">Street:</label>
                        <form:input class="form-control" type="text" id="street" path="address.street" required="true"/>
                    </div>
                    <div class="form-group">
                        <label for="number">Number:</label>
                        <form:input class="form-control" type="text" id="number" path="address.number" required="true"/>
                    </div>
                </div>
            </div>
            <div class="d-flex ml-2 mt-2"><h5>Company:</h5>
            </div>
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company">Company name:</label>
                        <form:input class="form-control" id="company" path="company.name"/>
                    </div>
                    <div class="form-group rounded-lg">
                        <label for="company-street">Street:</label>
                        <form:input class="form-control" type="text" id="company-street" path="company.address.street"/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company-city">City:</label>
                        <form:input class="form-control" type="text" id="company-city" path="company.address.city"/>
                    </div>
                    <div class="form-group">
                        <label for="company-number">Number:</label>
                        <form:input class="form-control" type="text" id="company-number" path="company.address.number"/>
                    </div>
                </div>
            </div>
            <div class="ml-2 mt-2">
                <button type="submit" class="btn btn-secondary">Submit</button>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
