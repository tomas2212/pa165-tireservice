<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" var="actionBean"/>

<div class="row-fluid">
<div style="float: left; margin-bottom: 5px;">
<s:link beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" event="getAllTypes">gvhgvgh</s:link>
</div>

<table class="table table-striped table-bordered">
<tr>
<th>IDcko</th>
</tr>
<c:forEach items="${actionBean.allTireTypes}" var="item">
<tr>
<td><c:out value="${item.id}"/></td>
</tr>
</c:forEach>
</table>
</div>


<script type="text/javascript">
function setLink(anchor) {
var url = $('#confirmButton').attr('href');
if ($('#confirmButton').data('url') === undefined) {
$('#confirmButton').data('url', url);
}
url = $('#confirmButton').data('url');
var id = $(anchor).data('id');
$('#confirmButton').attr('href', url + id);
}
</script>
<br><hr>
<h4>Empty</h4>