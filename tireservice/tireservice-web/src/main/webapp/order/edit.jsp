<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.edit">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.registration"/></h1>
            </div>
        </div>
       
        <div class="aui-page-panel margin-fix">
            <div class="aui-page-panel-inner">
            <section class="aui-page-panel-content">
                <s:errors/>
                
                <%@include file="itemsEdit.jsp"%>
            
                <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui" id="edit">
                    <fieldset>
                        <s:hidden name="formType" value="edit"></s:hidden>
                        <%@include file="form.jsp"%>
                     <s:submit name="save"><f:message key="action.edit"/></s:submit>   
                    </fieldset>
                </s:form>
            </section>
        </div>
        </div>
    </s:layout-component>
</s:layout-render>