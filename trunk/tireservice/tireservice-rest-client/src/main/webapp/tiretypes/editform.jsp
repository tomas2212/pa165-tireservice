<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td></td>
    <td><s:text id="tt1" name="tireType.manufacturer" value="${tireType.manufacturer}"/></td>    
    <td><s:text id="tt2" name="tireType.type" value="${tireType.type}"/></td>
    <td><s:text id="tt3" name="tireType.description" value="${tireType.description}"/></td>  
    <td><s:text id="tt4" name="tireType.tireRimSize" value="${tireType.tireRimSize}"/></td>      
    <td><s:text id="tt5" name="tireType.amountOnStore" value="${tireType.amountOnStore}"/></td> 
    <td><s:text id="tt6" name="tireType.price" value="${tireType.price}"/></td>
    <td><s:submit name="save"><f:message key="action.save"/></s:submit></td>
    </tr>
<s:hidden id="tt7" name="tireType.id"/>