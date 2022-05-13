<jsp:useBean id="vehicle" scope="request" type="com.springmvc.entity.Vehicle"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<div>
    ${vehicle}
</div>