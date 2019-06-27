<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage pageName="${category}">
    <jsp:attribute name="footer">
        <%@include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-3">
        <t:category-navigation categories="${categories}"/>
        <div class="col-md-9">
            <hr>
            <t:article-list articleList="${articles}"/>
        </div>
    </jsp:body>

</t:genericpage>