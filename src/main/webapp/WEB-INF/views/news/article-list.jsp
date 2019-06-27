<%--@elvariable id="categories" type="java.util.List<java.lang.String>"--%>
<%--@elvariable id="articles" type="java.util.List<com.hajres.news.model.Article>"--%>

<%--@elvariable id="sortMap" type="java.util.Map<java.lang.String, java.lang.String>"--%>
<%--@elvariable id="languages" type="java.util.Map<java.lang.String, java.lang.String>"--%>


<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage pageName="${category}">
    <jsp:attribute name="footer">
        <%@include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-3">
            <div class="col-md-3">
                <t:side-nav-bar categories="${categories}" sortMap="${sortMap}" languages="${languages}"/>
            </div>
            <div class="col-md-9 border-left">
                <t:article-list articleList="${articles}"/>
            </div>
        </div>
    </jsp:body>

</t:genericpage>