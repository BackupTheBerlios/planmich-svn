package org.schorpp.planmich.web.jsf.komponenten;

import java.io.IOException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UISpacer extends UIComponentBase {

	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		String size = (String) getAttributes().get("size");

		writer.startElement("hr", this);
		writer.endElement("hr");
	}

	public String getFamily() {
		return "PlanmichFamily";
	}


}
