<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="header.tiretype">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" var="actionBean"/>

        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.tiretype"/></h1>
            </div>
        </div>

        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">
                    <table class="aui">
                        <thead>
                            <tr>
                                <th id="tiretype-id">#</th>
                                <th id="tiretype-manufacturer"><f:message key="tireTypeDTO.manufacturer"/></th>
                                <th id="tiretype-type"><f:message key="tireTypeDTO.type"/></th>
                                <th id="tiretype-description"><f:message key="tireTypeDTO.description"/></th>
                                <th id="tiretype-tirerimsize"><f:message key="tireTypeDTO.tireRimSize"/></th>
                                <th id="tiretype-amountonstore"><f:message key="tireTypeDTO.amountOnStore"/></th>
                                <th id="tiretype-price"><f:message key="tireTypeDTO.price"/></th>
                                <th id="tiretype-action" class="aui-table-column-unsortable"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${actionBean.tireType}" var="tireTypeDTO">
                                <c:choose>
                                    <c:when test="${actionBean.tireTypeDTO.id == tireTypeDTO.id}"> 
                                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean">
                                            <s:hidden name="tireType.id"/>
                                        <fieldset>
                                            <%@include file="editform.jsp"%>                                            
                                        </fieldset>
                                    </s:form>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><c:out value="${tireTypeDTO.id}"/></td>
                                        <td><c:out value="${tireTypeDTO.manufacturer}"/></td>
                                        <td><c:out value="${tireTypeDTO.type}"/></td>
                                        <td><c:out value="${tireTypeDTO.description}"/></td>
                                        <td><c:out value="${tireTypeDTO.tireRimSize}"/></td>
                                        <td><c:out value="${tireTypeDTO.amountOnStore}"/></td>
                                        <td><c:out value="${tireTypeDTO.price}"/></td>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <td class="adg-actions">
                                            <button aria-owns="dropdown-button-tiretype${tireTypeDTO.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                            <div id="dropdown-button-tiretype${tireTypeDTO.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                                <ul class="aui-list-truncate">
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="edit"><s:param name="tireTypeDTO.id" value="${tireTypeDTO.id}"/><f:message key="action.edit"/></s:link>
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="delete"><s:param name="tireTypeDTO.id" value="${tireTypeDTO.id}"/><f:message key="action.delete"/></s:link>
                                                    </ul>
                                                </div>
                                            </td>
                                            </sec:authorize>
                                        </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <c:if test="${actionBean.tireTypeDTO.id == tireTypeDTO.id}" >
                            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean">
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