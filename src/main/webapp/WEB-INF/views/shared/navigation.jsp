<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom row">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">WorkerApp</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <c:set var="active" value="${pageContext.request.servletPath.split('/')[3]}"/>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto d-flex">
            <li class="nav-item <c:if test="${active == 'news'}">active</c:if>">
                <a class="nav-link" href="${pageContext.request.contextPath}/news/">News</a>
            </li>
            <security:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_EMPLOYEE')">
                <li class="nav-item <c:if test="${active == 'worker'}">active</c:if>">
                    <a class="nav-link" href="${pageContext.request.contextPath}/worker/list">Worker list</a>
                </li>
                <li class="nav-item <c:if test="${active == 'company'}">active</c:if>">
                    <a class="nav-link" href="${pageContext.request.contextPath}/company/list">Company list</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item <c:if test="${active == 'admin'}">active</c:if>">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/panel">Admin panel</a>
                </li>
            </security:authorize>
        </ul>


        <div class="dropdown nav-item">
            <button class="btn btn-dark dropdown-toggle"
                    type="button" id="dropDownMenu" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                <span class="fa fa-cog"></span>
            </button>
            <div class="dropdown-menu" aria-labelledby="dropDownMenu">

                <c:set var="user" value="${pageContext.session.getAttribute('user')}"
                       target="com.hajres.domain.entity.User"/>
                <h3 class="dropdown-header">${user.firstName} ${user.lastName} (${user.username})</h3>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/user/profile">Profile </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" onclick="document.getElementById('logout').submit();">Logout</a>
            </div>
        </div>

        <form id="logout" action="${pageContext.request.contextPath}/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</nav>