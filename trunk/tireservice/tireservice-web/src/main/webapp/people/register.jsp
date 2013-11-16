<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" var="actionBean"/>
       
        <div class="aui-page-panel margin-fix">
            <div class="aui-page-panel-inner">
            <section class="aui-page-panel-content">
            <h2>Registration</h2>
            
            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" class="aui">
                <fieldset>
                    <%@include file="form.jsp"%>
                    <s:submit name="add">Registrovat</s:submit>
                </fieldset>
            </s:form>
            </section><!-- .aui-page-panel-content -->
        </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>