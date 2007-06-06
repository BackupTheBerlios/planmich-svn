package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.LiquiplanService;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;


public class LiquiplanBakingBean extends BaseBean {

	private LiquiplanService service;

	private MandantDAO mandantDAO;
	
	private Calendar von = Calendar.getInstance();
	
	private Calendar bis = Calendar.getInstance();
	
	protected DataModel columnHeaders;
	
	protected DataModel data;
	
	public LiquiplanBakingBean() {

	}
	
	public void setService(LiquiplanService service) {
		this.service = service;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}
	
	public DataModel getPlanData() {

		Mandant mandant = mandantDAO.getMandantById((Integer) getFromSession("Mandant"));
		List<SpaltenUeberschrift> colHeaders = new ArrayList<SpaltenUeberschrift>();
		
		bis.add(Calendar.DATE, 5);
		//return null;
		
		String[][] plan = service.getPlanAsMap(mandant, von, bis, colHeaders);
		
		data = new ArrayDataModel(plan);
		columnHeaders = new ListDataModel(colHeaders);
		
		return data;
	}

	public Calendar getBis() {
		return bis;
	}

	public void setBis(Calendar bis) {
		this.bis = bis;
	}

	public Calendar getVon() {
		return von;
	}

	public void setVon(Calendar von) {
		this.von = von;
	}
	

	public Object getColumnValue() {
		Object columnValue = null;
		if (data.isRowAvailable() && columnHeaders.isRowAvailable()) {
	
			columnValue = ((String[]) data.getRowData())[columnHeaders.getRowIndex()];
					
		}
		return columnValue;
	}

	@SuppressWarnings("unchecked")
	public void setColumnValue(Object value) {
		if (data.isRowAvailable() && columnHeaders.isRowAvailable()) {
			((List<Object>) data.getRowData()).set(columnHeaders.getRowIndex(),
					value);
		}
	}

	public String getColumnWidth() {
		String columnWidth = null;
		if (data.isRowAvailable() && columnHeaders.isRowAvailable()) {
			columnWidth = ((SpaltenUeberschrift) columnHeaders.getRowData())
					.getWidth();
		}
		return columnWidth;
	}

	public String getTextAlign() {
		String textAlign = null;
		if (data.isRowAvailable() && columnHeaders.isRowAvailable()) {
			textAlign = ((SpaltenUeberschrift) columnHeaders.getRowData())
					.getTextAlign();
		}
		return textAlign;
	}

	public DataModel getColumnHeaders() {
		return columnHeaders;
	}
}
