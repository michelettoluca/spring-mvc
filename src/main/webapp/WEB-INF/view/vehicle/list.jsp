<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <b>Vehicles</b>
    <security:authorize access="hasRole('ADMIN')">
        <a href="vehicles/save?action=add">Add new vehicle</a>
        <jsp:include page="all-list.jsp"/>
    </security:authorize>
    <security:authorize access="hasRole('CUSTOMER')">
        <jsp:include page="available-list.jsp"/>
    </security:authorize>
</div>