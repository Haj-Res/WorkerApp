<%--@elvariable id="worker" type="com.hajres.domain.model.Worker"--%>
<%--
  Created by IntelliJ IDEA.
  User: resad
  Date: 5/22/19
  Time: 1:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Worker Management App</title>
    <style type="text/css">
        <%@include file="../bootstrap/css/bootstrap.min.css"%>
    </style>
</head>
<body>
<div class="container d-flex flex-column bg-light h-100">
    <div class="d-flex justify-content-center p-2">
        <h1>
            Delete Worker
        </h1>
    </div>
    <%@include file="../shared/navigation.jsp" %>
    <div class="d-flex justify-content-center">
        <div class="container d-flex flex-column p-5 m-5">
            <div class="alert border-info bg-light p-5">
                <h2>Worker info:</h2>
                <h4>${worker.firstName} ${worker.lastName}</h4><h5>Jmbg: ${worker.jmbg}</h5>
                <p>Born on: ${worker.birthDate}</p>
                <p>Address: ${worker.address.street} ${worker.address.number}, ${worker.address.city}</p>
                <p>Employed
                    at: ${worker.company.name}, ${worker.company.address.street} ${worker.company.address.number}, ${worker.company.address.city}</p>
            </div>
            <div class="alert alert-danger p-2 d-flex justify-content-around" role="alert">
        <span class="m-2">You're about to delete this worker. This process is irreversible. Are you sure you want to delete the
        user?</span>
                <a class="btn btn-danger m-2"
                   href="${pageContext.request.contextPath}/worker/delete?jmbg=${worker.jmbg}&confirm=true">DELETE</a>
                <a class="btn btn-light m-2" href="${pageContext.request.contextPath}/worker">Cancel</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
