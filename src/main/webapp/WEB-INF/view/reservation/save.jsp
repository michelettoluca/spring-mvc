<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="reservation" type="com.springmvc.entity.Reservation"--%>

<c:choose>
    <c:when test="${param.action == 'add'}">
        <c:set var="inputType" value="hidden"/>
    </c:when>
    <c:when test="${param.action == 'edit'}">
        <c:set var="inputType" value="date"/>
    </c:when>
</c:choose>

<div class="form-wrapper">
    <form:form method="POST" action="${pageContext.request.contextPath}/reservations/save" modelAttribute="reservation">
        <span class="form-header">Edit reservation</span>
        <form:input path="id" type="hidden" name="id" value="${reservation.id}"/>
        <form:input path="user.id" type="hidden" name="user" value="${reservation.user.id}"/>
        <form:input path="vehicle.id" type="hidden" name="vehicle" value="${reservation.vehicle.id}"/>
        <form:input path="status" type="hidden" name="status" value="PENDING"/>
        <div class="form-field">
            <label class="input-label">Vehicle</label>
            <span>${reservation.vehicle.brand} - ${reservation.vehicle.model}</span>
        </div>
        <div class="form-field">
            <label class="input-label">Plate #</label>
            <span>${reservation.vehicle.plateNumber}</span>
        </div>
        <hr>

        <div class="form-field">
            <label class="input-label" for="beginsAt">From</label>

            <c:if test="${param.action == 'add'}">
                <span>${reservation.beginsAt}</span>
            </c:if>
            <form:input path="beginsAt" class="input-text" type="${inputType}" name="beginsAt" id="beginsAt"
                        value="${reservation.beginsAt}"/>

        </div>
        <div class="form-field">
            <label class="input-label" for="endsAt">To</label>

            <c:if test="${param.action == 'add'}">
                <span>${reservation.endsAt}</span>
            </c:if>
            <form:input path="endsAt" class="input-text" type="${inputType}" name="endsAt" id="endsAt"
                        value="${reservation.endsAt}"/>
        </div>


        <button class="button-submit">Edit reservation</button>
    </form:form>
</div>