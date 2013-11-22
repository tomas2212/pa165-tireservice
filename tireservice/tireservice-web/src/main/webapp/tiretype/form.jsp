<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td></td>
    <td><s:text id="tt1" name="tireTypeDTO.manufacturer"/></td>    
    <td><s:text id="tt2" name="tireTypeDTO.type"/></td>
    <td><s:text id="tt3" name="tireTypeDTO.description"/></td>  
    <td><s:text id="tt4" name="tireTypeDTO.tireRimSize"/></td>      
    <td><s:text id="tt5" name="tireTypeDTO.amountOnStore"/></td> 
    <td><s:text id="tt6" name="tireTypeDTO.price"/></td>
    <td><s:submit name="add">Submit</s:submit></td>
    </tr>
<s:hidden id="tt7" name="tireTypeDTO.id"/>
<s:hidden id="tt8" name="tireTypeDTO.active" value="true"/>