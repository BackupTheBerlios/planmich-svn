package org.schorpp.planmich.web.jsf.konverter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.schorpp.planmich.domain.KategorieTyp;

public class KategorieTypConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {

		try {
			return KategorieTyp.valueOf(value);
		} catch (final IllegalArgumentException e) {
			throw new ConverterException();
		}
	}

	public String getAsString(FacesContext context, UIComponent component,
			Object value) throws ConverterException {
		return value.toString();
	}

}
