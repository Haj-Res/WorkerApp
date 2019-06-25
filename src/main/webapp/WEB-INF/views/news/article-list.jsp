<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage pageName="News">
    <jsp:body>
        <c:if test="${empty articles}"><p>Some error occurred. No articles found</p></c:if>
        <c:if test="${not empty articles}">
            <hr>
            <c:forEach var="art" items="${articles}">
                <a href="#" class="list-group-item-action">
                    <div class="media pr-5 mb-5">
                        <img class="d-flex mr-4 thumbnail" src="${art.urlToImage}" alt="Missing Image">
                        <div class="media-body">
                            <h5 class="mt-0">${art.title}</h5>
                                ${art.description}
                        </div>
                    </div>
                </a>
                <hr>
            </c:forEach>
        </c:if>
    </jsp:body>
</t:genericpage>
