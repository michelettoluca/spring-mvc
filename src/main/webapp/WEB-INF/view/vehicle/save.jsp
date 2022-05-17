<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--@elvariable id="vehicle" type="com.springmvc.entity.Vehicle"--%>

<div class="form-wrapper">
    <form:form method="POST" action="save" modelAttribute="vehicle">
        <span class="form-header"><tiles:insertAttribute name="formHeader"/></span>
        <form:input type="hidden" path="id" name="id" value="${vehicle.id}"/>

        <div class="form-field">
            <label class="input-label" for="brand">brand</label>
            <form:input class="input-text" type="text" path="brand" name="brand" id="brand"
                        placeholder="brand" value="${vehicle.brand}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="model">model</label>
            <form:input class="input-text" type="text" path="model" name="model" id="model"
                        placeholder="Model" value="${vehicle.model}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="dateOfRegistration">dateOfRegistration</label>
            <form:input type="date" class="input-text" path="dateOfRegistration" value="${vehicle.dateOfRegistration}"
                        name="dateOfRegistration"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="plateNumber">plateNumber</label>
            <form:input class="input-text" type="text" path="plateNumber" name="plateNumber" id="plateNumber"
                        placeholder="Password" value="${vehicle.plateNumber}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="type">type</label>
            <form:input class="input-text" type="text" path="type" name="type" id="type"
                        placeholder="Password" value="${vehicle.type}"/>
        </div>
        <form:button type="submit" class="button-submit"><tiles:insertAttribute name="submitButton"/></form:button>
    </form:form>
</div>