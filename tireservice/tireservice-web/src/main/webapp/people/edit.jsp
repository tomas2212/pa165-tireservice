<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.person.edit">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.person.edit"/></h1>
            </div>
        </div>
       
        <div class="aui-page-panel margin-fix">
            <div class="aui-page-panel-inner">
            <section class="aui-page-panel-content">
            
            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" class="aui">
                <fieldset>
                    <%@include file="edit.jsp"%>
                    <s:submit name="save"><f:message key="action.save"/></s:submit>
                </fieldset>
            </s:form>
            </section>
        </div>
        </div>
    </s:layout-component>
</s:layout-render>