<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage pageName="News">

    <jsp:attribute name="footer">
        <%@include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-3">
            <div class="col-md-3">
                <c:forEach var="cat" items="${categories}">
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/news/category/${cat}"
                           class="list-group-item list-group-item-action">${cat}</a>
                    </div>
                </c:forEach>
            </div>
            <div class="col-md-9">
                <div class="col-md-12 pl-0">
                    <p>
                        <a class="btn btn-outline-secondary mr-3" data-toggle="collapse" href="#countryCollapse"
                           aria-expanded="false" aria-controls="countryCollapse"> Country Preference </a>
                        <c:if test="${not empty sources}">
                            <a class="btn btn-outline-secondary" data-toggle="collapse" href="#sourceCollapse"
                               aria-expanded="false"
                               aria-controls="sourceCollapse"> Sources </a>
                        </c:if>
                    </p>
                </div>
                <div class="collapse mb-4" id="countryCollapse">
                    <div class="card card-body">
                        <form:form action="${pageContext.request.contextPath}/user/preferences" method="post"
                                   modelAttribute="dto">
                            <div class="form-group mt-2">
                                <label for="country">Country News Preference</label>
                                <form:select cssClass="custom-select" path="country" name="country">
                                    <form:options items="${countries}"/>
                                </form:select>
                            </div>
                            <div class="form-group mt-2">
                                <label for="category">Category Preference</label><br>
                                <div class="d-flex flex-row container justify-content-start flex-wrap">
                                    <div class="col-md-5">
                                        <form:radiobutton cssClass="mr-2" path="category" value=""
                                                          label="No preference"/>
                                    </div>
                                    <form:radiobuttons cssClass="mr-2" element="div class='col-md-5'" path="category"
                                                       items="${categories}"/>
                                </div>
                            </div>
                            <input class="btn btn-secondary mr-3" type="submit" value="Update"/>
                        </form:form>
                    </div>
                </div>
                <c:if test="${not empty sources}">
                    <div class="collapse" id="sourceCollapse">
                        <div class="card card-body">
                            <c:if test="${not empty sources}">
                                <div class="form-group mt-2">
                                    <label for="source">Country News Preference</label>
                                    <form:form method="get" action="${pageContext.request.contextPath}/news">
                                        <select id="source" class="custom-select" name="source">
                                            <option value="" label="All">All</option>
                                            <c:forEach items="${sources}" var="src">
                                                <option value="${src.id}" label="${src.name}"
                                                        <c:if test="${selectedSource.equals(src.id)}">selected</c:if>
                                                >${src.name}</option>
                                            </c:forEach>
                                        </select>
                                        <input class="btn btn-secondary mt-3" type="submit" value="Update preference"/>
                                    </form:form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:if>
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
            </div>
        </div>
    </jsp:body>
</t:genericpage>
