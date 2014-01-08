<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.login">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.SecurityActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.login"/></h1>
            </div>
        </div>
        
        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">
                    <c:if test="${not empty actionBean.error}">
                        <div class="errorblock">
                            <f:message key="login.error" />
                        </div>
                    </c:if>
                    <c:url value="/j_spring_security_check" var="checkUrl"/>
                    <form class="aui" action="${checkUrl}" method="POST"
                        <fieldset>
                            <div class="field-group">
                                <label for="inputUsername">Email</label>
                                <input type="text" id="inputUsername" name="j_username" />
                            </div>
                            <div class="field-group">
                                <label for="inputUsername">Password</label>
                                <input type="password" id="inputPassword" name="j_password" />
                            </div>
                        </fieldset>
                        <div class="buttons-container">
                            <div class="buttons">
                                <button type="submit" class="btn"><f:message key="login.button.login"/></button>
                                <a class="cancel" href="#">Cancel</a>
                            </div>
                        </div>
                    </form>
               
                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>