
<%@ include file="../00-header.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="../snippets/datePicker-past.js" %>
    });
</script>
<spring:hasBindErrors name="pdlForm">
    <div class="error">
        Error while trying to add a pdl:
        <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
        </ul>
    </div>
</spring:hasBindErrors>
<div class="content"><div>
    <section><span>Add Pdl</span></section>
    <form:form action="${ctxPath}/manager/pdls/add" modelAttribute="pdlForm">

        <table class="userInput">
            <thead><tr><th>Pdl</th><th></th></thead>
            <tr><td>Station:</td><td><form:input path="station_pk"/></td></tr>
            <tr><td>Power en KVA:</td><td><form:input path="power"/></td></tr>
            <tr><td></td>
                <td id="add_space">
                    <input type="submit" name="add" value="Add">
                    <input type="submit" name="backToOverview" value="Back to Overview">
                </td></tr>
        </table>


    </form:form>
</div></div>
<%@ include file="../00-footer.jsp" %>