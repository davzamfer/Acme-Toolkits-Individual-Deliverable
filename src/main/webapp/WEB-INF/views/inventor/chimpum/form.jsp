<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<h2><acme:message code="inventor.CHIMPUM.message.CHIMPUM"/></h2>
	<acme:input-textbox code="inventor.CHIMPUM.form.label.title" path="title"/>
	<acme:input-textbox code="inventor.CHIMPUM.form.label.description" path="description"/>
	<acme:input-moment code="inventor.CHIMPUM.form.label.startDate" path="startDate"/>
	<acme:input-moment code="inventor.CHIMPUM.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="inventor.CHIMPUM.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.CHIMPUM.form.label.info" path="link"/>	
	

	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(command, 'show, update, delete')}">
			<acme:input-textbox code="inventor.CHIMPUM.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="inventor.CHIMPUM.form.label.creationMoment" path="creationMoment" readonly="true"/>
			<acme:button code="inventor.CHIMPUM.form.button.artifacts" action="/any/artifact/list-published?chimpumId=${id}&type=tool"/>
			<acme:submit code="inventor.CHIMPUM.form.button.update" action="/inventor/chimpum/update"/>
			<acme:submit code="inventor.CHIMPUM.form.button.delete" action="/inventor/chimpum/delete"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">		
			<acme:input-textbox code="inventor.CHIMPUM.form.label.code" path="pattern"/>
			<acme:submit code="inventor.CHIMPUM.form.button.create" action="/inventor/chimpum/create"/>
		</jstl:when>		

	</jstl:choose>

</acme:form> 