<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage pageName="Delete Country">
    <jsp:body>
        <p>Are you sure you want to delete country
            "<b>[${dataModel.countryId}]${dataModel.localName}(${dataModel.internationalName}</b>"?
            This process can't be undone.</p>
        <form:form method="post"
                   action="${pageContext.request.contextPath}/admin/country/delete/${dataModel.countryId}">
            <input type="submit" class="btn btn-danger mr-4" value="DELETE"/>
            <a href="${pageContext.request.contextPath}/admin/country-list" class="btn btn-secondary">Cancel</a>
        </form:form>
    </jsp:body>
</t:genericpage>