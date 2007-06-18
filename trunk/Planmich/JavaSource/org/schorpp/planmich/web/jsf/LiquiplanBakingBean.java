package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.jfree.data.general.DefaultPieDataset;
import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.LiquiplanService;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;

public class LiquiplanBakingBean extends BaseBean {

	private LiquiplanService service;

	private MandantDAO mandantDAO;

	private Date von;

	private Date bis;

	private DataModel columnHeaders;

	private DataModel data;

	private List<SpaltenUeberschrift> colHeaders;

	public LiquiplanBakingBean() {
		von = new Date();
		bis = new Date();

		final Calendar temp = Calendar.getInstance();
		temp.setTime(bis);
		temp.add(Calendar.MONTH, 12);
		bis = temp.getTime();

		colHeaders = new ArrayList<SpaltenUeberschrift>();
	}

	public void updatePlan() {

		final Mandant mandant = mandantDAO
				.getMandantById((Integer) getFromSession("Mandant"));

		final String[][] plan = service.calculatePlanAsMap(mandant, von, bis,
				colHeaders);

		data = new ArrayDataModel(plan);
		columnHeaders = new ListDataModel(colHeaders);
	}


	public void setService(LiquiplanService service) {
		this.service = service;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}

	public DataModel getPlanData() {

		// return null;

		return data;
	}

	public Date getBis() {
		return bis;
	}

	public void setBis(Date bis) {
		this.bis = bis;
	}

	public Date getVon() {
		return von;
	}

	public void setVon(Date von) {
		this.von = von;
	}

	public Object getColumnValue() {
		Object columnValue = null;
		if (data.isRowAvailable() && columnHeaders.isRowAvailable()) {
			final String[] rowData = (String[]) data.getRowData();
			columnValue = rowData[columnHeaders.getRowIndex()];

		}
		return columnValue;
	}

	@SuppressWarnings("unchecked")
	public void setColumnValue(Object value) {
		if (data.isRowAvailable() && columnHeaders.isRowAvailable())
			((List) data.getRowData()).set(columnHeaders.getRowIndex(), value);
	}

	public DataModel getColumnHeaders() {
		updatePlan();
		return columnHeaders;
	}

	public String getColumnWidth() {
		if (data.isRowAvailable() && columnHeaders.isRowAvailable())
			return ((SpaltenUeberschrift) columnHeaders.getRowData())
					.getWidth();

		return null;
	}

	public String getColumnAlign() {
		if (data.isRowAvailable() && columnHeaders.isRowAvailable())
			return ((SpaltenUeberschrift) columnHeaders.getRowData())
					.getTextAlign();

		return null;
	}

	public DefaultPieDataset getPieDataSet() {
		final DefaultPieDataset pieDataSet = new DefaultPieDataset();
		pieDataSet.setValue("A", 52);
		pieDataSet.setValue("B", 18);
		pieDataSet.setValue("C", 30);

		return pieDataSet;
	}
}
