<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

      
<table>
    <tr>
        <th><s:label for="o1"><f:message key="field.customer" /></s:label></th>
        <td>
            <select name="personId" class="select" id ="optionPerson" >
                <c:forEach items="${actionBean.people}" var="person">
                    <c:choose> 
                        <c:when test="${person.id == actionBean.order.person.id}">
                            <option selected="selected" value="${person.id}">${person.firstName} ${person.lastName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${person.id}">${person.firstName} ${person.lastName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <th><s:label for="o2"><f:message key="field.cartype" /></s:label></th>
        <td><s:text id="txtCarTypeId" name="carType" value="${actionBean.order.carType}"/></td>
    </tr>
    <tr>
        <th><s:label for="o3"><f:message key="field.date" /></s:label></th>
        <td><input class="aui-date-picker" id="demo-range-always" type="date" name="date" value="${actionBean.convertDateToString()}"/>
        <script>
            AJS.$(document).ready(function() {
                AJS.$('#demo-range-always').datePicker({'overrideBrowserDefault': true});
             });
        </script>
        </td>
    </tr>
    <s:hidden id="o4" name="order.id" />
</table>
            
            