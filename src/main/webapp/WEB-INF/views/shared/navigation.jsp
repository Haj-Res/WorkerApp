<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom row">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">WorkerApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
<c:set var="active" value="${pageContext.request.servletPath.split('/')[3]}"/>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto d-flex">
            <li class="nav-item <c:if test="${active == 'worker'}">active</c:if>">
                <a class="nav-link" href="${pageContext.request.contextPath}/worker/list">Worker list</a>
            </li>
            <li class="nav-item <c:if test="${active == 'company'}">active</c:if>">
                <a class="nav-link" href="${pageContext.request.contextPath}/company/list">Company list</a>
            </li>
        </ul>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <div class="nav-item mx-4">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/administration/registration"><span
                        class="fas fa-plus"></span> Register New Employee</a>
            </div>
        </sec:authorize>
        <div class="nav-item">
            <a class="btn btn-dark" href="#" onclick="document.getElementById('logout').submit();">Logout</a>
        </div>


        <form id="logout" action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</nav>