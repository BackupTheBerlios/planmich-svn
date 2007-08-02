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

	private DataModel ueberschriftenDM;

	private DataModel einnahmenDM, ausgabenDM, saldenDM;

	private List<SpaltenUeberschrift> spaltenUeberschriften;

	public LiquiplanBakingBean() {
		von = new Date();

		Calendar temp = Calendar.getInstance();

		temp.setTime(von);
		temp.roll(Calendar.MONTH, 1);
		temp.set(Calendar.DATE, 1);

		von = temp.getTime();

		bis = (Date) (von.clone());

		temp.setTime(bis);
		temp.add(Calendar.MONTH, 12);
		bis = temp.getTime();

		spaltenUeberschriften = new ArrayList<SpaltenUeberschrift>();
	}

	public void updatePlan() {

		List<List> einnahmen = new ArrayList<List>();
		List<List> ausgaben = new ArrayList<List>();
		List<String> salden = new ArrayList<String>();
		
		final Mandant mandant = mandantDAO
				.getMandantById((Integer) getFromSession("Mandant"));

		if (service.calculatePlanAsMap(mandant, von, bis, spaltenUeberschriften, einnahmen, ausgaben, salden)) {

			einnahmenDM = new ListDataModel(einnahmen);
			ausgabenDM = new ListDataModel(ausgaben);
			ueberschriftenDM = new ListDataModel(spaltenUeberschriften);
			saldenDM = new ListDataModel(salden);
		}
	}

	public void setService(LiquiplanService service) {
		this.service = service;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}

	public DataModel getEinnahmen() {
		return einnahmenDM;
	}
	
	public DataModel getAusgaben() {
		return ausgabenDM;
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

	public DataModel getColumnHeaders() {
		updatePlan();
		return ueberschriftenDM;
	}


	public DefaultPieDataset getPieDataSet() {
		final DefaultPieDataset pieDataSet = new DefaultPieDataset();
		pieDataSet.setValue("A", 52);
		pieDataSet.setValue("B", 18);
		pieDataSet.setValue("C", 30);

		return pieDataSet;
	}

	public DataModel getSalden() {
		return saldenDM;
	}
}
