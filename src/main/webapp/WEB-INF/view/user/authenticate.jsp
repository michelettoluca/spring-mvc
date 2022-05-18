<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%--@elvariable id="user" type="com.springmvc.entity.User"--%>

<div class="form-wrapper">
    <form action="${pageContext.request.contextPath}/sign-in" method="post" class="form-horizontal">
        <%--        <c:if test="${param.error != null}">--%>
        <%--            <div class="alert alert-danger">--%>
        <%--                <p>Invalid username and password.</p>--%>
        <%--            </div>--%>
        <%--        </c:if>--%>
        <%--        <c:if test="${param.logout != null}">--%>
        <%--            <div class="alert alert-success">--%>
        <%--                <p>You have been logged out successfully.</p>--%>
        <%--            </div>--%>
        <%--        </c:if>--%>
        <div class="input-group input-sm">
            <label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Enter Username" required>
        </div>
        <div class="input-group input-sm">
            <label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter Password"
                   required>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <div class="form-actions">
            <input type="submit"
                   class="btn btn-block btn-primary btn-default" value="Log in">
        </div>
    </form>
    <%--    <form:form method="POST" modelAttribute="user">--%>
    <%--        <span class="form-header">Sign in</span>--%>
    <%--        <div class="form-field">--%>
    <%--            <label class="input-label" for="username">Username</label>--%>
    <%--            <form:input class="input-text" type="text" path="username" name="username" id="username"--%>
    <%--                        placeholder="Username"/>--%>
    <%--        </div>--%>
    <%--        <div class="form-field">--%>
    <%--            <label class="input-label" for="password">Password</label>--%>
    <%--            <form:input class="input-text" type="password" path="password" name="password" id="password"--%>
    <%--                        placeholder="Password"/>--%>
    <%--        </div>--%>
    <%--        <form:button type="submit" class="button-submit">Sign in</form:button>--%>
    <%--    </form:form>--%>
</div>