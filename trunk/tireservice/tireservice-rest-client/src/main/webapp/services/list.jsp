<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.services">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.rest.client.ServicesClientActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1>SERVICES</h1>
            </div>
        </div>           

        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">

                    <table class="aui">
                        <thead>
                            <tr>
                                <th id="service-id">#</th>
                                <th id="service-name"><f:message key="field.name"/></th>
                                <th id="service-description"><f:message key="field.description"/></th>
                                <th id="service-price"><f:message key="field.price"/></th>
                                <th id="service-action" class="aui-table-column-unsortable"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${actionBean.allServices}" var="service">
                                    <tr>
                                        <td><c:out value="${service.id}"/></td>
                                        <td><c:out value="${service.name}"/></td>
                                        <td><c:out value="${service.description}"/></td>
                                        <td><c:out value="${service.price}"/></td>

                                        <td class="adg-actions">
                                            <button aria-owns="dropdown-button-service${service.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                            <div id="dropdown-button-service${service.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                                <ul class="aui-list-truncate">
                       
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>

                        </c:forEach>

                        </tbody>
                    </table>

                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>