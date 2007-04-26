
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<%@include file="/inc/head.inc"%>

<html>

<body>
<f:view>

	<f:loadBundle basename="org.schorpp.planmich.web.resources.resources" var="messages" />

	<t:panelLayout id="page" styleClass="pageLayout"
		headerClass="pageHeader" navigationClass="pageNavigation"
		bodyClass="pageBody">
		<f:facet name="header">
			<h:panelGrid columns="2" style="width: 100%" styleClass="headerTable"
				columnClasses="standardTable_ColumnLeft, standardTable_ColumnRight">
				<f:verbatim>Webplanme Version 0.0.2 </f:verbatim>
				<h:outputText style="padding-right: 1em"
					value="messages.mandant:  #{sessionBean.mandant.name}" />
			</h:panelGrid>
		</f:facet>

		<f:facet name="navigation">
			<f:subview id="menu">
				<jsp:include page="/inc/navigation.jsp" />
			</f:subview>
		</f:facet>

		<jsp:doBody />

	</t:panelLayout>

</f:view>
</body>
</html>
