<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.rest.client.ErrorHandlerActionBean" var="actionBean"/>
        
        <div class="aui-message error shadowed" style="width:80%; margin-left: auto; margin-right: auto; margin-top: 2%">
            <p class="title"><f:message key="error"/></p>
            <span class="aui-icon icon-error"></span>
            <p><f:message key="error.message"/></p>
            <p><f:message key="error.cause"/>: ${actionBean.rootCause}</p>
        </div>

    </s:layout-component>
</s:layout-render>
