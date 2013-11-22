<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
  
<p>Person</p>
<c:if test="${not empty actionBean.people}">
            <select name="personId" >
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
   </c:if>        
            <table>
    <tr>
        <th><s:label for="b1" name="car.type"/></th>
        <td><s:text id="b1" name="carType" value="${actionBean.order.carType}"/></td>
    </tr>
    <tr>
        <th><s:label for="b1" name="date"/></th>
        <td><input class="aui-date-picker" id="demo-range-always" type="date" name="date" value="${actionBean.convertDateToString()}"/>
        <script>
            AJS.$(document).ready(function() {
   AJS.$('#demo-range-always').datePicker({'overrideBrowserDefault': true});
});
        </script>
        </td>
    </tr>
    <s:hidden id="b3" name="order.id" />
            
            <%@include file="servicesForm.jsp"%>
            <%@include file="tireForm.jsp"%>
            

</table>
            
            