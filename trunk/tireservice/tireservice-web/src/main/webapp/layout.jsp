<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
  <title><f:message key="${titlekey}"/></title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/docs/img/favicon.png" />
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
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="list"><f:message key="menu.person.list"/></s:link></li>
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean" event="list"><f:message key="menu.service.list"/></s:link></li>
                    </ul>
                </div>  
                <div class="aui-header-secondary">
                    <ul class="aui-nav">
                        <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="register"><f:message key="menu.registration"/></s:link></li>
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