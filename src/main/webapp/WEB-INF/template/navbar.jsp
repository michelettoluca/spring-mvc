<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="nav__wrapper">
    <nav class="nav">
        <span class="nav__logo">Rental Car</span>
        <div class="nav__items">
            <a class="nav__item" href=<c:url value="/"/>>Home</a>
            <c:if test="${sessionScope.userRole != null}">
                <a class="nav__item" href=<c:url value="/vehicles"/>>Vehicles</a>
            </c:if>
            <c:if test="${sessionScope.userRole == 'CUSTOMER'}">
                <a class="nav__item" href=<c:url value="/reservatons"/>>Reservations</a>
            </c:if>
            <c:if test="${sessionScope.userRole == 'ADMIN'}">
                <a class="nav__item" href=<c:url value="/admin"/>>Admin</a>
            </c:if>
        </div>

        <div class="nav__auth">
            <c:choose>
                <c:when test="${sessionScope.userId != null}">
                    <a href=<c:url value="/profile"/>>Profile</a>
                    <a href=<c:url value="/sign-out"/>>Sign out</a>
                </c:when>
                <c:otherwise>
                    <a href=<c:url value="/sign-in"/>>Sign in</a>
                    <a href=<c:url value="/sign-up"/>>Sign up</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>