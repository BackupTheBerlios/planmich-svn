<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<h:form>

				<h:form>
					<t:jscookMenu id="hiz" layout="vbr" theme="ThemeOffice"
						styleLocation="/Planmich/css/jscookmenu">
						<t:navigationMenuItems value="#{menu.menuItems}" />
					</t:jscookMenu>
				</h:form>




</h:form>

<f:verbatim>
	<p />
</f:verbatim>



