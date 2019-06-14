<%--
  Created by IntelliJ IDEA.
  User: resad
  Date: 6/10/19
  Time: 1:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%--@elvariable id="page" type="int"--%>
<%--@elvariable id="pageCount" type="int"--%>
<%--@elvariable id="size" type="int"--%>

<div class="d-flex justify-content-center p-2 pt-0 mt-0">
    <nav aria-label="...">
        <ul class="pagination">
            <li class="page-item
            <c:if test="${page <= 1}">disabled</c:if>">
                <a class="page-link" href="?page=${page - 1}&size=${size}" tabindex="-1">Previous</a>
            </li>
            <!-- Show page 1 and ... if selected page is 5 or higher -->
            <c:if test="${page >= 4}">
                <li class="page-item"><a class="page-link" href="?page=1&size=${size}">1</a></li>
                <li class="page-item disabled"><span class="page-link">...</span></li>
            </c:if>

            <c:if test="${page > 2}">
                <li class="page-item"><a class="page-link" href="?page=${page -2}&size=${size}">${page - 2}</a></li>
            </c:if>

            <c:if test="${page > 1}">
                <li class="page-item"><a class="page-link" href="?page=${page -1}&size=${size}">${page - 1}</a></li>
            </c:if>

            <li class="page-item active">
                <a class="page-link" href="?page=${page}&size=${size}">${page} <span class="sr-only">(current)</span></a>
            </li>

            <c:if test="${page < pageCount}">
                <li class="page-item"><a class="page-link" href="?page=${page + 1}&size=${size}">${page + 1}</a></li>
            </c:if>
            <c:if test="${page + 1 < pageCount}">
                <li class="page-item"><a class="page-link" href="?page=${page + 2}&size=${size}">${page + 2}</a></li>
            </c:if>

            <c:if test="${page + 2 < pageCount}">
                <li class="page-item disabled"><span class="page-link">...</span></li>
                <li class="page-item"><a class="page-link" href="?page=${pageCount}&size=${size}">${pageCount}</a></li>

            </c:if>
            <li class="page-item <c:if test="${page >= pageCount}">disabled</c:if>">
                <a class="page-link"
                   href="?page=${page + 1}&size=${size}">Next</a>
            </li>
        </ul>
    </nav>
</div>