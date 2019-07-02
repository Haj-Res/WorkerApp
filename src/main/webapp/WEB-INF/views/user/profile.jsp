<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:genericpage pageName="User profile"
               message="${message}"
               errorMessage="${errorMessage}">
    <jsp:body>
        <form:form action="save" method="post" modelAttribute="user"
                   cssClass="d-flex flex-column mt-5 px-5 py-2">
            <div class="input-group mt-1">
                <div class="input-group-prepend">
                    <div class="input-group-text"><span class="far fa-user"></span></div>
                </div>
                <form:input path="username" type="text" class="form-control" readonly="true"/>
            </div>

            <div class="form-group mt-2">
                <label for="firstName">First Name</label>
                <form:errors path="firstName" cssClass="error"/>
                <form:input path="firstName" type="text" class="form-control" id="firstName"/>
            </div>

            <div class="form-group mt-2">
                <label for="lastName">Last Name</label>
                <form:errors path="lastName" cssClass="error"/>
                <form:input path="lastName" type="text" class="form-control" id="lastName"/>
            </div>

            <div class="form-group mt-2">
                <label for="email">Email</label>
                <form:errors path="email" cssClass="error"/>
                <form:input path="email" type="text" class="form-control" id="email"/>
            </div>
            <div class="form-group mt-2">
                <label for="country">Country News Preference</label>
                <form:errors path="country" cssClass="error"/>
                <form:select cssClass="custom-select" path="country" name="country">
                    <form:options items="${countries}" itemValue="countryId" itemLabel="internationalName"/>
                </form:select>
            </div>
            <div class="form-group mt-2">
                <label class="d-flex flex-row" for="category">Category Preference</label>
                <div class="d-flex flex-row container justify-content-start flex-wrap">
                    <div class="col-md-3">
                        <form:radiobutton cssClass="mr-2" path="category" value="*" label="No preference"/>
                    </div>
                    <form:radiobuttons cssClass="mr-2" element="div class='col-md-3'" path="category"
                                       items="${categories}" itemValue="id" itemLabel="name"/>
                </div>
            </div>

            <div class="mt-2">
                <a class="btn btn-danger" href="${pageContext.request.contextPath}/user/password">Change password</a>
            </div>
            <div class="d-flex flex-row mt-4">
                <input class="btn btn-secondary mr-3" type="submit" value="Save"/>
                <a class="btn btn-outline-secondary" href="${pageContext.request.contextPath}/">Cancel</a>
            </div>
        </form:form>
    </jsp:body>
</t:genericpage>

