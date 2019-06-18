<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage pageName="Access Denied">
    <jsp:body>
        <div class="alert alert-warning mt-5 w-100">
            <div class="d-flex justify-content-center p-2">
                <h2>
                    HTTP Status 403 - Access Denied
                </h2>
            </div>
            <div class="container d-flex flex-column">
                <h4 class="d-flex justify-content-center p-2">You do not have the authorization level needed to access
                    this
                    resource.</h4>
                <p class="d-flex justify-content-center p-2"><a href="${pageContext.request.contextPath}/">Back to Home
                    Page</a></p>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
