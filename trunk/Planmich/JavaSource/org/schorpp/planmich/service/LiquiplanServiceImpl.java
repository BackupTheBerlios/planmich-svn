package org.schorpp.planmich.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Wiederholung;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;


public class LiquiplanServiceImpl implements LiquiplanService {


	SimpleDateFormat df = new SimpleDateFormat();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.Liquidiplan#getPlanAsMap(java.util.Calendar,
	 *      java.util.Calendar)
	 */
	public String[][] getPlanAsMap(Mandant mandant, Date von, Date bis, List<SpaltenUeberschrift> colHeaders) {

			Calendar vonDatum = Calendar.getInstance();
			vonDatum.setTime(von);
			
			Calendar bisDatum = Calendar.getInstance();
			bisDatum.setTime(bis);
			
		
		List<Plandatum> plandaten = null;

		List<Kategorie> kategorien = null;
		
		plandaten = mandant.getPlandaten();
		kategorien = mandant.getKategorien();
		
		// Liste der Uberschriften L�schen
		colHeaders.clear();
		colHeaders.add(new SpaltenUeberschrift("Kategorie", "300", "left", false));
		
		// Soviele Zeile wie Kategorien vorhanden sind
		String daten[][] = new String[kategorien.size()][1000];
			

		int x=0;
		
		for (Calendar aktDatum = (Calendar) vonDatum.clone(); aktDatum.before(bisDatum); aktDatum
				.add(Calendar.YEAR, 1)) {
			
			int y = 0;
			
			
			colHeaders.add(new SpaltenUeberschrift(df.format(aktDatum.getTime()), "300", "left", false));
			
			for (Kategorie aktKategorie : kategorien) {

				double wert = 0.0;

				for (Plandatum aktPlandatum : plandaten) {
					if (aktKategorie.equals(aktPlandatum.getKategorie())) {
						if (pruefeDatumAufTurnus(aktDatum, aktPlandatum))
							wert += aktPlandatum.getBetrag();
					}
				}

				daten[y][0] = aktKategorie.getName();
				daten[y][x+1] = Double.toString(wert);

				y += 1;
				
			}
			
			x += 1;
			
		}

		return daten;
	}

	private boolean pruefeDatumAufTurnus(Calendar aktDatum, Plandatum plandatum) {
		if (df.format(aktDatum.getTime()).equals(plandatum.getWertstellung()))
			return true;

		Date plandatumDatum = plandatum.getWertstellung();

		Calendar aktCalendar = Calendar.getInstance();
		aktCalendar.setTime(plandatumDatum);

		switch (plandatum.getTurnus()) {

		case Wiederholung.KEINE: // kein
			return false;

		case Wiederholung.TAEGLICH: // täglich
			return true;

		case Wiederholung.WOECHENTLICH:
			return matchByWeek(aktDatum, aktCalendar);

		case Wiederholung.ZWEIWOECHENTLICH:
			return matchByWeek(aktDatum, aktCalendar, 2);

		case Wiederholung.MONATLICH: // Monatlich
			return matchByMonth(aktDatum, aktCalendar);

		case Wiederholung.ZWEIMONATLICH: // 2-monatlich
			return matchByMonth(aktDatum, aktCalendar, 2);

		case Wiederholung.VIERTELJAEHRLICH: // 3-monatlich
			return matchByMonth(aktDatum, aktCalendar, 3);

		case Wiederholung.HALBJAEHRLICH: // 6-monatlich
			return matchByMonth(aktDatum, aktCalendar, 6);

		case Wiederholung.JAEHRLICH: // jaehrlich
			return matchByYear(aktDatum, aktCalendar);

		}

		return false;

	}

	private boolean matchByWeek(Calendar a, Calendar b) {
		return matchByWeek(a, b, 1);
	}

	private boolean matchByWeek(Calendar a, Calendar b, int dauer) {
		return matchByWhatever(a, b, Calendar.WEEK_OF_YEAR, 1);
	}

	private boolean matchByMonth(Calendar a, Calendar b) {
		return matchByMonth(a, b, 1);
	}

	private boolean matchByMonth(Calendar a, Calendar b, int dauer) {
		return matchByWhatever(a, b, Calendar.MONTH, dauer);
	}

	private boolean matchByYear(Calendar a, Calendar b) {
		return matchByWhatever(a, b, Calendar.YEAR, 1);
	}

	private boolean matchByWhatever(Calendar a, Calendar b, int feld, int dauer) {
		while (b.before(a)) {
			if (dateCompare(a, b))
				return true;
			b.add(feld, dauer);
		}

		return false;
	}

	private boolean dateCompare(Calendar a, Calendar b) {
		return (df.format(a.getTime())).equals((df.format(b.getTime())));
	}

	private Calendar clearTime(Calendar date) {
		date.clear(Calendar.MILLISECOND);
		date.clear(Calendar.SECOND);
		date.clear(Calendar.MINUTE);
		date.clear(Calendar.HOUR);

		return date;
	}

}
