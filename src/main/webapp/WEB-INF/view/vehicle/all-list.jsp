<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:choose>
    <c:when test="${vehicles.isEmpty()}">
        <i>No vehicles found</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>id</th>
                <th>brand</th>
                <th>model</th>
                <th>dateOfRegistration</th>
                <th>plateNumber</th>
                <th>type</th>
            </tr>
            <c:forEach var="availableVehicle" items="${vehicles}">
                <c:url var="editVehicleUrl" value="/vehicles/save">
                    <c:param name="action" value="edit"/>
                    <c:param name="id" value="${availableVehicle.id}"/>
                </c:url>
                <tr>
                    <td>${availableVehicle.id}</td>
                    <td>${availableVehicle.brand}</td>
                    <td>${availableVehicle.model}</td>
                    <td>${availableVehicle.dateOfRegistration}</td>
                    <td>${availableVehicle.plateNumber}</td>
                    <td>${availableVehicle.type}</td>
                    <td>
                        <form:form method="POST" action="${pageContext.request.contextPath}/vehicles/delete">
                            <input type="hidden" name="id" value="${availableVehicle.id}">
                            <input type="submit" value="Delete">
                        </form:form>
                    </td>
                    <td>
                        <a href="${editVehicleUrl}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>