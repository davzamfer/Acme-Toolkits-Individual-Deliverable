<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2><acme:message code="inventor.troqua.message.troqua"/></h2>
	<acme:input-textbox code="inventor.troqua.form.label.theme" path="theme"/>
	<acme:input-textbox code="inventor.troqua.form.label.statement" path="statement"/>
	<acme:input-moment code="inventor.troqua.form.label.startDate" path="startDate"/>
	<acme:input-moment code="inventor.troqua.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="inventor.troqua.form.label.quota" path="quota"/>
	<acme:input-url code="inventor.troqua.form.label.additionalInfo" path="additionalInfo"/>	
	

	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="inventor.troqua.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="inventor.troqua.form.label.creationMoment" path="creationMoment" readonly="true"/>
			<acme:button code="inventor.troqua.form.button.artifacts" action="/any/artifact/list-published?troquaId=${id}&type=tool"/>
			<acme:submit code="inventor.troqua.form.button.update" action="/inventor/troqua/update"/>
			<acme:submit code="inventor.troqua.form.button.delete" action="/inventor/troqua/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">		
			<acme:input-textbox code="inventor.troqua.form.label.code" path="code"/>
			<acme:submit code="inventor.troqua.form.button.create" action="/inventor/troqua/create"/>
		</jstl:when>		

	</jstl:choose>

</acme:form> 