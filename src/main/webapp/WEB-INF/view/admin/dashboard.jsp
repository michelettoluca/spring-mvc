<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="customers" scope="request" type="java.util.List<com.springmvc.entity.User>"/>

<div class="content">
    <div class="customers">
        <span class="customers__header">Customers</span>
        <form method="GET" action="admin">
            <input class="input-text" type="text" name="query" placeholder="Search customer">
            <input class="--hidden" type="submit"/>
        </form>
        <c:url var="addUserUrl" value="/users/save">
            <c:param name="origin" value="admin"/>
            <c:param name="action" value="add-customer"/>
        </c:url>
        <a href="${addUserUrl}" class="button--dashed">
            + Add customer
        </a>
        <div class="customers__list">
            <c:choose>
                <c:when test="${customers.isEmpty()}">
                    <i>No customers found</i>
                </c:when>
                <c:otherwise>
                    <c:forEach var="tmpCustomer" items="${customers}">
                        <c:url var="customerUrl" value="/admin">
                            <c:param name="userId" value="${tmpCustomer.id}"/>
                        </c:url>
                        <a class="customers__list__item"
                           href="${customerUrl}"
                           data-active="${param.userId == tmpCustomer.id}">
                            <span class=" customer__fullname">
                                    ${tmpCustomer.firstName} ${tmpCustomer.lastName}
                            </span>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <jsp:include page="customer-details.jsp"/>
</div>
