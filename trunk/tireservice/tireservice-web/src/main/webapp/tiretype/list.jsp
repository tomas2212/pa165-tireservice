<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" var="actionBean"/>

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
            <c:forEach items="${actionBean.tireType}" var="tireType">
                <tr>
                    <td>${tireType.id}</td>
                    <td><c:out value="${tireType.manufacturer}"/></td>
                    <td><c:out value="${tireType.description}"/></td>
                    <td><c:out value="${tireType.tireRimSize}"/></td>
                    <td><c:out value="${tireType.type}"/></td>
                    <td><c:out value="${tireType.price}"/></td>
                    <td><c:out value="${tireType.amountOnStore}"/></td>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="edit"><s:param name="tireType.id" value="${tireType.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean">
                            <s:hidden name="tireType.id" value="${tireType.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        ahoj ${actionBean.tireType}
        
        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean">
            <fieldset><legend><f:message key="book.list.newbook"/></legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Create new tire type</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>