<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="p1"><f:message key="person.email"/></s:label></th>
        <td><s:text id="p1" name="person.email"/></td>
    </tr>
    <tr>
        <th><s:label for="p2"><f:message key="person.firstName"/></s:label></th>
        <td><s:text id="p3" name="person.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="p3"><f:message key="person.lastName"/></s:label></th>
        <td><s:text id="p3" name="person.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="p4"><f:message key="person.address"/></s:label></th>
        <td><s:text id="p4" name="person.address"/></td>
    </tr>
    <tr>
        <th><s:label for="p5"><f:message key="person.phoneNumber"/></s:label></th>
        <td><s:text id="p5" name="person.phoneNumber"/></td>
    </tr>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
    <tr>
        <th><s:label for="p6"><f:message key="person.isServiceman"/></s:label></th>
        <td><s:checkbox id="p6" name="person.isServiceman"/></td>
    </tr>
    </sec:authorize>
    <tr>
        <th><s:label for="p7"><f:message key="person.password"/></s:label></th>
        <td><s:password id="p7" name="person.password"/></td>
    </tr>
    <tr>
        <th><s:label for="p8"><f:message key="confirmPassword"/></s:label></th>
        <td><s:password id="p8" name="confirmPassword"/></td>
    </tr>
    <s:hidden id="p9" name="person.active" value="true"/>
    <s:hidden id="p10" name="person.id" />
</table>