<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>


<html>

<%@include file="inc/head.inc"%>

<body>

<table class="loginTable" align="center">
	<tr>
		<td class="loginTable_Header">
		<p align="center"><strong>Planmich Version 0.0.7</strong></p>
		<p align="center">(C) 2007 Markus Schorpp</p>
		</td>
	</tr>
	<tr>
		<td class="loginTable_Footer"><f:view>
			<h:form rendered="true">

				<h:panelGrid styleClass="innerLoginTable"
					headerClass="standardTable_Header"
					footerClass="standardTable_Header" rendered="true" id="grid"
					columns="2" border="0">
					<f:facet name="header">
						<f:verbatim>Anmeldung:</f:verbatim>
					</f:facet>
					<h:outputText value="Benutzername: " />
					<t:inputText id="j_username" forceId="true" />

					<h:outputText value="Passwort: " />
					<t:inputSecret id="j_password" rendered="true" forceId="true" />

					<f:facet name="footer">
						<h:commandLink rendered="true" value="Login" action="login"
							id="submit" />
					</f:facet>

				</h:panelGrid>
				<h:messages layout="list" globalOnly="true" errorClass="error" />

			</h:form>
		</f:view></td>
	</tr>
</table>

</body>
</html>