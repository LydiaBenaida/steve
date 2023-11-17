
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<table class="userInput">
    <thead><tr><th>Pdl</th><th></th></thead>
    <tr><td>Station:</td><td><form:input path="station_pk"/></td></tr>
    <tr><td>Power en KVA:</td><td><form:input path="power"/></td></tr>
    <tr><td></td>
        <td id="add_space">
            <input type="submit" name="${submitButtonName}" value="${submitButtonValue}">
            <input type="submit" name="backToOverview" value="Back to Overview">
        </td></tr>
</table>