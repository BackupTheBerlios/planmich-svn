<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<h:form>

	<t:div id="menue" forceId="true">

		<t:panelNavigation2 id="nav" layout="list" itemClass="menuepunkt"
			activeItemClass="aktiv" styleClass="menue">
			<t:navigationMenuItems id="navitems" value="#{menu.menuItems}" />
		</t:panelNavigation2>

	</t:div>

</h:form>

<f:verbatim>
	<p />
</f:verbatim>



