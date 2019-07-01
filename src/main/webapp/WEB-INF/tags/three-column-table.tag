<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="list" required="true" type="java.util.List<com.hajres.domain.entity.news.Country>" %>
<table class="table">
    <thead class="thead-light">
    <tr>
        <th>#</th>
        <th>Country Code</th>
        <th>Local Name</th>
        <th>International Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="element" items="${list}" varStatus="loop">
        <tr>
            <!-- Defining edit element URL -->
            <c:url var="editElement" value="/admin/country/edit/${element.countryId}">
            </c:url>

            <!-- Defining delete element URL -->
            <c:url var="deleteElement" value="/admin/country/delete/${element.countryId}">
            </c:url>


            <td><c:out value="${loop.index + 1}."/></td>
            <td><c:out value="${element.countryId}"/></td>
            <td><c:out value="${element.localName}"/></td>
            <td><c:out value="${element.internationalName}"/></td>
            <td class="d-flex justify-content-around">
                <a href="${editElement}"><span class="fas fa-pen"></span></a>
                |
                <a href="${deleteElement}"><span class="fas fa-trash"></span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>