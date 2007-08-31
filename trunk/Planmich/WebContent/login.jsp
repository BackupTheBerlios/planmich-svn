<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form id="plandatum" styleClass="eingabeForm">

			<t:inputText id="j_username" forceId="true" value="#{loginBackingBean.username}"/>
			<p/>
			<t:inputSecret id="j_password" forceId="true" value="#{loginBackingBean.password}"/>
			<p/>
			<h:commandLink action="login" value="Anmelden" />

		</h:form>
	</f:facet>
</layout:page>