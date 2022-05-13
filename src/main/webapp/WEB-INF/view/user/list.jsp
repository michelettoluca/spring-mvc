<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<div class="form-wrapper">
    <form method="POST" action="<c:url value="/users"/>">
        <span class="form-header"><tiles:insertAttribute name="formHeader"/></span>
        <input type="hidden" name="origin" value="${param.origin}"/>
        <input type="hidden" name="action" value="${param.action}"/>
        <input type="hidden" name="role" value="CUSTOMER"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <input class="input-text" type="text" name="firstName" id="firstName" placeholder="First name"
                   value="${user.firstName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <input class="input-text" type="text" name="lastName" id="lastName" placeholder="Last name"
                   value="${user.lastName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"
                   value="${user.username}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <input class="input-text" type="password" name="password" id="password" placeholder="Password"
                   value="${user.password}"/>
        </div>
        <button class="button-submit">${addOrEditText} customer</button>
    </form>
</div>