<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="s1" name="name"/></th>
        <td><s:text id="s1" name="service.name"/></td>
    
        <th><s:label for="s2" name="description"/></th>
        <td><s:text id="s2" name="service.description"/></td>
      
        <th><s:label for="s3" name="price"/></th>
        <td><s:text id="s3" name="service.price"/></td>
    </tr>
    <s:hidden id="tt7" name="service.id"/>
    <s:hidden id="tt8" name="service.active" value="true"/>
</table>