<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<tr>
    <td/>
    <td><s:text id="s1" name="service.name" value="${service.name}"/>    
    <td><s:text id="s2" name="service.description" value="${service.description}"/> 
    <td><s:text id="s3" name="service.price" value="${service.price}"/> 

    <td><s:submit name="save">Save</s:submit> <td/>
<tr/>
<s:hidden id="s4" name="service.id"/>
<%-- <s:hidden id="s5" name="service.active" value="true"/> --%>