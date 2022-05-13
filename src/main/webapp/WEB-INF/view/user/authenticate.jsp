<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="form-wrapper">
    <form:form method="POST">
        <span class="form-header">Sign in</span>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <form:input class="input-text" type="text" path="username" name="username" id="username"
                        placeholder="Username"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <form:input class="input-text" type="password" path="password" name="password" id="password"
                        placeholder="Password"/>
        </div>
        <button class="button-submit">Sign in</button>
    </form:form>
</div>