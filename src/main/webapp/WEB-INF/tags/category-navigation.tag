<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="categories" type="java.util.List<java.lang.String>" required="true" %>

<nav class="col-md-3">
    <div class="list-group">
        <a href="${pageContext.request.contextPath}/news/"
           class="list-group-item list-group-item-action">Show my feed</a>
        <c:forEach var="cat" items="${categories}">
            <a href="${pageContext.request.contextPath}/news/category/${cat}"
               class="list-group-item list-group-item-action">${cat}</a>
        </c:forEach>
    </div>
</nav>