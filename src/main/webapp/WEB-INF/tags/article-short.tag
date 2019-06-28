<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ attribute name="article" required="true" type="com.hajres.news.model.Article" %>

<article>
    <a href="${pageContext.request.contextPath}/news/articles/${article.hashCode()}" class="list-group-item-action">
        <div class="d-flex flex-wrap media pr-5 mb-5">
            <img class="d-flex flex-wrap mr-4 thumbnail" src="${article.urlToImage}" alt="Missing Image"
                 data-toggle="tooltip"
                 data-placement="top" title="${article.title}">
            <div class="media-body">
                <h5 class=mt-0">${article.title}</h5>
                <span class="text-secondary">${article.description}</span>
            </div>
        </div>
    </a>
</article>