<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@ attribute name="articleList" required="true" type="java.util.List<com.hajres.news.model.Article>" %>

<c:choose>
    <c:when test="${articleList.size() != 0}">
        <c:forEach var="article" items="${articleList}">
            <t:article-short article="${article}"/>
            <hr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <h4>No results found.</h4>
    </c:otherwise>
</c:choose>