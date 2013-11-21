<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="book.list.title">
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

                    <table class="aui aui-table-interactive aui-table-sortable tablesorter tablesorter-default">
                        <thead>
                            <tr>
                                <th id="tiretype-id">#</th>
                                <th id="tiretype-manufacturer">Manufacturer</th>
                                <th id="tiretype-type">Type</th>
                                <th id="tiretype-description">Description</th>
                                <th id="tiretype-tirerimsize">Tire Rim Size</th>
                                <th id="tiretype-amountonstore">Amount on store</th>
                                <th id="tiretype-price">Price</th>
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

                                        <td class="adg-actions">
                                            <button aria-owns="dropdown-button-tiretype${tireTypeDTO.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                            <div id="dropdown-button-tiretype${tireTypeDTO.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                                <ul class="aui-list-truncate">
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="list"><s:param name="tireTypeDTO.id" value="${tireTypeDTO.id}"/>Edit</s:link>
                                                    <s:link beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean" event="delete"><s:param name="tireTypeDTO.id" value="${tireTypeDTO.id}"/>Delete</s:link>
                                                    </ul>
                                                </div>
                                            </td>
                                        </tr>

                                </c:otherwise>
                            </c:choose>


                        </c:forEach>




                        <c:if test="${actionBean.tireTypeDTO.id == tireTypeDTO.id}" >

                            <s:form beanclass="cz.muni.fi.pa165.tireservice.web.TireTypeActionBean">
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