<table>
            <p>Services in order</p>
            <c:forEach items="${order.services}" var="service">
                                        <tr>
                                            <th>Service Name</th>
                                            <td><c:out value="${service.name}"/></td>
                                            <th>Service Price</th>
                                            <td><c:out value="${service.price}"/><s:submit name="removeService">
                                                <s:param name="service.id" value="${service.id}"/>Remove</s:submit></td>
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
                                            <s:submit name="addService">
                                                <s:param name="service.id" value="${service.id}"/>Add</s:submit>
                                            </td>
                                        </tr>
            </c:forEach>
                     </table>