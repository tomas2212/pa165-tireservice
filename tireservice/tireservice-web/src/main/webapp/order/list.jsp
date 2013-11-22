<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.order">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.order"/></h1>
            </div>
        </div>
        
        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">
                    <li><s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="register">fgdcxgfgsdfgsdgsdfgdsffgf</s:link></li>
                    <table class="aui aui-table-interactive aui-table-sortable tablesorter tablesorter-default">
                    <thead>
                        <tr>
                            <th id="order-id">#</th>
                            <th id="person-firstname">First Name</th>
                            <th id="person-lastname">Last Name</th>
                            <th id="order-cartype">Car Type</th>
                            <th id="order-date">Date</th>
                            <th id="order-isactive">Is Active</th>
                            <th id="order-services">Services</th>
                            <th id="order-tires">Tires</th>
                            <th id="order-action" class="aui-table-column-unsortable"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.orders}" var="order">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${order.person.firstName}"/></td>
                                <td><c:out value="${order.person.lastName}"/></td>
                                <td><c:out value="${order.carType}"/></td>
                                <td><c:out value="${order.date}"/></td>
                                <td><c:out value="${order.isActive}"/></td>
                                <td><tbody>
                                    <c:forEach items="${order.services}" var="service">
                                        <tr>
                                            <td><c:out value="${service.name}"/></td>
                                            <td><c:out value="${service.price}"/></td>
                                        </tr>
                                    </c:forEach>
                                </tbody></td>
                                <td><tbody>
                                    <c:forEach items="${order.tires}" var="tire">
                                        <tr>
                                            <td><c:out value="${tire.tireType.manufacturer}"/></td>
                                            <td><c:out value="${tire.tireType.price}"/></td>
                                        </tr>
                                    </c:forEach>    
                                </tbody></td>
                                <td class="adg-actions">
                                    <button aria-owns="dropdown-button-person${order.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                    <div id="dropdown-button-person${person.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                        <ul class="aui-list-truncate">
                                            <s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="edit"><s:param name="order.id" value="${order.id}"/>Edit</s:link>
                                            <s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="delete"><s:param name="order.id" value="${order.id}"/>Delete</s:link>
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