<%--@elvariable id="page" type="int"--%>
<%--@elvariable id="pageCount" type="int"--%>
<%--@elvariable id="pageSize" type="int"--%>
<%--@elvariable id="selectedSource" type="String"--%>

<c:choose>
    <c:when test="${selectedSource != null}">
        <c:set var="base" value="?source=${selectedSource}&" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="base" value="?" scope="page"/>
    </c:otherwise>
</c:choose>

<div class="d-flex justify-content-center p-2 pt-0 mt-0">
    <nav aria-label="...">
        <ul class="pagination">
            <li class="page-item
            <c:if test="${page <= 1}">disabled</c:if>">
                <a class="page-link" href="${base}page=${page - 1}&size=${pageSize}" tabindex="-1">Previous</a>
            </li>
            <!-- Show page 1 and ... if selected page is 5 or higher -->
            <c:if test="${page >= 4}">
                <li class="page-item"><a class="page-link" href="${base}page=1&size=${pageSize}">1</a></li>
                <li class="page-item disabled"><span class="page-link">...</span></li>
            </c:if>

            <c:if test="${page > 2}">
                <li class="page-item"><a class="page-link" href="${base}page=${page -2}&size=${pageSize}">${page - 2}</a></li>
            </c:if>

            <c:if test="${page > 1}">
                <li class="page-item"><a class="page-link" href="${base}page=${page -1}&size=${pageSize}">${page - 1}</a></li>
            </c:if>

            <li class="page-item active">
                <a class="page-link" href="${base}page=${page}&size=${pageSize}">${page} <span
                        class="sr-only">(current)</span></a>
            </li>

            <c:if test="${page < pageCount}">
                <li class="page-item"><a class="page-link" href="${base}page=${page + 1}&size=${pageSize}">${page + 1}</a>
                </li>
            </c:if>
            <c:if test="${page + 1 < pageCount}">
                <li class="page-item"><a class="page-link" href="${base}page=${page + 2}&size=${pageSize}">${page + 2}</a>
                </li>
            </c:if>

            <c:if test="${page + 2 < pageCount}">
                <li class="page-item disabled"><span class="page-link">...</span></li>
                <li class="page-item"><a class="page-link" href="${base}page=${pageCount}&size=${pageSize}">${pageCount}</a>
                </li>

            </c:if>
            <li class="page-item <c:if test="${page >= pageCount}">disabled</c:if>">
                <a class="page-link"
                   href="${base}page=${page + 1}&size=${pageSize}">Next</a>
            </li>
        </ul>
    </nav>
</div>