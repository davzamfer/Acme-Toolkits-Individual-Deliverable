<%@ page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.troqua.list.label.theme" path="theme"/>
	<acme:list-column code="inventor.troqua.list.label.statement" path="statement"/>
</acme:list>
<acme:button code="inventor.troqua.list.button.create" action="/inventor/troqua/create"/>