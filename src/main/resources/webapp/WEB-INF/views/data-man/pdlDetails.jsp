<%--

    SteVe - SteckdosenVerwaltung - https://github.com/steve-community/steve
    Copyright (C) 2013-2023 SteVe Community Team
    All Rights Reserved.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

--%>
<%@ include file="../00-header.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="../snippets/datePicker-past.js" %>
    });
</script>
<spring:hasBindErrors name="pdlForm">
    <div class="error">
        Error while trying to update a pdl:
        <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
        </ul>
    </div>
</spring:hasBindErrors>
<div class="content"><div>
    <section><span>Pdl Details</span></section>
    <form:form action="${ctxPath}/manager/pdls/update" modelAttribute="pdlForm">

        <form:hidden path="ref_PDL" readonly="true"/>

        <table class="userInput">
            <thead><tr><th>Pdl</th><th></th></thead>
            <tr><td>Station:</td><td><form:input path="station_pk"/></td></tr>
            <tr><td>Power en KVA:</td><td><form:input path="power"/></td></tr>
            <tr><td></td>
                <td id="add_space">
                    <input type="submit" name="update" value="Update">
                    <input type="submit" name="backToOverview" value="Back to Overview">
                </td></tr>
        </table>

    </form:form>
</div></div>
<%@ include file="../00-footer.jsp" %>