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
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Edit Worker
        </h1>
    </div>
    <%@include file="../navigation.jsp" %>
    <div class="p-2">
        <form method="post" action="${pageContext.request.contextPath}/worker/${action}">
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <h5>Worker info:</h5>
                    <div class="form-group">
                        <label for="newJmbg">JMBG:</label>
                        <input class="form-control" type="text" id="newJmbg" name="newJmbg" value="${worker.jmbg}" required/>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name:</label>
                        <input class="form-control" type="text" id="firstName" name="firstName"
                               value="${worker.firstName}" required/>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name:</label>
                        <input class="form-control" type="text" id="lastName" name="lastName"
                               value="${worker.lastName}" required/>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">Birthday:</label>
                        <input class="form-control" type="date" id="birthDate" name="birthDate"
                               value="${worker.localDateBirthDate}" required/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <h5>Address:</h5>
                    <div class="form-group border-info rounded-lg">
                        <label for="city">City:</label>
                        <input class="form-control" type="text" id="city" name="city" value="${worker.address.city}" required/>
                    </div>
                    <div class="form-group">
                        <label for="street">Street:</label>
                        <input class="form-control" type="text" id="street" name="street"
                               value="${worker.address.street}" required/>
                    </div>
                    <div class="form-group">
                        <label for="number">Number:</label>
                        <input class="form-control" type="text" id="number" name="number"
                               value="${worker.address.number}" required/>
                    </div>
                </div>
            </div>
            <div class="d-flex ml-2 mt-2"><h5>Company:</h5>
            </div>
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company">Company name:</label>
                        <input class="form-control" list="companies" id="company" name="company" value="${worker.company.name}">
                        <datalist id="companies">
                            <%--@elvariable id="companyList" type="java.util.List<com.hajres.domain.model.Company>"--%>
                            <c:forEach var="company" items="${companyList}">
                                <option value="<c:out value="${company.name}" />"></option>
                            </c:forEach>
                        </datalist>
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
