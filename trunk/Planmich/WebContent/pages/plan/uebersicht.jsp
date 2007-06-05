
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


			<t:dataTable value="#{liquiplanBakingBean.planData}" rendered="true"
				border="0" rows="10" var="daten" id="showPlandates"
				styleClass="standardTable" headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column"
				sortable="true">
				<t:columns value="#{liquiplanBakingBean.columnHeaders}" var="columnHeader">
					<f:facet name="header">
						<h:outputText value="#{columnHeader.label}" />
					</f:facet>
					<h:outputText value="#{liquiplanBakingBean.columnValue}" />
				</t:columns>

			</t:dataTable>


		</h:form>

	</f:facet>
</layout:page>
