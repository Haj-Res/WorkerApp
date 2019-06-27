<%--@elvariable id="selectedSource" type="java.lang.String"--%>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:genericpage pageName="Personalized news feed">
    <jsp:attribute name="footer">
        <%@include file="../shared/pagination.jsp" %>
    </jsp:attribute>

    <jsp:body>
        <div class="row mt-3">
            <div class="col-md-3">
                <t:side-nav-bar categories="${categories}" sortMap="${sortMap}"/>
            </div>
            <div class="col-md-9">
                <div class="accordion" id="preferenceAccordion">
                    <div class="card">
                        <div class="card-header d-flex " id="countryCategoryHeading">
                            <h2 class="mb-0">
                                <button class="btn btn-link flex-fill" type="button" data-toggle="collapse"
                                        data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                    Country & Categories
                                </button>
                            </h2>
                        </div>

                        <div id="collapseOne" class="collapse" aria-labelledby="countryCategoryHeading"
                             data-parent="#preferenceAccordion">
                            <div class="card-body">
                                <form:form action="${pageContext.request.contextPath}/user/preferences" method="post"
                                           modelAttribute="dto">
                                    <div class="form-group mt-2">
                                        <label for="country">Country Preference</label>
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
                                            <form:radiobuttons cssClass="mr-2" element="div class='col-md-5'"
                                                               path="category"
                                                               items="${categories}"/>
                                        </div>
                                    </div>
                                    <input class="btn btn-secondary mr-3" type="submit" value="Update"/>
                                </form:form>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty sources}">
                        <div class="card">
                            <div class="card-header" id="sourceHeading">
                                <h2 class="mb-0">
                                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                            data-target="#collapseTwo" aria-expanded="false"
                                            aria-controls="collapseTwo">
                                        Sources
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseTwo" class="collapse" aria-labelledby="sourceHeading"
                                 data-parent="#preferenceAccordion">
                                <div class="card-body">
                                    <c:if test="${not empty sources}">
                                        <div class="form-group mt-2">
                                            <label for="source">News sources</label>
                                            <form:form method="get" action="${pageContext.request.contextPath}/news">
                                                <select id="source" class="custom-select" name="source">
                                                    <option value="" label="All">All</option>
                                                    <c:forEach items="${sources}" var="src">
                                                        <option value="${src.id}" label="${src.name}"
                                                                <c:if test="${selectedSource.equals(src.id)}">selected</c:if>
                                                        >${src.name}</option>
                                                    </c:forEach>
                                                </select>
                                                <input class="btn btn-secondary mt-3" type="submit"
                                                       value="Update preference"/>
                                            </form:form>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>

                <c:if test="${empty articles}"><p>Some error occurred. No articles found</p></c:if>
                <c:if test="${not empty articles}">
                    <t:article-list articleList="${articles}"/>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
