<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form>

			<d:Headline headline="#{messages.neuermandant}" />

			<h:panelGrid columns="2" styleClass="standardTable"
				headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column">
				<f:facet name="header">
					<h:outputText value="Neuer Mandant:" />
				</f:facet>
				<h:outputText value="#{messages.bezeichnung}" />
				<h:inputText id="bezeichnung" value="#{mandantBakingBean.name}"
					required="true" maxlength="45">
					<f:validateLength minimum="1" maximum="45" />
				</h:inputText>

				<h:outputText value="#{messages.kommentar}" />
				<h:inputTextarea id="Kommentar"
					value="#{mandantBakingBean.kommentar}" required="true">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputTextarea>

			</h:panelGrid>

			<h:messages showSummary="false" showDetail="true" styleClass="error" />

			<f:verbatim>
				<br>
			</f:verbatim>

			<h:commandLink id="submit" value="#{messages.neuermandant}"
				action="#{mandantBakingBean.addMandant}" />

		</h:form>
	</f:facet>
</layout:page>
