package org.schorpp.planmich.web.jsf.komponenten;

import java.io.IOException;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UISpacer extends UIComponentBase {

	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String size = (String) getAttributes().get("size");

		writer.startElement("hr", this);
		writer.endElement("hr");
	}

	@Override
	public String getFamily() {
		return "PlanmichFamily";
	}

}
