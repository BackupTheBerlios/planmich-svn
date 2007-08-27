<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">

		<h:form>

			<d:Headline headline="#{messages.neueKategorie}"
				rendered="#{!kategorieBakingBean.editMode}" styleClass="headline" />
			<d:Headline headline="#{messages.updateKategorie}"
				rendered="#{kategorieBakingBean.editMode}" styleClass="headline" />

			<h:panelGrid columns="2" styleClass="tabelle"
				headerClass="tabelle_Ueberschrift"
				footerClass="tabelle_Ueberschrift" rowClasses="tabelle_Zeile1"
				columnClasses="tabelle_Spalte, whiteSpalte">

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


			<h:panelGrid columns="2" styleClass="kommandoTabelle">

				<t:commandLink immediate="true"
					action="#{kategorieBakingBean.cancelAction}">
					<h:graphicImage value="/images/abbrechen.PNG" styleClass="button" />
				</t:commandLink>

				<h:commandLink id="add" action="#{kategorieBakingBean.addKategorie}"
					rendered="#{not kategorieBakingBean.editMode}">
					<h:graphicImage value="/images/uebernehmen.PNG" styleClass="button" />
				</h:commandLink>

				<h:commandLink id="update"
					action="#{kategorieBakingBean.updateKategorie}"
					rendered="#{kategorieBakingBean.editMode}">
					<h:graphicImage value="/images/uebernehmen.PNG" styleClass="button" />
				</h:commandLink>

			</h:panelGrid>



		</h:form>
	</f:facet>
</layout:page>
