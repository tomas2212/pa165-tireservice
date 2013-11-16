<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="header.people">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" var="actionBean"/>
        <div class="aui-page-header">
            <div class="aui-page-header-inner">
                <h1><f:message key="header.people"/></h1>
            </div>
        </div>
        
        <div class="aui-page-panel">
            <div class="aui-page-panel-inner">
                <section class="aui-page-panel-content">

                    <table class="aui aui-table-interactive aui-table-sortable tablesorter tablesorter-default">
                    <thead>
                        <tr>
                            <th id="person-id">#</th>
                            <th id="person-firstname">First Name</th>
                            <th id="person-lastname">Last Name</th>
                            <th id="person-address">Address</th>
                            <th id="person-phonenumber">Phone Number</th>
                            <th id="person-isadmin">Is Admin</th>
                            <th id="person-action" class="aui-table-column-unsortable"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${actionBean.people}" var="person">
                            <tr>
                                <td><c:out value="${person.id}"/></td>
                                <td><c:out value="${person.firstName}"/></td>
                                <td><c:out value="${person.lastName}"/></td>
                                <td><c:out value="${person.address}"/></td>
                                <td><c:out value="${person.phoneNumber}"/></td>
                                <td>    
                                    <s:form beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" class="aui">
                                        <s:checkbox id="b2" name="person.isServiceman" checked="${person.isServiceman}" disabled="disabled"/>
                                    </s:form>
                                </td>
                                <td class="adg-actions">
                                    <button aria-owns="dropdown-button-person${person.id}" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                                    <div id="dropdown-button-person${person.id}" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                                        <ul class="aui-list-truncate">
                                            <s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="edit"><s:param name="person.id" value="${person.id}"/>Edit</s:link>
                                            <s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="delete"><s:param name="person.id" value="${person.id}"/>Delete</s:link>
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