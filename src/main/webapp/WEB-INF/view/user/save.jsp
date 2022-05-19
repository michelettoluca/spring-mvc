<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%--@elvariable id="user" type="com.springmvc.entity.User"--%>
<c:choose>
    <c:when test="${user.role == 'ADMIN'}">
        <c:set var="role" value="ADMIN"/>
    </c:when>
    <c:otherwise>
        <c:set var="role" value="CUSTOMER"/>
    </c:otherwise>
</c:choose>

<div class="form-wrapper">
    <form:form method="POST" action="${pageContext.request.contextPath}/users/save" modelAttribute="user">
        <span class="form-header"><tiles:insertAttribute name="formHeader"/></span>
        <input type="hidden" name="origin" value="${param.origin}"/>
        <form:input type="hidden" path="role" name="role" value="${role}"/>
        <form:input type="hidden" path="id" name="id" value="${param.id}"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <form:input class="input-text" type="text" path="firstName" name="firstName" id="firstName"
                        placeholder="First name" value="${user.firstName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <form:input class="input-text" type="text" path="lastName" name="lastName" id="lastName"
                        placeholder="Last name" value="${user.lastName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <form:input class="input-text" type="text" path="username" name="username" id="username"
                        placeholder="Username" value="${user.username}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <form:input class="input-text" type="password" path="password" name="password" id="password"
                        placeholder="Password" value="${user.password}"/>
        </div>
        <form:button type="submit" class="button-submit"><tiles:insertAttribute name="submitButton"/></form:button>
    </form:form>
</div>