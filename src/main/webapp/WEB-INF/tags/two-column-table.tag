<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="type" required="true" type="java.lang.String" %>

<%@ attribute name="list" required="true" type="java.util.List<com.hajres.domain.entity.news.SortOrder>" %>
<table class="table">
    <thead class="thead-light">
    <tr>
        <th>#</th>
        <th>Parameter name</th>
        <th>Display text</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="element" items="${list}" varStatus="loop">
        <tr>
            <!-- Defining edit element URL -->
            <c:url var="editElement" value="/admin/${type}/edit/${element.id}">
            </c:url>

            <!-- Defining delete element URL -->
            <c:url var="deleteElement" value="/admin/${type}/delete/${element.id}">
            </c:url>


            <td><c:out value="${loop.index + 1}."/></td>
            <td><c:out value="${element.id}"/></td>
            <td><c:out value="${element.name}"/></td>
            <td class="d-flex justify-content-around">
                <a href="${editElement}"><span class="fas fa-pen"></span></a>
                |
                <a href="${deleteElement}"><span class="fas fa-trash"></span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>