<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="book.author"/></th>
        <td><s:text id="b1" name="book.author"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="book.title"/></th>
        <td><s:text id="b2" name="book.title"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="book.paperback"/></th>
        <td><s:checkbox id="b3" name="book.paperback"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="book.year"/></th>
        <td><s:text id="b5" name="book.year" size="4"/></td>
    </tr>
</table>