<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- include header.jsp and set page title --%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="View users page"/>
</jsp:include>

<h1><spring:message code="view.users.main.header"/></h1>

<%-- Print model connectedUser --%>
<h4><spring:message code="view.users.greetings"/> ${connectedUser} !</h4>

<table id="user-list">
    <thead>
        <tr>
            <td><spring:message code="domain.user.username"/></td>
        </tr>
    </thead>
    <tbody/>
</table>

<ul>
    <li><a href="newUser"><spring:message code="new.user.main.header"/></a></li>
    <li><a href="login?logout"><spring:message code="view.users.main.logout"/></a></li>
</ul>

<%-- AJAX event to load the list of users and insert it in table with id #user-list --%>
<script>
    window.onload = function() {
        $.getJSON("/api/users/", function (data) {
            var tr = $('<tr>').appendTo('#user-list > tbody');
            $(data).each(function (index, item) {
                tr.append(
                    $(document.createElement('td')).text(item.username)
                );
            });
        });
    }
</script>

<%-- include footer.jsp --%>
<jsp:include page="footer.jsp"/>