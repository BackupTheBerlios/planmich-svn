package org.schorpp.planmich.web.jsf.komponenten;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class HeadlineTag extends UIComponentTag {

	private String headline;
	
	protected void setProperties(UIComponent component)
	  {
	    super.setProperties(component);
	    
	    if (headline != null) 
	    { 
	      if (isValueReference(headline))
	      {
	        FacesContext context = FacesContext.getCurrentInstance();
	        Application app = context.getApplication();
	        ValueBinding vb = app.createValueBinding(headline);
	        component.setValueBinding("headline", vb);                  
	      }
	      else 
	        component.getAttributes().put("headline", headline);
	    }                         
	  }

	  public void release()
	  {
	    super.release();
	    headline = null;
	  }


	  public void setHeadline(String hellomsg)
	  {
	    this.headline = hellomsg;
	  } 

	public String getComponentType() {
		
		return "Headline";
	}

	public String getRendererType() {

		return null;
	}



	
}
