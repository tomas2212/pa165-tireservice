<p>
<strong><s:label for="o5"><f:message key="services.to.select"/></s:label></strong>
<table>
    <thead>
        <td><f:message key="field.servicename"/></td>
        <td><f:message key="field.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${actionBean.allServices}" var="service">
                <tr>
                    <td><c:out value="${service.name}"/></td>
                    <td><c:out value="${service.price}"/>
                    <td><s:submit name="addService">
                        <s:param name="service.id" value="${service.id}"/><f:message key="action.add" /></s:submit>
                    </td>
                </tr>
            </c:forEach>
    </tbody>
                     </table></p>
<p><strong><s:label for="o6"><f:message key="selected.services"/></s:label></strong>
<table class="aui">
    <thead>
        <td><f:message key="field.servicename"/></td>
        <td><f:message key="field.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${order.services}" var="service">
            <tr>
                <td><c:out value="${service.name}"/></td>
                <td><c:out value="${service.price}"/></td>
                <td><s:submit name="removeService"><s:param name="service.id" value="${service.id}"/><f:message key="action.remove" /></s:submit></td>
            </tr>
        </c:forEach>
    </tbody>
</table></p>
