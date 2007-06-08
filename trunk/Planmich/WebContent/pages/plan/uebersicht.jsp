
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">
		<h:form>

			<d:Headline headline="Liquiditätsplan" styleClass="headline" />

			<h:panelGrid id="datum" columns="3">

				<t:inputCalendar id="von" monthYearRowClass="yearMonthHeader"
					weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
					currentDayCellClass="currentDayCell"
					value="#{liquiplanBakingBean.von}" renderAsPopup="true"
					popupTodayString="Heute: " popupDateFormat="dd.MM.yyyy"
					popupWeekString="Woche:" helpText="dd.MM.yyyy" forceId="true" />



				<t:inputCalendar id="bis" monthYearRowClass="yearMonthHeader"
					weekRowClass="weekHeader" popupButtonStyleClass="standard_bold"
					currentDayCellClass="currentDayCell"
					value="#{liquiplanBakingBean.bis}" renderAsPopup="true"
					popupTodayString="Heute: " popupDateFormat="dd.MM.yyyy"
					popupWeekString="Woche:" helpText="dd.MM.yyyy" forceId="true" />

				<h:commandLink id="submit" action="#{liquiplanBakingBean.updatePlan}"
					value="Aktualisieren">
					<h:outputText>Aktualisieren</h:outputText>
				</h:commandLink>

			</h:panelGrid>

			<d:Spacer/>

			<t:div style="overflow: auto">

				<t:dataTable value="#{liquiplanBakingBean.planData}" rendered="true"
					border="0" rows="10" var="daten" id="showPlandates"
					styleClass="standardTable" headerClass="standardTable_Header"
					footerClass="standardTable_Header"
					rowClasses="standardTable_Row1,standardTable_Row2"
					columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
					sortable="false" preserveDataModel="false">

					<t:columns value="#{liquiplanBakingBean.columnHeaders}"
						var="columnHeader">
						<f:facet name="header">
							<h:outputText value="#{columnHeader.label}" />
						</f:facet>
						<h:outputText value="#{liquiplanBakingBean.columnValue}" />
					</t:columns>

				</t:dataTable>

			</t:div>

		</h:form>

	</f:facet>
</layout:page>
