<%@ attribute name="faClass" required="true" type="java.lang.String" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="tooltip" required="false" type="java.lang.String" %>
<%@ attribute name="active" required="false" type="java.lang.String" %>

<a href="#" class="tile btn btn-sq-lg btn-secondary py-2 px-3 m-2 ${active}" data-toggle="tooltip"
   data-placement="top" title="${tooltip}">
    <i class="${faClass} fa-5x"></i><br/>
    ${title}
</a>
