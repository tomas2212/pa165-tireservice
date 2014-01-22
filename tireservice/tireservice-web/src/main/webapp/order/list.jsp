<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="header.orders">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.orders"/></h1>
            </div>
        </div>
            
        <div class="aui-page-panel margin-fix">
            <div class="aui-page-panel-inner">
            <section class="aui-page-panel-content">
                
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <div>
                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" class="aui">
                            <s:label for="personFilter"><f:message key="field.filter.by.person" /></s:label>:
                            <select name="personFilter" class="select" id ="personFilter" >
                                <option value="${person.id}"><f:message key="field.all.users" /></option>
                                <c:forEach items="${actionBean.people}" var="person">
                                    <c:choose> 
                                        <c:when test="${person.id == actionBean.personFilter}">
                                            <option selected="selected" value="${person.id}">${person.firstName} ${person.lastName} (${person.email})</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${person.id}">${person.firstName} ${person.lastName} (${person.email})</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <s:submit name="filter"><f:message key="action.filter"/></s:submit>
                        </s:form>
                    </div>
                </sec:authorize>
                
                <table class="aui aui-table-sortable">
                    <thead>
                        <tr>
                            <th id="order-id">#</th>
                            <th id="order-customer"><f:message key="field.customer"/></th>
                            <th id="order-cartype"><f:message key="field.cartype"/></th>
                            <th id="order-date"><f:message key="field.date"/></th>
                            <th id="order-tires"><f:message key="field.tires"/></th>
                            <th id="order-services"><f:message key="field.services"/></th>
                            <th id="order-price"><f:message key="field.price"/></th>
                            <th id="order-action" class="aui-table-column-unsortable"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.orders}" var="order">
                            <tr>
                                <td><c:out value="${order.person.id}"/></td>
                                <td><c:out value="${order.person.firstName} ${order.person.lastName}"/></td>
                                <td><c:out value="${order.carType}"/></td>
                                <td><c:out value="${order.date}"/></td>
                                <td><ul>
                                <c:forEach items="${order.tires}" var="tire">
                                            <li><c:out value="${tire.tireType.manufacturer} (${tire.amountOnStore})"/></li>
                                    </c:forEach>
                                </ul></td>
                                <td><ul>
                                <c:forEach items="${order.services}" var="service">
                                            <li><c:out value="${service.name} (${service.price} czk)"/></li>
                                    </c:forEach>
                                </ul>
                                </td>
                                <td><c:out value="${order.orderPrice}"/>
                                    
                                </td>
                                <td class="adg-actions">
                                    <button aria-owns="dropdown-button-order${order.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                    <div id="dropdown-button-order${order.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
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
               
                </section>

            </div>
        </div>
    </s:layout-component>
</s:layout-render>