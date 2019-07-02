<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage pageName="${pageTitle}">
    <jsp:body>
        <div class="d-flex flex-row justify-content-end px-2 py-3">
            <a href="${pageContext.request.contextPath}/admin/${type}/add" class="btn btn-success"><span
                    class="fas fa-plus"></span> Add New</a>
        </div>
        <t:two-column-table type="${type}" list="${list}"/>
    </jsp:body>
</t:genericpage>

