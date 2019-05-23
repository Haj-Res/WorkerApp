<%--@elvariable id="errorMessage" type="java.lang.String"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<c:if test="${not empty errorMessage}">
    <div class="container mt-5 pt-2">
        <div class="alert alert-danger" role="alert">
            <strong>Oh snap!</strong>
            <c:out value="${errorMessage}"/>
        </div>
    </div>
</c:if>

<c:if test="${not empty message}">
    <div class="container mt-5">
        <div class="alert alert-success" role="alert">
            <strong>Success!</strong>
            <c:out value="${message}"/>
        </div>
    </div>
</c:if>