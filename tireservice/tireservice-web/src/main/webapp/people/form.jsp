<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="email"/></th>
        <td><s:text id="b1" name="person.email"/></td>
    </tr>
    <tr>
        <th><s:label for="b1" name="first.name"/></th>
        <td><s:text id="b1" name="person.firstName"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="last.name"/></th>
        <td><s:text id="b2" name="person.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="address"/></th>
        <td><s:text id="b2" name="person.address"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="phone.number"/></th>
        <td><s:text id="b2" name="person.phoneNumber"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="is.serviceman"/></th>
        <td><s:checkbox id="b2" name="person.isServiceman"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="password"/></th>
        <td><s:password id="b2" name="person.password"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="confirm.password" /></th>
        <td><s:password id="b2" name="confirmPassword"/></td>
    </tr>
    <s:hidden id="b2" name="person.active" value="true"/>
    <s:hidden id="b2" name="person.id" />
</table>