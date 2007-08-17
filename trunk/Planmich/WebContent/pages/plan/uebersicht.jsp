
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>
<%@ taglib uri="http://sourceforge.net/projects/jsf-comp" prefix="c"%>

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

				<h:commandLink id="submit" action="liquiplanUebersicht"
					value="Aktualisieren">
					<h:outputText>Aktualisieren</h:outputText>
				</h:commandLink>

			</h:panelGrid>


			<h:panelGrid columns="2">
				<h:inputText id="anfangsbestand" value="#{liquiplanBakingBean.anfangsbestand}" />
							<h:commandLink id="submit" action="liquiplanUebersicht"
					value="Aktualisieren">
					<h:outputText>Aktualisieren</h:outputText>
				</h:commandLink>
			</h:panelGrid>
			<f:verbatim>
				<br>
				<br>
			</f:verbatim>


			<d:Liquiplan header="#{liquiplanBakingBean.columnHeaders}" einnahmen="#{liquiplanBakingBean.einnahmen}" ausgaben="#{liquiplanBakingBean.ausgaben}" salden="#{liquiplanBakingBean.salden}" rowClasses="tabelle_Zeile1,tabelle_Zeile2" styleClass="tabelle"  headerClass="tabelle_Ueberschrift" />


			<t:div style="overflow-y:scroll;height:100%;overflow:auto;max-width:expression((document.body.clientWidth-50)+'px');width:expression((document.body.clientWidth-50)+'px');">

				

						<f:verbatim>
							<br>
							<br>
						</f:verbatim>



			</t:div>

		</h:form>

	</f:facet>
</layout:page>
