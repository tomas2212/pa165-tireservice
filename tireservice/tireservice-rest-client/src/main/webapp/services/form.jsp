<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td/>
    <td><s:text id="s1" name="service.name"/>   
    <td><s:text id="s2" name="service.description"/>
    <td><s:text id="s3" name="service.price"/> 
    <s:submit name="create"><f:message key="action.create.service"/></s:submit>
    
    <td/>
<tr/>
<s:hidden id="s4" name="service.id"/>
<s:hidden id="s5" name="service.active" value="true"/>