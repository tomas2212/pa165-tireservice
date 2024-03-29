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
                <script type="text/javascript">
                    function rememberFields(action){
                        var carType = document.getElementById("txtCarTypeId").value;
                        var date = document.getElementById("demo-range-always").value;
                        var e = document.getElementById("optionPerson");
                        var person = e.options[e.selectedIndex].value;

                        document.getElementById("editOrder."+action+".carType").value = carType;
                        document.getElementById("editOrder."+action+".date").value = date;
                        document.getElementById("editOrder."+action+".personId").value = person;    
                    }
                </script>

                <table>
                    <thead>
                        <td><f:message key="tireTypeDTO.manufacturer"/></td>
                        <td><f:message key="tireTypeDTO.type"/></td>
                        <td><f:message key="tireTypeDTO.amountOnStore"/></td>
                        <td><f:message key="tireTypeDTO.price"/></td>
                        <td></td>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.allTireTypes}" var="tireType">
                            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                                <s:hidden id="editOrder.addTire.carType" name="carType" ></s:hidden>
                                <s:hidden id="editOrder.addTire.date" name="date" ></s:hidden>
                                <s:hidden id="editOrder.addTire.personId" name="personId" ></s:hidden>
                                <tr>
                                    <td><c:out value="${tireType.manufacturer}"/></td>
                                    <td><c:out value="${tireType.type}"/></td>
                                    <td><c:out value="${tireType.amountOnStore}"/></td>
                                    <td><c:out value="${tireType.price}"/></td>
                                    <td><s:text name="tireAmount"/></td>
                                    <td>
                                    <s:hidden name="formType" value="edit"></s:hidden>
                                    <s:submit name="addTire" onclick="rememberFields('addTire');">
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
                    <td><f:message key="service.name"/></td>
                    <td><f:message key="service.price"/></td>
                    <td></td>
                </thead>
                <tbody>
                    <c:forEach items="${actionBean.allServices}" var="service">
                            <tr>
                                <td><c:out value="${service.name}"/></td>
                                <td><c:out value="${service.price}"/><td>
                                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui" id="register">
                                        <s:hidden id="editOrder.addService.carType" name="carType" ></s:hidden>
                                        <s:hidden id="editOrder.addService.date" name="date" ></s:hidden>
                                        <s:hidden id="editOrder.addService.personId" name="personId" ></s:hidden>
                                        <s:hidden name="formType" value="edit"></s:hidden>
                                        <s:submit name="addService" onclick="rememberFields('addService');">
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
        <td><f:message key="tireTypeDTO.manufacturer"/></td>
        <td><f:message key="tireTypeDTO.type"/></td>
        <td><f:message key="tireTypeDTO.amountOnStore"/></td>
        <td><f:message key="tireTypeDTO.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${order.tires}" var="tire">
            <tr>
                <td><c:out value="${tireType.manufacturer}"/></td>
                <td><c:out value="${tireType.type}"/></td>
                <td><c:out value="${tireType.amountOnStore}"/></td>
                <td><c:out value="${tireType.price}"/></td>
                <td>
                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                        <s:hidden id="editOrder.removeTire.carType" name="carType" ></s:hidden>
                        <s:hidden id="editOrder.removeTire.date" name="date" ></s:hidden>
                        <s:hidden id="editOrder.removeTire.personId" name="personId" ></s:hidden>
                        <s:hidden name="formType" value="edit"></s:hidden>
                        <s:submit name="removeTire" onclick="rememberFields('removeTire');"><s:param name="tireType.id" value="${tire.tireType.id}"/><f:message key="action.remove" /></s:submit>
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
        <td><f:message key="service.name"/></td>
        <td><f:message key="service.price"/></td>
        <td></td>
    </thead>
    <tbody>
        <c:forEach items="${order.services}" var="service">
            <tr>
                <td><c:out value="${service.name}"/></td>
                <td><c:out value="${service.price}"/></td>
                <td>
                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                        <s:hidden id="editOrder.removeService.carType" name="carType" ></s:hidden>
                        <s:hidden id="editOrder.removeService.date" name="date" ></s:hidden>
                        <s:hidden id="editOrder.removeService.personId" name="personId" ></s:hidden>
                        <s:hidden name="formType" value="edit"></s:hidden>
                        <s:submit name="removeService"  onclick="rememberFields('removeService');"><s:param name="service.id" value="${service.id}"/><f:message key="action.remove" /></s:submit>
                    </s:form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
