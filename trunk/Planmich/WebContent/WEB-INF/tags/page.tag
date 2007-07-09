
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
					<h:panelGrid columns="1" style="width: 100%; margin: 0; border: none;">

						<h:panelGrid columns="2" styleClass="kopfzeile"
							columnClasses="kopfSpalteLinks,kopfSpalteRechts">

							<h:panelGrid columns="1" styleClass="kopfZeileLinks">
								<h:graphicImage url="/images/logo.jpg" />
							</h:panelGrid>

							<h:panelGrid columns="2" styleClass="kopfZeileRechts">

								<h:outputText style="padding: 1em; color: black;"
									value="#{messages.mandant}:" />

								<h:outputText style="padding: 1em; color: black"
									value="#{sessionBean.mandant.name}" />
								<h:outputText style="padding: 1em; color: black"
									value="#{messages.kommentar}:" />
								<h:outputText style="padding: 1em; color: black"
									value="#{sessionBean.mandant.kommentar}" />
							</h:panelGrid>

						</h:panelGrid>
						
						<h:form>
							<t:jscookMenu id="menu" layout="hbr" theme="ThemeOffice"
								styleLocation="/css/jscookmenu">
								<t:navigationMenuItems id="hauptmenue" value="#{menu.menuItems}" />
							</t:jscookMenu>
						</h:form>



					</h:panelGrid>



				</f:facet>

				<f:facet name="navigation">
					<h:form>
					<t:panelNavigation2 id="menu2" layout="list" styleClass="contextMenu" itemClass="contextItem">
						<t:navigationMenuItems id="contextMenu" value="#{menu.contextMenu}" />
					</t:panelNavigation2>
					</h:form>
				</f:facet>

				<jsp:doBody />

			</t:panelLayout>

		</f:view>

	</body>
</html>
