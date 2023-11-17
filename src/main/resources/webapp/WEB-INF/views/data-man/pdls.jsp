
<%@ include file="../00-header.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="../snippets/sortable.js" %>
    });
</script>
<div class="content"><div>

    <br>
    <table class="res action">
        <thead>
        <tr>
            <th data-sort="int">Pdl Ref</th>
            <th data-sort="string">Station</th>
            <th data-sort="string">Power</th>
            <th data-sort="string">Date of activation</th>
            <th>
                <form:form action="${ctxPath}/manager/pdls/add" method="get">
                    <input type="submit" class="blueSubmit" value="Add New">
                </form:form>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pdlList}" var="cr">
            <tr>
                <td><a href="${ctxPath}/manager/pdls/details/${cr.ref_PDL}">${cr.ref_PDL}</a></td>
                <td>${cr.station_pk}</td>
                <td>${cr.power}</td>
                <td>${cr.dateActivation}</td>
                <td>
                    <form:form action="${ctxPath}/manager/pdls/delete/${cr.ref_PDL}">
                        <input type="submit" class="redSubmit" value="Delete">
                    </form:form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div></div>
<%@ include file="../00-footer.jsp" %>