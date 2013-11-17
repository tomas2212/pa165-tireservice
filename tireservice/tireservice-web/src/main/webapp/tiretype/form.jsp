<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="tt1" name="type"/></th>
        <td><s:text id="tt1" name="tireTypeDTO.type"/></td>
    
        <th><s:label for="tt2" name="manufactuer"/></th>
        <td><s:text id="tt2" name="tireTypeDTO.manufacturer"/></td>
    
        <th><s:label for="tt3" name="description"/></th>
        <td><s:text id="tt3" name="tireTypeDTO.description"/></td>
   
        <th><s:label for="tt4" name="amount.on.store"/></th>
        <td><s:text id="tt4" name="tireTypeDTO.amountOnStore"/></td>
    
        <th><s:label for="tt5" name="tire.rim.size"/></th>
        <td><s:text id="tt5" name="tireTypeDTO.tireRimSize"/></td>
    
        <th><s:label for="tt6" name="price"/></th>
        <td><s:text id="tt6" name="tireTypeDTO.price"/></td>
    </tr>
    <s:hidden id="tt7" name="tireTypeDTO.id"/>
    <s:hidden id="tt8" name="tireTypeDTO.active" value="true"/>
</table>