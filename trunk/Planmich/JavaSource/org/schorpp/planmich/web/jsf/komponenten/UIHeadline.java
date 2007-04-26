package org.schorpp.planmich.web.jsf.komponenten;

import java.io.IOException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UIHeadline extends UIComponentBase {

	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String headline = (String) getAttributes().get("headline");

		writer.startElement("h1", this);
		if (headline != null)
			writer.writeText(headline, "headline");
		
		writer.endElement("h1");
	}

	public String getFamily() {
		return "PlanmichFamily";
	}


}
