<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--@elvariable id="user" type="com.springmvc.entity.User"--%>

<div class="form-wrapper">
    <form:form method="POST" modelAttribute="user">
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
        <form:button type="submit" class="button-submit">Sign in</form:button>
    </form:form>
</div>