<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>${user.firstName} ${user.lastName}</h1>

<div>
    <c:url var="editUserUrl" value="users/save">
        <c:param name="action" value="edit"/>
        <c:param name="origin" value="profile"/>
        <c:param name="id" value="${user.id}"/>
    </c:url>
    <a href="${editUserUrl}">Edit profile</a>
</div>