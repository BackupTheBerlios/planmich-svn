<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form>

			<d:Headline headline="#{messages.addcategorie}" />

			<h:panelGrid columns="2" styleClass="inputTable"
				headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column">
				<f:facet name="header">
					<h:outputText value="Neue Kategorie:" />
				</f:facet>
				<h:outputText value="#{messages.bezeichnung}" />
				<h:inputText id="bezeichnung" value="#{kategorieBakingBean.name}"
					required="true" maxlength="45">
					<f:validateLength minimum="1" maximum="45" />
				</h:inputText>

				<h:outputText value="#{messages.kommentar}" />
				<h:inputTextarea id="Kommentar" value="#{kategorieBakingBean.kommentar}"
					required="true">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputTextarea>
				
				<h:outputText value="#{messages.sollhaben}" />	
	
			</h:panelGrid>

<h:messages showSummary="false" showDetail="true" styleClass="error" />

			<f:verbatim>
				<br>
			</f:verbatim>

			<h:commandButton id="submit" value="#{messages.addcategory}"
				action="#{kategorieBakingBean.addKategorie}" />

		</h:form>
	</f:facet>
</layout:page>
