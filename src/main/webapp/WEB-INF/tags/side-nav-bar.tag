<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="language" type="java.lang.String"--%>
<%--@elvariable id="sortBy" type="java.lang.String"--%>

<%@ attribute name="categories" required="true" type="java.util.Map<java.lang.String, java.lang.String>" %>
<%@ attribute name="sortMap" required="true" type="java.util.Map<java.lang.String, java.lang.String>" %>
<%@ attribute name="languages" required="true" type="java.util.Map<java.lang.String, java.lang.String>" %>
<%@ attribute name="q" required="false" type="java.lang.String" %>
<%@ attribute name="sortBy" required="false" type="java.lang.String" %>
<%@ attribute name="language" required="false" type="java.lang.String" %>

<div class="side-nav-bar">
    <nav class="col-md-12 mb-4">
        <div class="list-group">
            <a href="${pageContext.request.contextPath}/news/"
               class="list-group-item list-group-item-action">Show my feed</a>
            <c:forEach var="cat" items="${categories}">
                <a href="${pageContext.request.contextPath}/news/category/${cat.key}"
                   class="list-group-item list-group-item-action">${cat.value}</a>
            </c:forEach>
        </div>
    </nav>
    <div class="col-md-12 my-4 ">
        <hr>
        <form:form method="get" action="${pageContext.request.contextPath}/news/search">
        <label for="sortBy" class="col-md-12 mb-0">Sort by</label>
        <select id="sortBy" class="custom-select col-md-12" name="sortBy">
            <c:forEach var="item" items="${sortMap}">
                <option value="${item.key}"
                        <c:if test="${item.key == sortBy}">selected</c:if>
                >${item.value}</option>
            </c:forEach>
        </select>
        <label for="languages" class="col-md-12 mb-0 mt-2">Language</label>
        <select id="languages" class="custom-select col-md-12" name="language">
            <c:forEach var="item" items="${languages}">
                <option value="${item.key}"
                        <c:if test="${item.key == language}">selected</c:if>
                >${item.value}</option>
            </c:forEach>
        </select>
        <label class="mt-2">
            <input class="col-md-12" type="text" name="q" value="${q}" placeholder="Search . . ." required/>
        </label>
        <div class="col-md-12 p-0 d-flex justify-content-end">
            <input type="submit" class="btn btn-secondary" value="Search"/>
        </div>
    </div>

    </form:form>
</div>
