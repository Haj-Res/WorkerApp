<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage pageName="${pageName}">

    <jsp:body>
        <div class="row">
            <div class="col-md-3">
                <t:side-nav-bar categories="${categories}" sortMap="${sortMap}" languages="${languages}"/>
            </div>
            <div class="col-md-9">
                <form:form method="post" action="${action}" modelAttribute="${dataMode}">
                    <div class="form-group">
                        <label for="id">URL parameter name:</label>
                        <form:input cssClass="form-control" type="text" id="id" path="id"/>
                        <form:errors path="id" cssClass="error"/>
                    </div>
                    <div class="form-group">
                        <label for="name">Display name:</label>
                        <form:input cssClass="form-control" type="text" id="name" path="name"/>
                        <form:errors path="name" cssClass="error"/>
                    </div>
                    <div class="mx-2 mt-2">
                        <button type="submit" class="btn btn-secondary">Submit</button>
                        <a class="ml-3 btn btn-light border-secondary"
                           href="${pageContext.request.contextPath}/admin/panel">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>