<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<p>
<strong><s:label for="o1"><f:message key="tires.to.select"/></s:label></strong>
<table>
    <thead>
        <td><f:message key="field.manufacturer"/></td>
        <td><f:message key="field.amount"/></td>
        <td><f:message key="field.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${actionBean.allTireTypes}" var="tireType">
            <tr>
                <td><c:out value="${tireType.manufacturer}"/></td>
                <td><c:out value="${tireType.amountOnStore}"/></td>
                <td><c:out value="${tireType.price}"/></td>
                <td><s:text name="tireAmount"/></td>
                <td>
                <s:submit name="addTire">
                    <s:param name="tireType.id" value="${tireType.id}"/>Add</s:submit>
                </td>
            </tr>
        </c:forEach>
    </tbody>
                    
</table></p>

<p>
<strong><s:label for="o1"><f:message key="selected.tires"/></s:label></strong>
<table class="aui">
    <thead>
        <td><f:message key="field.manufacturer"/></td>
        <td><f:message key="field.amount"/></td>
        <td><f:message key="field.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${order.tires}" var="tire">
            <tr>
                <td><c:out value="${tire.tireType.manufacturer}"/></td>
                <td><c:out value="${tire.amountOnStore}"/></td>
                <td><c:out value="${tire.tireType.price}"/></td>
                <td><s:submit name="removeTire"><s:param name="tireType.id" value="${tire.tireType.id}"/>Remove</s:submit></td>
            </tr>
        </c:forEach>
    </tbody>
</table></p>


