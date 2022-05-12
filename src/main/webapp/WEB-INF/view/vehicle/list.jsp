<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach var="vehicle" items="${vehicles}">
    <a href="<c:url value='/vehicles/${vehicle.plateNumber}'/>">${vehicle.brand} - ${vehicle.model}</a>
</c:forEach>

</body>
</html>
