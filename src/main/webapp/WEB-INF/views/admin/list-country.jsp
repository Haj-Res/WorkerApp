<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage pageName="Country List">
    <jsp:body>
        <div class="flex-row d-flex justify-content-between mb-2">
            <a href="${pageContext.request.contextPath}/admin/panel" class="btn btn-secondary">Back</a>
            <a href="${pageContext.request.contextPath}/admin/country/add" class="btn btn-success">
                <span class="fas fa-plus"></span> Add New
            </a>
        </div>
        <t:three-column-table list="${list}"/>
    </jsp:body>
</t:genericpage>

