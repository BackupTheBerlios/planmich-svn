package org.schorpp.planmich.web.jsf.komponenten;

import java.util.List;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;

public class LiquiplanTag extends UIComponentTag {

	private String header;
	private String value;
	private String styleClass;
	private String headerClass;
	private String rowClasses;

	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);

		if (header != null)
			if (isValueReference(header)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(header);
				component.setValueBinding("header", vb);
			} 
	
		if (value != null)
			if (isValueReference(value)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(value);
				component.setValueBinding("value", vb);
			} 
		
		if (rowClasses != null)
			component.getAttributes().put("rowClasses", rowClasses);
		
		if (styleClass != null)
			component.getAttributes().put("styleClass", styleClass);
	
		if (headerClass != null)
			component.getAttributes().put("headerClass", headerClass);
	
	}

	@Override
	public void release() {
		super.release();
		header = null;
		value = null;
		rowClasses = null;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setRowClasses(String rowClasses) {
		this.rowClasses = rowClasses;
	}
	
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public void setHeaderClass(String headerClass) {
		this.headerClass =headerClass;
	}
	
	@Override
	public String getComponentType() {

		return "Liquiplan";
	}

	@Override
	public String getRendererType() {

		return null;
	}

}
