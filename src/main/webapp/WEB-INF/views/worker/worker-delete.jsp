<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:genericpage pageName="Delete Worker">
    <jsp:body>
        <div class="d-flex justify-content-center">
            <div class="container d-flex flex-column p-5">
                <div class="alert border-info bg-light p-5">
                    <h4>Worker:</h4>
                    <hr>
                    <h5>${worker.firstName} ${worker.lastName} (${worker.jmbg})</h5>
                    <p>Born on: ${worker.birthDate}</p>
                    <p>Address: ${worker.address.street} ${worker.address.number}, ${worker.address.city}</p>
                    <c:if test="${worker.company != null}">
                        <p>Employed
                            at: ${worker.company.name}, ${worker.company.address.street} ${worker.company.address.number}, ${worker.company.address.city}
                        </p>
                    </c:if>
                </div>

                <c:url var="formAction" value="/worker/delete">
                    <c:param name="workerId" value="${worker.id}"/>
                </c:url>

                <form:form modelAttribute="worker" action="${formAction}" cssClass="mb-0">
                    <form:hidden path="id"/>
                    <div class="alert alert-danger p-2 d-flex justify-content-around" role="alert">
        <span class="m-2">You're about to delete this worker. This process is irreversible. Are you sure you want to delete the
        user?</span>
                        <button type="submit" class="btn btn-danger m-2">DELETE</button>

                        <a class="btn btn-light m-2" href="${pageContext.request.contextPath}/worker/list">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>