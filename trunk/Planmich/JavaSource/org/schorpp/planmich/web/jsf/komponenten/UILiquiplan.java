package org.schorpp.planmich.web.jsf.komponenten;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.ListDataModel;

import org.apache.myfaces.shared_impl.util.StringUtils;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;

public class UILiquiplan extends UIOutput {

	
	
	@SuppressWarnings("unchecked")
	@Override
	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		List<SpaltenUeberschrift> header = (List<SpaltenUeberschrift>) ((ListDataModel) getAttributes().get("header")).getWrappedData();
		List<List> value = (List<List>) ((ListDataModel)getAttributes().get("value")).getWrappedData();
		String rowClasses = (String) getAttributes().get("rowClasses");
		String styleClass = (String) getAttributes().get("styleClass");
		String headerClass = (String) getAttributes().get("headerClass");
		
		String[] rowClassesArray = StringUtils.splitShortString(rowClasses, ',');
		
		List<List> rows = new ArrayList<List>();
		List<String> colList;
		
		int ySize = value.get(0).size();
		
		int i=0;
		
		for(int y=0; y<ySize; y++) {
			
			colList = new ArrayList<String>();
			
			for (int x = 0; x<value.size(); x++) {
				colList.add((String) ((List) value.get(x)).get(y));
			}
			
			rows.add(colList);
			
		}
		
		
		writer.startElement("table", this);
		writer.writeAttribute("class", styleClass, "class");
		
		writer.startElement("tr", this);
		
		for(int x=0; x<header.size(); x++) {
			writer.startElement("th", this);
			writer.writeAttribute("class", headerClass, "class");
			writer.writeText(header.get(x).getLabel(), null);
			writer.endElement("th");
		}
		
		writer.endElement("tr");
		
		
		
		
		
		for(List<String> row : rows) {
					
			writer.startElement("tr", this);
			
			for(String entry : row) {
				writer.startElement("td", this);
				writer.writeAttribute("class", rowClassesArray[i], "class");
				
				writer.writeText(entry, null);
				
				writer.endElement("td");
				
				i++;
				if(i<rowClassesArray.length)
					i=0;
			}
			
			
			writer.endElement("tr");
		}
		
		
		
		writer.endElement("table");
	}

	@Override
	public String getFamily() {
		return "PlanmichFamily";
	}

}
