<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="header.services">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.services"/></h1>
            </div>
        </div>           

        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">

                    <table class="aui">
                        <thead>
                            <tr>
                                <th id="service-id">#</th>
                                <th id="service-name"><f:message key="service.name"/></th>
                                <th id="service-description"><f:message key="service.description"/></th>
                                    <%--<th id="service-active">Activity status</th> --%>
                                <th id="service-price"><f:message key="service.price"/></th>
                                <th id="service-action" class="aui-table-column-unsortable"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${actionBean.services}" var="service">

                                <c:choose>
                                    <c:when test="${actionBean.service.id == service.id}"> 

                                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean">
                                            <s:hidden name="service.id"/>
                                        <fieldset>
                                            <%@include file="editinlist.jsp"%>                                            
                                        </fieldset>
                                    </s:form>

                                </c:when>
                                <c:otherwise> 

                                    <tr>
                                        <td><c:out value="${service.id}"/></td>
                                        <td><c:out value="${service.name}"/></td>
                                        <td><c:out value="${service.description}"/></td>
                                        <td><c:out value="${service.price}"/></td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <td class="adg-actions">
                                                <button aria-owns="dropdown-button-service${service.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                                <div id="dropdown-button-service${service.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                                    <ul class="aui-list-truncate">
                                                        <s:link beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean" event="edit"><s:param name="service.id" value="${service.id}"/><f:message key="action.edit"/></s:link>
                                                        <s:link beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean" event="delete"><s:param name="service.id" value="${service.id}"/><f:message key="action.delete"/></s:link>
                                                        </ul>
                                                    </div>
                                                </td>
                                        </sec:authorize>
                                    </tr>

                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:if test="${actionBean.service.id == service.id}" >

                                <s:form beanclass="cz.muni.fi.pa165.tireservice.web.ServiceActionBean">

                                    <%@include file="form.jsp"%>

                                </s:form> 

                            </c:if>
                        </sec:authorize>

                        </tbody>
                    </table>

                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>