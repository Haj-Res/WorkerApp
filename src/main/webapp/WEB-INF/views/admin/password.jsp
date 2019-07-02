<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:genericpage pageName="User profile">
    <jsp:body>
        <form:form action="${pageContext.request.contextPath}/admin/user/password/${username}" method="post"
                   modelAttribute="password"
                   cssClass="d-flex flex-column mt-5 px-5 py-2">
            <div class="form-group mt-2">
                <label for="firstName">New Password</label>
                <form:errors path="newPassword" cssClass="error"/>
                <form:password path="newPassword" class="form-control" id="firstName"/>
            </div>

            <div class="form-group mt-2">
                <label for="lastName">Repeat New Password</label>
                <form:errors path="newMatchingPassword" cssClass="error"/>
                <form:password path="newMatchingPassword" class="form-control" id="lastName"/>
            </div>
            <div class="d-flex flex-row mt-4">
                <input class="btn btn-secondary mr-3" type="submit" value="Save"/>
                <a class="btn btn-outline-secondary"
                   href="${pageContext.request.contextPath}/admin/user/edit/${username}">Cancel</a>
            </div>
        </form:form>
    </jsp:body>
</t:genericpage>
