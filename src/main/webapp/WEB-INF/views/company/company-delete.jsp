<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:genericpage pageName="Delete Company">
    <jsp:body>
        <div class="d-flex justify-content-center">
            <div class="container d-flex flex-column p-5">
                <div class="alert border-info bg-light p-5">
                    <h4>Company info:</h4>
                    <hr>
                    <h5>${company.name}</h5>
                    <p>Address: ${company.address.street} ${company.address.number}, ${company.address.city}</p>
                </div>
                <div class="alert alert-danger p-2 d-flex justify-content-around" role="alert">

                    <c:url value="/company/delete" var="formAction">
                        <c:param name="companyId" value="${company.idCompany}"/>
                    </c:url>
                    <form:form method="post" action="${formAction}" cssClass="mb-0">
                        <span class="m-2">You're about to delete this company. This process is irreversible. Are you sure you want to delete the
                        user?</span>
                        <button type="submit" class="btn btn-danger m-2">DELETE</button>
                        <a class="btn btn-light m-2" href="${pageContext.request.contextPath}/company/list">Cancel</a>
                    </form:form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
