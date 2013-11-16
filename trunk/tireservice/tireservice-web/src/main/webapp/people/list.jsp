<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" var="actionBean"/>

        <p><f:message key="book.list.allbooks"/></p>
        
        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="book.author"/></th>
                <th><f:message key="book.title"/></th>
                <th><f:message key="book.year"/></th>
                <th><f:message key="book.paperback"/></th>
                <th><f:message key="book.color"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.people}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="edit"><s:param name="person.id" value="${person.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean">
                            <s:hidden name="person.id" value="${person.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </s:layout-component>
</s:layout-render>