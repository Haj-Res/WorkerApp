<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<t:genericpage pageName="Worker Management">
    <div class="px-5 py-2">
        <form:form method="post" action="save" modelAttribute="worker">
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <h5>Worker info:</h5>
                    <hr class="mt-0">

                    <!-- Object IDs -->
                    <form:hidden path="id"/>
                    <form:hidden path="address.idAddress"/>
                    <form:hidden path="company.idCompany"/>
                    <form:hidden path="company.address.idAddress"/>

                    <div class="form-group">
                        <label for="jmbg">JMBG:</label>
                        <form:input class="form-control" type="text" id="jmbg" path="jmbg"/>
                        <form:errors path="jmbg" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="firstName">First name:</label>
                        <form:input class="form-control" type="text" id="firstName" path="firstName"/>
                        <form:errors path="firstName" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name:</label>
                        <form:input class="form-control" type="text" id="lastName" path="lastName"/>
                        <form:errors path="lastName" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="birthDate">Birthday:</label>
                        <form:input class="form-control" type="date" id="birthDate" path="birthDate"/>
                        <form:errors path="birthDate" cssClass="error"/>
                    </div>
                </div>
                <div class="flex-fill m-2">
                    <h5>Address:</h5>
                    <hr class="mt-0">
                    <div class="form-group border-info rounded-lg">
                        <label for="city">City:</label>
                        <form:input class="form-control" type="text" id="city" path="address.city"/>
                        <form:errors path="address.city" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="street">Street:</label>
                        <form:input class="form-control" type="text" id="street" path="address.street"/>
                        <form:errors path="address.street" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="number">Number:</label>
                        <form:input class="form-control" type="text" id="number" path="address.number"/>
                        <form:errors path="address.number" cssClass="error"/>
                    </div>
                </div>
            </div>
            <div class="d-flex ml-2 mt-2"><h5>Company:</h5>
            </div>
            <hr class="mt-0">
            <div class="d-flex">
                <div class="flex-fill m-2">
                    <div class="form-group">
                        <label for="company-name">Company name:</label>
                        <form:input class="form-control" id="company-name" path="company.name"/>
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
            <div class="mx-2 mt-2">
                <button type="submit" class="btn btn-secondary">Submit</button>
                <a class="ml-3 btn btn-light border-secondary" href="${pageContext.request.contextPath}/worker/list">Cancle</a>
            </div>
        </form:form>
    </div>
</t:genericpage>
