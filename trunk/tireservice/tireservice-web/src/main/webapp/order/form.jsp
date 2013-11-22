<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
  
<p>Person</p>
            <stripes:select name="actionBean.personId">
            <stripes:options-collection collection="${actionBean.people}" value="Id" label="firstName"/>
            </stripes:select>
            
            <table>
    <tr>
        <th><s:label for="b1" name="car.type"/></th>
        <td><s:text id="b1" name="order.carType"/></td>
    </tr>
    <tr>
        <th><s:label for="b1" name="date"/></th>
        <td><input class="aui-date-picker" id="demo-range-always" type="date" max="2012-01-05" min="2011-12-25" name="order.date"/>
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
            
            