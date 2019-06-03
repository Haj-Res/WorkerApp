<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            Edit Company
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="container p-2 pl-5 pr-5">
        <form:form cssClass="mx-2" method="post" action="save" modelAttribute="company">
            <form:hidden path="idCompany"/>
            <form:hidden path="address.idAddress"/>
            <div class="form-group">
                <label for="name">Company name</label>
                <form:input type="text" class="form-control" id="name" path="name" placeholder="Company name"
                            requiered="true"/>
                <form:errors path="name" cssClass="error"/>
            </div>

            <div class="form-group">
                <label for="city">City</label>
                <form:input type="text" class="form-control" id="city" path="address.city" placeholder="City"
                            requiered="true"/>
                <form:errors path="address.city" cssClass="error"/>
            </div>

            <div class="form-group">
                <label for="street">Street</label>
                <form:input type="text" class="form-control" id="street" path="address.street" placeholder="Street"
                            requiered="true"/>
                <form:errors path="address.street" cssClass="error"/>
            </div>

            <div class="form-group">
                <label for="number">Number</label>
                <form:input type="text" class="form-control" id="number" path="address.number" placeholder="Number"/>
                <form:errors path="address.number" cssClass="error"/>
            </div>
            <div class="mt-4">
                <button type="submit" class="btn btn-secondary">Submit</button>
                <a class="ml-3 btn btn-light border-secondary" href="${pageContext.request.contextPath}/company/list">Cancel</a>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
