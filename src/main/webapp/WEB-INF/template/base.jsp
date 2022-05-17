<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap">
    <link href="<c:url value="/css/style.css"/>" rel="stylesheet">

    <title><tiles:insertAttribute name="title"/></title>
</head>
<body>
<tiles:insertAttribute name="navbar"/>

<div class="container">
    <tiles:insertAttribute name="content"/>
</div>
</body>
</html>