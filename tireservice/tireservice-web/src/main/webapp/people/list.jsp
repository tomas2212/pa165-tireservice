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
            <th id="basic-number">#</th>
            <th id="basic-fname">First name</th>
            <th id="basic-lname">Last name</th>
            <th id="basic-username">Username</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td headers="basic-number">1</td>
            <td headers="basic-fname">Matt</td>
            <td headers="basic-lname">Bond</td>
            <td headers="basic-username">mbond</td>
        </tr>
        <tr>
            <td headers="basic-number">2</td>
            <td headers="basic-fname">Ross</td>
            <td headers="basic-lname">Chaldecott</td>
            <td headers="basic-username">rchaldecott</td>
        </tr>
        <tr>
            <td headers="basic-number">3</td>
            <td headers="basic-fname">Henry</td>
            <td headers="basic-lname">Tapia</td>
            <td headers="basic-username">htapia</td>
        </tr>
    </tbody>
</table>
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    <table class="aui aui-table-interactive aui-table-sortable tablesorter tablesorter-default">
        <thead>
        <tr class="tablesorter-headerRow">
            <th tabindex="0" data-column="0" class="tablesorter-header" unselectable="on" style="-webkit-user-select: none;"><div class="tablesorter-header-inner"><span class="aui-table-header-content">Author</span></div></th>
            <th tabindex="0" data-column="1" class="tablesorter-header" unselectable="on" style="-webkit-user-select: none;"><div class="tablesorter-header-inner"><span class="aui-table-header-content">Title</span></div></th>
            <th class="aui-table-column-unsortable tablesorter-header sorter-false" data-column="2" tabindex="0" unselectable="on" style="-webkit-user-select: none;"><div class="tablesorter-header-inner">Actions</div></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="#">Joel Unger</a></td>
            <td>DT-101 Update the new logo with some improved styles</td>
            <td class="adg-actions">
                <button aria-owns="dropdown-button1" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                <div id="dropdown-button1" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                    <ul class="aui-list-truncate">
                        <li><a href="#" class="active">Edit</a></li>
                        <li><a href="#">Delete</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        <tr>
            <td><a href="#">Ross Chaldecott</a></td>
            <td>DT-120 Create new styles for pages</td>
            <td class="adg-actions">
                <button aria-owns="dropdown-button1" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                <div id="dropdown-button1" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                    <ul class="aui-list-truncate">
                        <li><a href="#" class="active">Edit</a></li>
                        <li><a href="#">Delete</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        <tr>
            <td><a href="#">Jerry Gordinier</a></td>
            <td>DT-124 Added panels to the right most page containers</td>
            <td class="adg-actions">
                <button aria-owns="dropdown-button1" aria-haspopup="true" class="aui-button aui-button-subtle aui-dropdown2-trigger" data-container="#adg-table-1"><span class="aui-icon aui-icon-small aui-iconfont-configure">Configure</span></button>
                <div id="dropdown-button1" class="aui-dropdown2 aui-style-default" aria-hidden="false" data-dropdown2-alignment="right">
                    <ul class="aui-list-truncate">
                        <li><a href="#" class="active">Edit</a></li>
                        <li><a href="#">Delete</a></li>
                    </ul>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
                    
                    
                    
                    
                    
                    <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="book.author"/></th>
                <th><f:message key="book.title"/></th>
                <th><f:message key="book.year"/></th>
                <th><f:message key="book.paperback"/></th>
                <th><f:message key="book.color"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach items="${actionBean.people}" var="person">
                <tr>
                    <td>${person.id}</td>
                    <td><c:out value="${person.firstName}"/></td>
                    <td><c:out value="${person.lastName}"/></td>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean" event="edit"><s:param name="person.id" value="${person.id}"/>edit</s:link>
                    </td>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.tireservice.web.PeopleActionBean">
                            <s:hidden name="person.id" value="${person.id}"/>
                            <s:submit name="delete"><f:message key="book.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

                </section><!-- .aui-page-panel-content -->

            </div><!-- .aui-page-panel-inner -->
        </div>
    </s:layout-component>
</s:layout-render>