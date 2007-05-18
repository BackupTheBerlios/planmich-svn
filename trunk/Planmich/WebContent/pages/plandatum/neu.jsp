<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form>

			<d:Headline headline="#{messages.addPlandatum}" />

			<h:panelGrid columns="2" styleClass="inputTable"
				headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column">
				<f:facet name="header">
					<h:outputText value="#{messages.addPlandatum}" />
				</f:facet>
				<h:outputText value="#{messages.bezeichnung}" />
				<h:inputText id="bezeichnung" value="#{plandatumBakingBean.name}"
					required="true" maxlength="45">
					<f:validateLength minimum="1" maximum="45" />
				</h:inputText>

				<h:outputText value="#{messages.kommentar}" />
				<h:inputTextarea id="Kommentar"
					value="#{plandatumBakingBean.kommentar}">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputTextarea>

				<h:outputText value="#{messages.wertstellung}" />
				<t:inputCalendar id="valuta" monthYearRowClass="yearMonthHeader"
					weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
					currentDayCellClass="currentDayCell"
					value="#{plandatumBakingBean.wertstellung}" renderAsPopup="true"
					popupTodayString="Heute: " popupDateFormat="MM/dd/yyyy"
					popupWeekString="#{example_messages['popup_week_string']}"
					helpText="MM/DD/YYYY" forceId="true" />

				<h:outputText value="#{messages.turnus}" />
				<h:selectOneMenu id="kategorie"
					value="#{plandatumBakingBean.turnus}">
					<f:selectItems value="#{plandatumBakingBean.turnusListe}"/>
				</h:selectOneMenu>


				<h:outputText value="#{messages.betrag}" />
				<h:inputText id="betrag" value="#{plandatumBakingBean.betrag}">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputText>

				<h:outputText value="#{messages.kategorie}" />
				<h:selectOneMenu id="kategorie"
					value="#{plandatumBakingBean.kategorie}">
					<f:selectItems value="#{plandatumBakingBean.kategorieListe}"/>
				</h:selectOneMenu>

			</h:panelGrid>

			<h:messages showSummary="false" showDetail="true" errorClass="error"
				infoClass="info" />

			<f:verbatim>
				<br>
			</f:verbatim>

			<h:commandButton id="submit" value="#{messages.addPlandatum}"
				action="#{plandatumBakingBean.addPlandatum}" />

		</h:form>
	</f:facet>
</layout:page>
