<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Register New User Form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Reference Bootstrap files -->
    <link rel="stylesheet"
          href="<c:url value="/resources/css/style.css"/>">
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="d-flex justify-content-center pt-5">
    <div id="loginbox"
         class="mainbox col-md-3 col-sm-6 mt-5">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="panel-title">Register New User</div>
            </div>
            <div style="padding-top: 30px" class="panel-body">
                <!-- Registration Form -->
                <form:form action="${pageContext.request.contextPath}/administration/processRegistration"
                           modelAttribute="regHelperUser"
                           class="form-horizontal">
                    <!-- Place for messages: error, alert etc ... -->
                    <div class="form-group">
                        <div class="col-xs-15">
                            <div>
                                <!-- Check for registration error -->
                                <c:if test="${registrationError != null}">
                                    <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                            ${registrationError}
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <!-- User name -->
                    <form:errors path="username" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <form:input path="username" placeholder="Username*" class="form-control"/>
                    </div>
                    <!-- Password -->
                    <form:errors path="password" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <form:password path="password" placeholder="Password*" class="form-control"/>
                    </div>
                    <!-- Confirm Password -->
                    <form:errors path="matchingPassword" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <form:password path="matchingPassword" placeholder="Confirm Password*" class="form-control"/>
                    </div>
                    <!-- First name -->
                    <form:errors path="firstName" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <form:input path="firstName" placeholder="First Name*" class="form-control"/>
                    </div>
                    <!-- Last name -->
                    <form:errors path="lastName" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <form:input path="lastName" placeholder="Last Name*" class="form-control"/>
                    </div>
                    <!-- Email -->
                    <form:errors path="email" cssClass="error"/>
                    <div style="margin-bottom: 25px" class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                        <form:input path="email" placeholder="Email*" class="form-control"/>
                    </div>
                    <div class="my-1">
                        <b>Employee roles:</b>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="checkbox" value="ROLE_EMPLOYEE" id="ROLE_EMPLOYEE"
                               checked disabled/>
                        <label class="form-check-label" for="ROLE_EMPLOYEE">
                            Employee
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <form:checkbox path="roles" class="form-check-input" value="ROLE_MANAGER"
                                       id="ROLE_MANAGER"/>
                        <label class="form-check-label" for="ROLE_MANAGER">
                            Manager
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <form:checkbox path="roles" class="form-check-input" value="ROLE_ADMIN" id="ROLE_ADMIN"/>
                        <label class="form-check-label" for="ROLE_ADMIN">
                            Administrator
                        </label>
                    </div>
                    <!-- Register Button -->
                    <div style="margin-top: 10px" class="form-group">
                        <div class="col-sm-6 controls">
                            <button type="submit" class="btn btn-primary">Register</button>
                            <a href="${pageContext.request.contextPath}/worker/list" class="btn btn-danger ml-3">Cancel</a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>