<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form id="plandatum" styleClass="eingabeForm">

			<d:Headline headline="#{messages.addPlandatum}"
				rendered="#{!plandatumBakingBean.editMode}" styleClass="headline" />
			<d:Headline headline="#{messages.updatePlandatum}"
				rendered="#{plandatumBakingBean.editMode}" styleClass="headline" />

			<h:panelGrid columns="2" styleClass="tabelle"
				headerClass="tabelle_Ueberschrift"
				footerClass="tabelle_Ueberschrift" rowClasses="tabelle_Zeile1"
				columnClasses="tabelle_Spalte, whiteSpalte">

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
					popupTodayString="Heute: " popupDateFormat="dd.MM.yyyy"
					popupWeekString="#{example_messages['popup_week_string']}"
					helpText="dd.MM.yyyy" forceId="true" />

				<h:outputText value="#{messages.turnus}" />
				<h:selectOneMenu id="turnus" value="#{plandatumBakingBean.turnus}">
					<f:selectItems value="#{plandatumBakingBean.turnusListe}" />
				</h:selectOneMenu>


				<h:outputText value="#{messages.betrag}" />
				<h:inputText id="betrag" value="#{plandatumBakingBean.betrag}">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputText>

				<h:outputText value="#{messages.kategorie}" />
				<h:selectOneMenu id="kategorie"
					value="#{plandatumBakingBean.kategorie}">
					<f:selectItems value="#{plandatumBakingBean.kategorieListe}" />
				</h:selectOneMenu>

			</h:panelGrid>

			<h:messages id="infos" showSummary="false" showDetail="true"
				layout="table" errorClass="error" infoClass="info" />
			<f:verbatim>
				<br>
			</f:verbatim>

			<h:panelGrid columns="2" styleClass="tabelle"
				headerClass="tabelle_Ueberschrift"
				footerClass="tabelle_Ueberschrift"
				rowClasses="tabelle_Zeile1,tabelle_Zeile2"
				columnClasses="whiteSpalte, whiteSpalte">

				<t:commandButton value="#{messages.cancel}" immediate="true"
					action="#{plandatumBakingBean.cancelAction}" />


				<h:commandButton id="add" value="#{messages.speichernPlandatum}"
					action="#{plandatumBakingBean.addPlandatum}"
					rendered="#{not plandatumBakingBean.editMode}" />

				<h:commandButton id="update" value="#{messages.speichernPlandatum}"
					action="#{plandatumBakingBean.updatePlandatum}"
					rendered="#{plandatumBakingBean.editMode}" />

			</h:panelGrid>

		</h:form>
	</f:facet>
</layout:page>
