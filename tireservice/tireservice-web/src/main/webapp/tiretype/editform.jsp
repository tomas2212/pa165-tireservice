<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td></td>
    <td><s:text id="tt1" name="tireTypeDTO.manufacturer" value="${tireTypeDTO.manufacturer}"/></td>    
    <td><s:text id="tt2" name="tireTypeDTO.type" value="${tireTypeDTO.type}"/></td>
    <td><s:text id="tt3" name="tireTypeDTO.description" value="${tireTypeDTO.description}"/></td>  
    <td><s:text id="tt4" name="tireTypeDTO.tireRimSize" value="${tireTypeDTO.tireRimSize}"/></td>      
    <td><s:text id="tt5" name="tireTypeDTO.amountOnStore" value="${tireTypeDTO.amountOnStore}"/></td> 
    <td><s:text id="tt6" name="tireTypeDTO.price" value="${tireTypeDTO.price}"/></td>
    <td><s:submit name="save">Save</s:submit></td>
    </tr>
<s:hidden id="tt7" name="tireTypeDTO.id"/>
<s:hidden id="tt8" name="tireTypeDTO.active" value="true"/>