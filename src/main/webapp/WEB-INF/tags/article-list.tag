<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@ attribute name="articleList" required="true" type="java.util.List<com.hajres.news.model.Article>" %>

<c:forEach var="article" items="${articleList}">
    <t:article-short article="${article}"/>
    <hr>
</c:forEach>