<table>
            <p>Services in order</p>
            <c:forEach items="${order.services}" var="service">
                                        <tr>
                                            <th>Service Name</th>
                                            <td><c:out value="${service.name}"/></td>
                                            <th>Service Price</th>
                                            <td><c:out value="${service.price}"/><s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="removeService">
                                                <s:param name="service.id" value="${service.id}"/>Remove</s:link></td>
                                        </tr>
            </c:forEach>
            </table>
                                        <table>
                    <p>Services to choose from</p>
                    <c:forEach items="${actionBean.allServices}" var="service">
                                        <tr>
                                            <th>Service Name</th>
                                            <td><c:out value="${service.name}"/></td>
                                            <th>Service Price</th>
                                            <td><c:out value="${service.price}"/>
                                            <s:link beanclass="cz.muni.fi.pa165.tireservice.web.OrderActionBean" event="addService">
                                                <s:param name="service.id" value="${service.id}"/>Add</s:link>
                                            </td>
                                        </tr>
            </c:forEach>
                     </table>