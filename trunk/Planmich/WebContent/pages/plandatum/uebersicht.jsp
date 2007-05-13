
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="layout"%>

<layout:page>

	<f:facet name="body">
		<h:form>
			<d:Headline headline="#{messages.addPlandatum}" />
			
			<h:dataTable value="#{sessionBean.mandant.plandaten}" rendered="true"
				border="0" rows="10" var="plandate" id="showPlandates"
				styleClass="scrollerTable" headerClass="standardTable_Header"
				footerClass="standardTable_Header"
				rowClasses="standardTable_Row1,standardTable_Row2"
				columnClasses="standardTable_Column,standardTable_ColumnCentered,standardTable_Column">
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
                                        <h:outputText value="#{plandate.datum}" />
                                </h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Kategorie" />
					</f:facet>
					<h:outputText value="#{plandate.kategorie.name}" />
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Turnus" />
					</f:facet>
					<h:outputText value="#{plandate.turnus.name}" />
				</h:column>
			</h:dataTable>
			<f:verbatim>
				<br>
			</f:verbatim>
			<t:dataScroller id="scrollPlandate" for="showPlandates" fastStep="10"
				pageCountVar="pageCount" pageIndexVar="pageIndex"
				styleClass="scroller" paginator="true" paginatorMaxPages="9"
				paginatorTableClass="paginator"
				paginatorActiveColumnStyle="font-weight:bold;">
				<f:facet name="first">
					<t:graphicImage url="images/arrow-first.gif" border="1" />
				</f:facet>
				<f:facet name="last">
					<t:graphicImage url="images/arrow-last.gif" border="1" />
				</f:facet>
				<f:facet name="previous">
					<t:graphicImage url="images/arrow-previous.gif" border="1" />
				</f:facet>
				<f:facet name="next">
					<t:graphicImage url="images/arrow-next.gif" border="1" />
				</f:facet>
				<f:facet name="fastforward">
					<t:graphicImage url="images/arrow-ff.gif" border="1" />
				</f:facet>
				<f:facet name="fastrewind">
					<t:graphicImage url="images/arrow-fr.gif" border="1" />
				</f:facet>
			</t:dataScroller>
		</h:form>

	</f:facet>

</layout:page>

