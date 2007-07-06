
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@include file="/inc/head.inc"%>

<html>

	<body>
		<f:view>

			<f:loadBundle basename="org.schorpp.planmich.web.resources.resources"
				var="messages" />

			<t:panelLayout id="page" styleClass="pageLayout"
				headerClass="pageHeader" navigationClass="pageNavigation"
				bodyClass="pageBody">
				<f:facet name="header">
					<h:panelGrid columns="1" style="width: 100%">

						<h:panelGrid columns="3" styleClass="headerTable"  style="width: 100%"
							columnClasses="standardTable_ColumnLeft, standardTable_ColumnRight">

							<h:outputText style="padding: 1em; color: black"
								value="#{messages.planmich}" />
							<h:outputText style="padding: 1em; color: black"
								value="#{messages.mandant}:" />
							
							<h:outputText style="padding: 1em; color: black"
								value="#{sessionBean.mandant.name}" />
								<f:verbatim> </f:verbatim>
							<h:outputText style="padding: 1em; color: black"
								value="#{messages.kommentar}:" />
							<h:outputText style="padding: 1em; color: black"
								value="#{sessionBean.mandant.kommentar}" />
						</h:panelGrid>

						<h:form>
							<t:jscookMenu id="menu" layout="hbr" theme="ThemeOffice"
								styleLocation="/css/jscookmenu">
								<t:navigationMenuItems id="hauptmenue" value="#{menu.menuItems}" />
							</t:jscookMenu>
						</h:form>

					</h:panelGrid>


				</f:facet>


				<jsp:doBody />

			</t:panelLayout>

		</f:view>
		
	</body>
</html>
