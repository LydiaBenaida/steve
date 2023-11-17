
<%@ include file="../00-header.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="../snippets/sortable.js" %>
    });
</script>
<div class="content"><div>
    <section><span>User Overview</span></section>
    <br>
    <table class="res action">
        <thead>
        <tr>
            <th data-sort="int">Charge point ID</th>
                <th data-sort="string">Profile ID</th>
            <th data-sort="string">Purpose</th>
            <th data-sort="string">Stack level</th>
            <th data-sort="string">Description</th>
            <th data-sort="string">Unit</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${profilesList}" var="cr">
            <tr>
                <td>${cr.charge_box_id}</td>
                <td>${cr.charging_profile_pk}</td>
                <td>${cr.charging_profile_purpose}</td>
                <td>${cr.stack_level}</td>
                <td>${cr.description}</td>
                <td>${cr.charging_rate_unit}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div></div>
<%@ include file="../00-footer.jsp" %>