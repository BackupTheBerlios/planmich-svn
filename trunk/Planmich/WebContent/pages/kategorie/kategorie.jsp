<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form>


			<d:Headline headline="#{messages.neueKategorie}" rendered="#{!kategorieBakingBean.editMode}" styleClass="headline"/>
			<d:Headline headline="#{messages.updateKategorie}" rendered="#{kategorieBakingBean.editMode}" styleClass="headline"/>

			<h:panelGrid columns="2" styleClass="standardTable"
				headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column, whiteColumn">
				
				<h:outputText value="#{messages.bezeichnung}" />
				<h:inputText id="bezeichnung" value="#{kategorieBakingBean.name}"
					required="true" maxlength="45">
					<f:validateLength minimum="1" maximum="45" />
				</h:inputText>

				<h:outputText value="#{messages.kommentar}" />
				<h:inputTextarea id="Kommentar"
					value="#{kategorieBakingBean.kommentar}">
					<f:validateLength minimum="1" maximum="255" />
				</h:inputTextarea>

				<h:outputText value="#{messages.sollhaben}" />
				<h:selectOneListbox value="#{kategorieBakingBean.kategorieTyp}">
					<f:selectItems value="#{kategorieBakingBean.typListe}" />
				</h:selectOneListbox>

			</h:panelGrid>

			<h:messages showSummary="false" showDetail="true" errorClass="error"
				infoClass="info" />

			<f:verbatim>
				<br>
			</f:verbatim>

			<h:commandLink id="add" value="#{messages.addKategorie}"
				action="#{kategorieBakingBean.addKategorie}" rendered="#{not kategorieBakingBean.editMode}" />

			<h:commandLink id="update" value="#{messages.updateKategorie}"
				action="#{kategorieBakingBean.updateKategorie}" rendered="#{kategorieBakingBean.editMode}"/>
				
			<t:commandLink value="#{messages.cancel}" immediate="true" action="#{kategorieBakingBean.cancelAction}"/>

		</h:form>
	</f:facet>
</layout:page>
