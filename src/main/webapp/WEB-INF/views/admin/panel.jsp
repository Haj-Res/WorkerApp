<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage pageName="Administration Panel">
    <jsp:body>
        <div class="d-flex flex-wrap justify-content-around mt-5">
            <t:admin-tile faClass="fa fa-user" title="User Panel" tooltip="User administration panel"/>
            <t:admin-tile faClass="fa fa-th-list" title="News Categories" tooltip="Add and remove news categories"/>
            <t:admin-tile faClass="fa fa-language" title="News Languages" tooltip="Add and remove languages"/>
            <t:admin-tile faClass="fa fa-sort" title="News Sorting" tooltip="Add and remove sorting options"/>
        </div>
    </jsp:body>
</t:genericpage>

