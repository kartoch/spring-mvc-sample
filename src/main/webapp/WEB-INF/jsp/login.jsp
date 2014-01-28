<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="login.page.title"/></title>
    </head>
    <body>
        <h1><spring:message code="login.main.header"/></h1>
        <form:form commandName="user" method="POST">
            <table id="login" class="box">
                <tr>
                    <td><form:errors path="*"/></td>
                </tr>
                <tr>
                    <td><spring:message code="domain.user.username"/></td>
                    <td><form:input path="username"/></td>
                </tr>
                <tr>
                    <td><spring:message code="domain.user.password"/></td>
                    <td><form:password path="password"/></td></tr>
                <tr>
                    <td><input type="submit" value="<spring:message code="login.form.submit"/>"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>