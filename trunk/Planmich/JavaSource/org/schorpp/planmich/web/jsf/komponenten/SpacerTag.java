package org.schorpp.planmich.web.jsf.komponenten;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class SpacerTag extends UIComponentTag {

	private String size;
	
	protected void setProperties(UIComponent component)
	  {
	    super.setProperties(component);
	    
	    if (size != null) 
	    { 
	      if (isValueReference(size))
	      {
	        FacesContext context = FacesContext.getCurrentInstance();
	        Application app = context.getApplication();
	        ValueBinding vb = app.createValueBinding(size);
	        component.setValueBinding("size", vb);                  
	      }
	      else 
	        component.getAttributes().put("size", size);
	    }                         
	  }

	  public void release()
	  {
	    super.release();
	    size = null;
	  }


	  public void setHeadline(String size)
	  {
	    this.size = size;
	  } 

	public String getComponentType() {
		
		return "Spacer";
	}

	public String getRendererType() {

		return null;
	}



	
}
