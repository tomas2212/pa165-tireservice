<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td></td>
    <td><s:text id="tt1" name="tireType.manufacturer"/></td>    
    <td><s:text id="tt2" name="tireType.type"/></td>
    <td><s:text id="tt3" name="tireType.description"/></td>  
    <td><s:text id="tt4" name="tireType.tireRimSize"/></td>      
    <td><s:text id="tt5" name="tireType.amountOnStore"/></td> 
    <td><s:text id="tt6" name="tireType.price"/></td>
    <td><s:submit name="create"><f:message key="action.create.tiretype"/></s:submit></td>
    </tr>
<s:hidden id="tt7" name="tireType.id"/>
<s:hidden id="tt8" name="tireType.active" value="true"/>