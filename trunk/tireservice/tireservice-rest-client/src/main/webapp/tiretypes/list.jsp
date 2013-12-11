<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.services">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.rest.client.TireTypeClientActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1>TIRE TYPES</h1>
            </div>
        </div>           

        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">

                    <table class="aui">
                        <thead>
                            <tr>
                                <th id="tiretype-id">#</th>
                                <th id="tiretype-manufacturer"><f:message key="field.manufacturer"/></th>
                                <th id="tiretype-type"><f:message key="field.type"/></th>
                                <th id="tiretype-description"><f:message key="field.description"/></th>
                                <th id="tiretype-tirerimsize"><f:message key="field.tireRimSize"/></th>
                                <th id="tiretype-amountonstore"><f:message key="field.amount"/></th>
                                <th id="tiretype-price"><f:message key="field.price"/></th>
                                <th id="tiretype-action" class="aui-table-column-unsortable"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${actionBean.allTypes}" var="tireTypeDTO">
                                    <tr>
                                        <td><c:out value="${tireTypeDTO.id}"/></td>
                                        <td><c:out value="${tireTypeDTO.manufacturer}"/></td>
                                        <td><c:out value="${tireTypeDTO.type}"/></td>
                                        <td><c:out value="${tireTypeDTO.description}"/></td>
                                        <td><c:out value="${tireTypeDTO.tireRimSize}"/></td>
                                        <td><c:out value="${tireTypeDTO.amountOnStore}"/></td>
                                        <td><c:out value="${tireTypeDTO.price}"/></td>
                                        </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>