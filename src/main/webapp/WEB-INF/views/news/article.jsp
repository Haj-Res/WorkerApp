<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage pageName="News">
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
            <article class="col-md-9">
                <div class="mb-5 col-md-12"><h3>${article.title}</h3></div>
                <div class="mb-5 col-md-12">
                    <p>
                        <a href="${article.urlToImage}"><img class="article" src="${article.urlToImage}"/></a>
                            ${article.content}
                    </p>
                </div>
                <div class="mb-1 col-md-12 d-flex text-secondary">
                    <span class="mx-3">Time of publishing: ${article.publishedAt}</span> <br>
                </div>
                <div class="col-md-12 d-flex justify-content-between text-secondary">
                    <span class="mx-3">Author: ${article.author}</span>
                    <span class="mx-3">Source: <a href="${article.url}">${article.source.name.toLowerCase()}</a></span>
                </div>
            </article>
        </div>

    </jsp:body>
</t:genericpage>
