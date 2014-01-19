<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="p1"><f:message key="field.email"/></s:label></th>
        <td><s:text id="p1" name="person.email"/></td>
    </tr>
    <tr>
        <th><s:label for="p2"><f:message key="field.first.name"/></s:label></th>
        <td><s:text id="p3" name="person.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="p3"><f:message key="field.last.name"/></s:label></th>
        <td><s:text id="p3" name="person.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="p4"><f:message key="field.address"/></s:label></th>
        <td><s:text id="p4" name="person.address"/></td>
    </tr>
    <tr>
        <th><s:label for="p5"><f:message key="field.phone.number"/></s:label></th>
        <td><s:text id="p5" name="person.phoneNumber"/></td>
    </tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <tr>
        <th><s:label for="p6"><f:message key="field.is.admin"/></s:label></th>
        <td><s:checkbox id="p6" name="person.isServiceman"/></td>
    </tr>
    </sec:authorize>
    <tr>
        <th><s:label for="p7"><f:message key="field.password"/></s:label></th>
        <td><s:password id="p7" name="person.password"/></td>
    </tr>
    <tr>
        <th><s:label for="p8"><f:message key="field.confirm.password"/></s:label></th>
        <td><s:password id="p8" name="confirmPassword"/></td>
    </tr>
    <s:hidden id="p9" name="person.active" value="true"/>
    <s:hidden id="p10" name="person.id" />
</table>