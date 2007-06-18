package org.schorpp.planmich.web.jsf.komponenten;

import java.io.IOException;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UIHeadline extends UIComponentBase {

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		final ResponseWriter writer = context.getResponseWriter();
		final String headline = (String) getAttributes().get("headline");

		writer.startElement("h1", this);

		writer.writeAttribute("id", getClientId(context), null);

		final String styleClass = (String) getAttributes().get("styleClass");
		if (styleClass != null)
			writer.writeAttribute("class", styleClass, null);

		if (headline != null)
			writer.writeText(headline, "headline");

		writer.endElement("h1");
	}

	@Override
	public String getFamily() {
		return "PlanmichFamily";
	}

}
