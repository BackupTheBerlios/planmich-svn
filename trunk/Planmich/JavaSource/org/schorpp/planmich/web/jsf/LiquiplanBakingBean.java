package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.jfree.data.category.DefaultCategoryDataset;
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

	private List<List> einnahmen = new ArrayList<List>();
	private List<List> ausgaben = new ArrayList<List>();
	private List<Double> salden = new ArrayList<Double>();

	private boolean planChanged = true;

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

		salden.clear();
		einnahmen.clear();
		ausgaben.clear();

		final Mandant mandant = mandantDAO
				.getMandantById((Integer) getFromSession("Mandant"));

		if (service.calculatePlanAsMap(mandant, von, bis,
				spaltenUeberschriften, einnahmen, ausgaben, salden)) {

			einnahmenDM = new ListDataModel(einnahmen);
			ausgabenDM = new ListDataModel(ausgaben);
			ueberschriftenDM = new ListDataModel(spaltenUeberschriften);
			saldenDM = new ListDataModel(salden);
		}

		planChanged = false;

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
		if (planChanged)
			updatePlan();
		return ueberschriftenDM;
	}

	public DefaultCategoryDataset getPieDataSet() {
		DefaultCategoryDataset categoryDataSet;
		
		if (planChanged)
			updatePlan();

		categoryDataSet = new DefaultCategoryDataset();

		for (int i = 0; i < 12; i++) {
			categoryDataSet.addValue(salden.get(i), "Liquisaldo",
					spaltenUeberschriften.get(i + 2).getLabel());
		}

		return categoryDataSet;

	}

	public DataModel getSalden() {
		return saldenDM;
	}

	public double getAnfangsbestand() {
		final Mandant mandant = mandantDAO
				.getMandantById((Integer) getFromSession("Mandant"));

		return mandant.getAnfangsbestand();

	}

	
	public void setAnfangsbestand(double anfangsbestand) {
		final Mandant mandant = mandantDAO
		.getMandantById((Integer) getFromSession("Mandant"));
		mandant.setAnfangsbestand(anfangsbestand);
		
		mandantDAO.save(mandant);
	}

}
