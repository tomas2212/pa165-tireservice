<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
  
      
<table>
    <tr>
        <th><s:label for="o1" name="field.customer"/></th>
        <td>
            <select name="personId" class="select">
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
        <th><s:label for="o2" name="car.type"/></th>
        <td><s:text id="o2" name="carType" value="${actionBean.order.carType}"/></td>
    </tr>
    <tr>
        <th><s:label for="o3" name="date"/></th>
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
<br />
<br />
<br />
<%@include file="tireForm.jsp"%>
<br />
<br />
<br />
<%@include file="servicesForm.jsp"%>
            
            