<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="availableVehicles" scope="request" type="java.util.List<com.springmvc.entity.Vehicle>"/>

<form class="reservation__date-range" method="GET">
    <div class="form-field">
        <label class="input-label" for="from">From</label>
        <input class="input-text" type="date" name="from" id="from" value="${param.from}"/>
    </div>
    <div class="form-field">
        <label class="input-label" for="to">To</label>
        <input class="input-text" type="date" name="to" id="to" value="${param.to}"/>
    </div>
    <input type="submit" value="Check">
</form>
<c:choose>
    <c:when test="${availableVehicles.isEmpty()}">
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
            <c:forEach var="availableVehicle" items="${availableVehicles}">
                <c:url var="addReservationUrl" value="/reservations/save">
                    <c:param name="action" value="add"/>
                    <c:param name="vehicleId" value="${availableVehicle.id}"/>
                    <c:param name="beginsAt" value="${param.from}"/>
                    <c:param name="endsAt" value="${param.to}"/>
                </c:url>
                <tr>
                    <td>${availableVehicle.id}</td>
                    <td>${availableVehicle.brand}</td>
                    <td>${availableVehicle.model}</td>
                    <td>${availableVehicle.dateOfRegistration}</td>
                    <td>${availableVehicle.plateNumber}</td>
                    <td>${availableVehicle.type}</td>

                    <td>
                        <a href="${addReservationUrl}">
                            Book reservation
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>