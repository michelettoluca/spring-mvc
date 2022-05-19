<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="nav__wrapper">
    <nav class="nav">
        <span class="nav__logo">Rental Car</span>
        <div class="nav__items">
            <a class="nav__item" href=<c:url value="/"/>>Home</a>
            <security:authorize access="isAuthenticated()">
                <a class="nav__item" href=<c:url value="/vehicles"/>>Vehicles</a>
            </security:authorize>
            <security:authorize access="hasRole('CUSTOMER')">
                <a class="nav__item" href=<c:url value="/reservations"/>>Reservations</a>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <a class="nav__item" href=<c:url value="/admin"/>>Admin</a>
            </security:authorize>
        </div>

        <div class="nav__auth">
            <security:authorize access="isAuthenticated()">
                <a href=<c:url value="/profile"/>>Profile</a>
                <a href=<c:url value="/sign-out"/>>Sign out</a>
            </security:authorize>
            <security:authorize access="!isAuthenticated()">
                <a href=<c:url value="/sign-in"/>>Sign in</a>
                <a href=<c:url value="/sign-up"/>>Sign up</a>
            </security:authorize>
        </div>
    </nav>
</div>