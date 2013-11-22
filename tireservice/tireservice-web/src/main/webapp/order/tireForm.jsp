<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
  <p>Tires already in order</p>
            <table>
            <c:forEach items="${order.tires}" var="tire">
                                        <tr>
                                            <th>Manufacturer</th>
                                            <td><c:out value="${tire.tireType.manufacturer}"/></td>
                                            <th>Price</th>
                                            <td><c:out value="${tire.tireType.price}"/><s:submit name="removeTire">
                                                <s:param name="tireType.id" value="${tire.tireType.id}"/>Remove</s:submit></td>
                                        </tr>
            </c:forEach>
            </table>
                                        <table>
                    <p>Tires to choose from</p>
                    <c:forEach items="${actionBean.allTireTypes}" var="tireType">
                                        <tr>
                                            <th>Manufacturer</th>
                                            <td><c:out value="${tireType.manufacturer}"/></td>
                                            <th>Price</th>
                                            <td><c:out value="${tireType.price}"/></td>
                                            <td><s:text name="tireAmount"/></td>

                                            <td>
                                            <s:submit name="addTire">
                                                <s:param name="tireType.id" value="${tireType.id}"/>Add</s:submit>
                                            </td>
                                        </tr>
            </c:forEach>
                     </table>
