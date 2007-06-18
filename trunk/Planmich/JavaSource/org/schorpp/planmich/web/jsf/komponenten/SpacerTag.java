package org.schorpp.planmich.web.jsf.komponenten;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class SpacerTag extends UIComponentTag {

	private String size;

	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		if (size != null)
			if (isValueReference(size)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(size);
				component.setValueBinding("size", vb);
			} else
				component.getAttributes().put("size", size);
	}

	@Override
	public void release() {
		super.release();
		size = null;
	}

	public void setHeadline(String size) {
		this.size = size;
	}

	@Override
	public String getComponentType() {

		return "Spacer";
	}

	@Override
	public String getRendererType() {

		return null;
	}

}
