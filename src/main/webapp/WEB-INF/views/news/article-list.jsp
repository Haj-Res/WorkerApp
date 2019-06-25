<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage pageName="News">
    <jsp:body>
        <p>
            <a class="btn btn-outline-secondary mr-3" data-toggle="collapse" href="#countryCollapse"
               aria-expanded="false" aria-controls="countryCollapse">
                Country Preference
            </a><a class="btn btn-outline-secondary" data-toggle="collapse" href="#sourceCollapse" aria-expanded="false"
                   aria-controls="sourceCollapse">
            Sources
        </a>
        </p>
        <div class="collapse" id="countryCollapse">
            <div class="card card-body">
                Choose a country and categories.
            </div>
        </div>

        <div class="collapse" id="sourceCollapse">
            <div class="card card-body">
                <c:if test="${not empty sources}">
                    <div class="form-group mt-2">
                        <label for="source">Country News Preference</label>
                        <form:form method="get" action="${pageContext.request.contextPath}/news">
                            <select id="source" class="custom-select" name="source">
                                <c:forEach items="${sources}" var="src">
                                    <option value="${src.id}" label="${src.name}">${src.name}</option>
                                </c:forEach>
                            </select>
                            <input class="btn btn-secondary mt-3" type="submit" value="Update preference"/>
                        </form:form>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${empty articles}"><p>Some error occurred. No articles found</p></c:if>
        <c:if test="${not empty articles}">
            <hr>
            <c:forEach var="art" items="${articles}">
                <a href="#" class="list-group-item-action">
                    <div class="media pr-5 mb-5">
                        <img class="d-flex mr-4 thumbnail" src="${art.urlToImage}" alt="Missing Image"
                             data-toggle="tooltip"
                             data-placement="top" title="${art.title}">
                        <div class="media-body">
                            <h5 class=mt-0">${art.title}</h5>
                            <span class="text-secondary">${art.description}</span>
                        </div>
                    </div>
                </a>
                <hr>
            </c:forEach>
        </c:if>
    </jsp:body>
</t:genericpage>
