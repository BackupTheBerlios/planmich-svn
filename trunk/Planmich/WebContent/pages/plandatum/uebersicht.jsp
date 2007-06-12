
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>
<%@ taglib uri="http://schorpp.org/planmich" prefix="d"%>

<layout:page>

	<f:facet name="body">
		<h:form>
			<d:Headline headline="#{messages.plandatumUebersicht}" styleClass="headline"/>


			<t:dataTable value="#{sessionBean.mandant.plandaten}" rendered="true"
				border="0" rows="10" var="plandate" id="showPlandates"
				styleClass="tabelle" headerClass="tabelle_Ueberschrift"
				footerClass="tabelle_Ueberschrift"
				rowClasses="tabelle_Zeile1,tabelle_Zeile2"
				columnClasses="tabelle_Spalte,tabelle_SpalteCentered,tabelle_Spalte" sortable="true" preserveDataModel="false">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Bezeichnung" />
					</f:facet>
					<h:outputText value="#{plandate.name}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Betrag" />
					</f:facet>
					<h:outputText value="#{plandate.betrag}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Datum" />
					</f:facet>
					<h:outputText value="#{plandate.wertstellung}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Wiederholung" />
					</f:facet>
					<h:outputText value="#{plandate.turnus}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Kategorie" />
					</f:facet>
					<h:outputText value="#{plandate.kategorie.name}" />
				</h:column>
				<h:column>
					<t:commandLink action="#{plandatumBakingBean.deletePlandatum}"
						immediate="false">
						<h:outputText value="Löschen" />
						<t:updateActionListener property="#{plandatumBakingBean.plandatum}"
							value="#{plandate}" />
					</t:commandLink><f:verbatim> </f:verbatim>
					<t:commandLink action="#{plandatumBakingBean.selectPlandatum}"
						immediate="false">
						<h:outputText value="Bearbeiten" />
						<t:updateActionListener property="#{plandatumBakingBean.plandatum}"
							value="#{plandate}" />
					</t:commandLink>
				</h:column>		
		</t:dataTable>


			<f:verbatim>
				<br>
			</f:verbatim>
			<t:dataScroller id="scrollPlandate" for="showPlandates" fastStep="10"
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

			<t:commandLink value="#{messages.addPlandatum}"
				action="neuesPlandatum" />

		</h:form>

	</f:facet>

</layout:page>

