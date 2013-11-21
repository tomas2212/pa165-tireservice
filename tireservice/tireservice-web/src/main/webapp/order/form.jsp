<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
  
<table>
    <tr>
        <th><s:label for="b1" name="first.name"/></th>
        <td><stripes:options-collection collection="${people}" value="personId" label="firstName" name="personId"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="car.type"/></th>
        <td><s:text id="b2" name="car.type" /></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="car.type"/></th>
        <td></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="date"/></th>
        <td><input class="aui-date-picker" id="demo-range-always" type="date" max="2012-01-05" min="2011-12-25" />
            <script>AJS.$(document).ready(function() {
                     AJS.$('#demo-range-always').datePicker({'overrideBrowserDefault': true});
                    });
            </script>
</td>
    </tr>
    <s:hidden id="b2" name="order.active" value="true"/>
    <s:hidden id="b2" name="order.id" />
</table>