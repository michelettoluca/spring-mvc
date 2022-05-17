<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${reservations.isEmpty()}">
        <i>No reservations found</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Vehicle</th>
                <th>beginsAt</th>
                <th>endsAt</th>
                <th>status</th>
            </tr>
            <c:forEach var="tmpReservation" items="${reservations}">
                <tr>
                    <td>${tmpReservation.vehicle.brand} ${tmpReservation.vehicle.model}</td>
                    <td>${tmpReservation.beginsAt}</td>
                    <td>${tmpReservation.endsAt}</td>
                    <td>${tmpReservation.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>