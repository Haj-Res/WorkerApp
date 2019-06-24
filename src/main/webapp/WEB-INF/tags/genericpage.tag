<%--@elvariable id="pageName" type="java.lang.String"--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="pageName" required="true" %>
<%@attribute name="message" required="false" %>
<%@attribute name="errorMessage" required="false" %>
<html>
<head>
    <title>Worker Management App</title>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/fontawesome/css/all.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body class="bg-dark">
<div class="container d-flex flex-column bg-light h-100">
    <div id="pageheader">
        <%@include file="../views/shared/navigation.jsp" %>
        <%@include file="../views/shared/alerts.jsp" %>
        <div class="container d-flex flex-column mt-4">
            <div class="d-flex justify-content-center ">
                <h2>${pageName}</h2>
            </div>
        </div>
    </div>
    <div id="body">
        <jsp:doBody/>
    </div>
    <div id="pagefooter">
        <jsp:invoke fragment="footer"/>
    </div>
</div>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js" />" rel="script"></script>
</body>
</html>
