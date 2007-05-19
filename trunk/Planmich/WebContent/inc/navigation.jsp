<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>


<h:form>
	<t:jscookMenu id="menu2" layout="vbr" theme="ThemeOffice"
		styleLocation="/css/jscookmenu">
		<t:navigationMenuItems id="navitems" value="#{menu.menuItems}" />
	</t:jscookMenu>
</h:form>


