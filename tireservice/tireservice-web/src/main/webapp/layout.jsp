<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><f:message key="${titlekey}"/></title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/aui/css/aui-all.css" media="all">
        <!--[if lt IE 9]><link rel="stylesheet" href="${pageContext.request.contextPath}/aui/css/aui-ie.css" media="all"><![endif]-->
        <!--[if IE 9]><link rel="stylesheet" href="${pageContext.request.contextPath}/aui/css/aui-ie9.css" media="all"><![endif]-->
        <script src="${pageContext.request.contextPath}/aui/js/aui-all.js"></script>
        <!--[if lt IE 9]><script src="${pageContext.request.contextPath}/aui/js/aui-ie.js"></script><![endif]-->
  <s:layout-component name="header"/>
</head>
<body class="aui-layout aui-theme-default">
    <header id="header" role="banner">
        <nav class="aui-header aui-dropdown2-trigger-group" role="navigation">
            <div class="aui-header-inner">
                <div class="aui-header-primary">		
                    <ul class="aui-nav">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="list"><f:message key="menu.person.list"/></s:link></li>
                        </sec:authorize>
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="list"><f:message key="menu.tiretype.list"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean" event="list"><f:message key="menu.service.list"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="list"><f:message key="menu.order.list"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="register"><f:message key="menu.order.list.create"/></s:link></li>
                    </ul>
                </div>
               
                    <div class="aui-header-secondary">
                        <ul class="aui-nav"> 
                            <sec:authorize access="not isAuthenticated()">
                                <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.SecurityActionBean" event="login"><f:message key="menu.login"/></s:link></li>
                                <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="register"><f:message key="menu.registration"/></s:link></li>
                             </sec:authorize>
                            <sec:authorize access="isAuthenticated()">
                                <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.SecurityActionBean" event="logout"><f:message key="menu.logout"/></s:link></li>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="register"><f:message key="menu.registration"/></s:link></li>
                                </sec:authorize>
                                <span style="padding: 0 15px">
                                    <img src="images/avatar.png" alt="User Avatar" style="width: 24px; height: 24px; margin-top: 8px">
                                    <span style="vertical-align: super; font-weight: bold;"><sec:authentication property="principal.username" /></span>
                                </span>
                            </sec:authorize>
                        </ul>
                    </div>
               
            </div>
        </nav>
    </header>
    <section id="content" role="main">
        <s:messages/>
        <s:layout-component name="body"/>
    </section>
    <footer id="footer" role="contentinfo" style="background">
        <section class="footer-body" style="background: none;">
            <ul id="aui-footer-list">
                <li>AUI Design Library by 2009-2013 Atlassian</li>
                <li><a href="https://developer.atlassian.com/display/AUI/License">Apache License v2.0</a></li>
                <li><a href="https://developer.atlassian.com/display/AUI/AUI+Release+Notes">Release Notes</a></li>
            </ul>
        </section>
    </footer>
</body>
</html>
</s:layout-definition>