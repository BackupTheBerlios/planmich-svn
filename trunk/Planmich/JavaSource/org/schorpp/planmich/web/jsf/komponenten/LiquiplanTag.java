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
	private String einnahmen;
	private String ausgaben;
	private String styleClass;
	private String headerClass;
	private String rowClasses;
	private String saldoClass;
	private String salden;

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

		if (einnahmen != null)
			if (isValueReference(einnahmen)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(einnahmen);
				component.setValueBinding("einnahmen", vb);
			}

		if (ausgaben != null)
			if (isValueReference(ausgaben)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(ausgaben);
				component.setValueBinding("ausgaben", vb);
			}

		if (salden != null)
			if (isValueReference(salden)) {
				final FacesContext context = FacesContext.getCurrentInstance();
				final Application app = context.getApplication();
				final ValueBinding vb = app.createValueBinding(salden);
				component.setValueBinding("salden", vb);
			}

		if (rowClasses != null)
			component.getAttributes().put("rowClasses", rowClasses);

		if (styleClass != null)
			component.getAttributes().put("styleClass", styleClass);

		if (headerClass != null)
			component.getAttributes().put("headerClass", headerClass);

		if (saldoClass != null)
			component.getAttributes().put("saldoClass", saldoClass);
	}

	@Override
	public void release() {
		super.release();
		header = null;
		einnahmen = null;
		ausgaben = null;
		rowClasses = null;
		saldoClass = null;
		salden = null;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setEinnahmen(String einnahmen) {
		this.einnahmen = einnahmen;
	}

	public void setSalden(String salden) {
		this.salden = salden;
	}

	public void setAusgaben(String ausgaben) {
		this.ausgaben = ausgaben;
	}

	public void setRowClasses(String rowClasses) {
		this.rowClasses = rowClasses;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public void setHeaderClass(String headerClass) {
		this.headerClass = headerClass;
	}

	public void setSaldoClass(String saldoClass) {
		this.saldoClass = saldoClass;
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
