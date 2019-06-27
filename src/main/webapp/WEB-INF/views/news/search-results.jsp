<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage pageName="Search Results">
    <jsp:attribute name="footer">
        <%@ include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-3">
            <div class="col-md-3">
                <t:side-nav-bar categories="${categories}" sortMap="${sortMap}" languages="${languages}"
                q="${q}" language="${language}" sortBy="${sortBy}"/>
            </div>
            <div class="col-md-9 border-left">
                <t:article-list articleList="${articles}"/>
            </div>
        </div>
    </jsp:body>
</t:genericpage>

