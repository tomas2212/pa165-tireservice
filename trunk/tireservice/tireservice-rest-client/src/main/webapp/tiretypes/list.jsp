<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.services">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" var="actionBean"/>
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
                                <th id="tiretype-manufacturer"><f:message key="tireType.manufacturer"/></th>
                                <th id="tiretype-type"><f:message key="tireType.type"/></th>
                                <th id="tiretype-description"><f:message key="tireType.description"/></th>
                                <th id="tiretype-tirerimsize"><f:message key="tireType.tireRimSize"/></th>
                                <th id="tiretype-amountonstore"><f:message key="tireType.amountOnStore"/></th>
                                <th id="tiretype-price"><f:message key="tireType.price"/></th>
                                <th id="tiretype-action" class="aui-table-column-unsortable"></th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${actionBean.allTypes}" var="tireType">
                                <c:choose>
                                    <c:when test="${actionBean.tireType.id == tireType.id}"> 
                                        <s:form beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean">
                                            <s:hidden name="tireType.id"/>
                                        <fieldset>
                                            <%@include file="editform.jsp"%>                                            
                                        </fieldset>
                                    </s:form>
                                </c:when>
                                <c:otherwise>

                                    <tr>
                                        <td><c:out value="${tireType.id}"/></td>
                                        <td><c:out value="${tireType.manufacturer}"/></td>
                                        <td><c:out value="${tireType.type}"/></td>
                                        <td><c:out value="${tireType.description}"/></td>
                                        <td><c:out value="${tireType.tireRimSize}"/></td>
                                        <td><c:out value="${tireType.amountOnStore}"/></td>
                                        <td><c:out value="${tireType.price}"/></td>
                                        <td class="adg-actions">
                                            <button aria-owns="dropdown-button-tiretype${tireType.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                            <div id="dropdown-button-tiretype${tireType.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                                <ul class="aui-list-truncate">
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" event="edit"><s:param name="tireType.id" value="${tireType.id}"/><f:message key="action.edit"/></s:link>
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" event="delete"><s:param name="tireType.id" value="${tireType.id}"/><f:message key="action.delete"/></s:link>
                                                    </ul>
                                                </div>
                                            </td>


                                        </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <c:if test="${actionBean.tireType.id == tireType.id}" >
                            <s:form beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean">
                                <%@include file="form.jsp"%>
                            </s:form> 
                        </c:if>
                        </tbody>
                    </table>

                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>