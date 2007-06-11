
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>
	<f:facet name="body">
		<h:form>

			<d:Headline headline="#{messages.mandantenuebersicht}" styleClass="headline"/>

			<t:dataTable value="#{mandantDAO.mandanten}" rendered="true"
				border="0" rows="5" var="mandant" id="mandanten"
				styleClass="tabelle" headerClass="tabelle_Ueberschrift"
				footerClass="tabelle_Ueberschrift"
				rowClasses="tabelle_Zeile1,tabelle_Zeile2"
				columnClasses="tabelle_Spalte, tabelle_Spalte" sortable="true">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Name" />
					</f:facet>
					<h:outputText value="#{mandant.name}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Kommentar" />
					</f:facet>
					<h:outputText value="#{mandant.kommentar}" />
				</h:column>
			</t:dataTable>
			<f:verbatim>
				<br>
			</f:verbatim>
			<t:dataScroller for="mandanten" fastStep="10"
				pageCountVar="pageCount" pageIndexVar="pageIndex"
				styleClass="scroller" paginator="true" paginatorMaxPages="9"
				paginatorTableClass="paginator"
				paginatorActiveColumnStyle="font-weight:bold;">
				<f:facet name="first">
					<t:graphicImage url="/images/arrow-first.gif" border="1" />
				</f:facet>
				<f:facet name="last">
					<t:graphicImage url="/images/arrow-last.gif" border="1" />
				</f:facet>
				<f:facet name="previous">
					<t:graphicImage url="/images/arrow-previous.gif" border="1" />
				</f:facet>
				<f:facet name="next">
					<t:graphicImage url="/images/arrow-next.gif" border="1" />
				</f:facet>
				<f:facet name="fastforward">
					<t:graphicImage url="/images/arrow-ff.gif" border="1" />
				</f:facet>
				<f:facet name="fastrewind">
					<t:graphicImage url="/images/arrow-fr.gif" border="1" />
				</f:facet>
			</t:dataScroller>

			<d:Spacer />

			<t:commandLink value="#{messages.neuermandant}" action="neuerMandant" />


		</h:form>

	</f:facet>
</layout:page>
