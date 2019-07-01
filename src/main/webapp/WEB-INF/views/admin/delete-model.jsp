<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage pageName="Delete ${type}">
    <jsp:body>
        <p>Are you sure you want to delete parameter "<b>${dataModel.id}, ${dataModel.name}</b>"? This process can't be
            undone.</p>
        <form:form method="post" action="${pageContext.request.contextPath}/admin/${type}/delete/${dataModel.id}">
            <input type="submit" class="btn btn-danger mr-4" value="DELETE"/>
            <a href="${pageContext.request.contextPath}/admin/sort-order-list" class="btn btn-secondary">Cancel</a>
        </form:form>
    </jsp:body>
</t:genericpage>