<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <b>Vehicles</b>
    
    <a href="vehicles/save?action=add">Add new vehicle</a>
    <jsp:include page="all-list.jsp"/>
    <jsp:include page="available-list.jsp"/>
    <%--    <c:choose>--%>
    <%--        <c:when test="${userRole == 'ADMIN'}">--%>
    <%--        </c:when>--%>
    <%--        <c:when test="${userRole == 'CUSTOMER'}">--%>
    <%--        </c:when>--%>
    <%--    </c:choose>--%>
</div>