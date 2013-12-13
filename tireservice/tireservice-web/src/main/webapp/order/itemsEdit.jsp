<table style="width: 100%">
    <thead>
        <tr>
            <td><strong><s:label for="o7"><f:message key="tires.to.select"/></s:label></strong></td>
            <td><strong><s:label for="o5"><f:message key="services.to.select"/></s:label></strong></td>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>

                <table>
                    <thead>
                        <td><f:message key="field.manufacturer"/></td>
                        <td><f:message key="field.amount"/></td>
                        <td><f:message key="field.price"/></td>
                        <td></td>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.allTireTypes}" var="tireType">
                            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                                 <s:hidden name="editOrder.personId" value="${actionBean.order.personId}"></s:hidden>
                        <s:hidden name="editOrder.date" value="${actionBean.order.date}"></s:hidden>
                                <s:hidden name="editOrder.cartype" value="${actionBean.order.carType}"></s:hidden>
                                <tr>
                                    <td><c:out value="${tireType.manufacturer}"/></td>
                                    <td><c:out value="${tireType.amountOnStore}"/></td>
                                    <td><c:out value="${tireType.price}"/></td>
                                    <td><s:text name="tireAmount"/></td>
                                    <td>
                                    <s:hidden name="formType" value="edit"></s:hidden>
                                    <s:submit name="addTire">
                                        <s:param name="tireType.id" value="${tireType.id}"/><f:message key="action.add" /></s:submit>
                                    </td>
                                </tr>
                            </s:form>
                        </c:forEach>
                    </tbody>

                </table>
            </td>
            <td>

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
                                <td><c:out value="${service.price}"/><td>
                                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui" id="register">
                                         <s:hidden name="editOrder.personId" value="${actionBean.order.personId}"></s:hidden>
                        <s:hidden name="editOrder.date" value="${actionBean.order.date}"></s:hidden>
                                        <s:hidden name="editOrder.cartype" value="${actionBean.order.carType}"></s:hidden>
                                        <s:hidden name="formType" value="edit"></s:hidden>
                                        <s:submit name="addService">
                                            <s:param name="service.id" value="${service.id}"/><f:message key="action.add" />
                                        </s:submit>
                                    </s:form>
                                </td>
                            </tr>
                        </c:forEach>
                </tbody>
             </table>
            </td>
        </tr>
    </tbody>
</table>
<hr />
                    
<br />
<br />
<br />
<strong style="color: #707070;"><s:label for="o7"><f:message key="selected.tires"/></s:label></strong>
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
                <td>
                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                        <s:hidden name="editOrder.cartype" value="${actionBean.order.carType}"></s:hidden>
                         <s:hidden name="editOrder.personId" value="${actionBean.order.personId}"></s:hidden>
                        <s:hidden name="editOrder.date" value="${actionBean.order.date}"></s:hidden>
                        <s:hidden name="formType" value="edit"></s:hidden>
                        <s:submit name="removeTire"><s:param name="tireType.id" value="${tire.tireType.id}"/><f:message key="action.remove" /></s:submit>
                    </s:form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
                    
<br />
<br />
<br />    
<strong style="color: #707070;"><s:label for="o6"><f:message key="selected.services"/></s:label></strong>
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
                <td>
                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                        <s:hidden name="editOrder.cartype" value="${actionBean.order.carType}"></s:hidden>
                        <s:hidden name="editOrder.personId" value="${actionBean.order.personId}"></s:hidden>
                        <s:hidden name="editOrder.date" value="${actionBean.order.date}"></s:hidden>
                        <s:hidden name="formType" value="edit"></s:hidden>
                        <s:submit name="removeService"><s:param name="service.id" value="${service.id}"/><f:message key="action.remove" /></s:submit>
                    </s:form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
