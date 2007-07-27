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
		List<List> einnahmen = (List<List>) ((ListDataModel)getAttributes().get("einnahmen")).getWrappedData();
		List<List> ausgaben = (List<List>) ((ListDataModel)getAttributes().get("ausgaben")).getWrappedData();
		
		String rowClasses = (String) getAttributes().get("rowClasses");
		String styleClass = (String) getAttributes().get("styleClass");
		String headerClass = (String) getAttributes().get("headerClass");
		
		String[] rowClassesArray = StringUtils.splitShortString(rowClasses, ',');
		
		
		
		/*
		 * Einnahmen
		 * 
		 * 
		 */
		
		List<List> rowsEinnahmen = new ArrayList<List>();
		
		List<String> colList;
		
		int ySize = einnahmen.get(0).size();
		
		int i=0;
		
		for(int y=0; y<ySize; y++) {
			
			colList = new ArrayList<String>();
			
			for (int x = 0; x<einnahmen.size(); x++) {
				colList.add((String) ((List) einnahmen.get(x)).get(y));
			}
			
			rowsEinnahmen.add(colList);
			
		}
		

		
		
		
		/*
		 * Ausgaben
		 * 
		 * 
		 */
		
		List<List> rowsAusgaben = new ArrayList<List>();
		
		i=0;
		
		ySize = ausgaben.get(0).size();
		
		for(int y=0; y<ySize; y++) {
			
			colList = new ArrayList<String>();
			
			for (int x = 0; x<ausgaben.size(); x++) {
				colList.add((String) ((List) ausgaben.get(x)).get(y));
			}
			
			rowsAusgaben.add(colList);
			
		}		
		
		
		
		/*
		 * Tabellenüberschriften
		 * 
		 */
		
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
		
		
		
		writer.startElement("tr", this);
		writer.startElement("th", this);
		writer.writeAttribute("colspan", einnahmen.size(), null);
		writer.writeText("Einnahmen", null);
		writer.endElement("th");
		writer.endElement("tr");
		
		/*
		 * Einnahmen
		 * 
		 */
		
		for(List<String> row : rowsEinnahmen) {
					
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
		
		
		
		writer.startElement("tr", this);
		writer.startElement("th", this);
		writer.writeAttribute("colspan", einnahmen.size(), null);
		writer.writeText("Ausgaben", null);
		writer.endElement("th");
		writer.endElement("tr");
		
		
		/*
		 * Ausgaben
		 * 
		 * 
		 */
		
		for(List<String> row : rowsAusgaben) {
					
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
