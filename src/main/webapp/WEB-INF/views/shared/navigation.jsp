<div class="container">
    <div class="d-flex justify-content-center p-2 btn-group btn-group-lg" role="group">
        <h5>
            <ul class="list-group list-group-horizontal">
                <a href="${pageContext.request.contextPath}/">
                    <li class="list-group-item">Main Menu</li>
                </a>
                <a href="${pageContext.request.contextPath}/worker/list?page=1">
                    <li class="list-group-item">List All Worker</li>
                </a>
                <a href="${pageContext.request.contextPath}/company/list?page=1">
                    <li class="list-group-item">List All Companies</li>
                </a>
                <form id="logout" action="${pageContext.request.contextPath}/logout" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <a href="#" onclick="document.getElementById('logout').submit();">
                    <li class="list-group-item">Logout</li>
                </a>
            </ul>
        </h5>
    </div>
</div>