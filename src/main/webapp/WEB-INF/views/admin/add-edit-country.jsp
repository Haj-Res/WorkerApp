<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage pageName="${pageTitle}">
    <jsp:body>
        <form:form method="post" action="${pageContext.request.contextPath}/admin/country/save"
                   modelAttribute="dataModel">

            <label for="oldId">
                <input type="text" value="${dataModel.countryId}" id="oldId" name="oldId" hidden/>
            </label>
            <div class="form-group">
                <label for="id">Country code:</label>
                <form:input cssClass="form-control" type="text" id="id" path="countryId"/>
                <form:errors path="countryId" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="name">Local name:</label>
                <form:input cssClass="form-control" type="text" id="name" path="localName"/>
                <form:errors path="localName" cssClass="error"/>
            </div>
            <div class="form-group">
                <label for="name">International name:</label>
                <form:input cssClass="form-control" type="text" id="name" path="internationalName"/>
                <form:errors path="internationalName" cssClass="error"/>
            </div>
            <div class="mx-2 mt-2">
                <button type="submit" class="btn btn-secondary">Submit</button>
                <a class="ml-3 btn btn-light border-secondary"
                   href="${pageContext.request.contextPath}/admin/country-list">Cancel</a>
            </div>
        </form:form>
    </jsp:body>
</t:genericpage>