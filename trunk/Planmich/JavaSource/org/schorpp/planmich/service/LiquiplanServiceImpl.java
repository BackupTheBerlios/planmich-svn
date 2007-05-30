package org.schorpp.planmich.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Wiederholung;

public class LiquiplanServiceImpl implements LiquiplanService {


	SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.Liquidiplan#getPlanAsMap(java.util.Calendar,
	 *      java.util.Calendar)
	 */
	public Map getPlanAsMap(Mandant mandant, Calendar von, Calendar bis) {

		List<Plandatum> plandaten = null;

		List<Kategorie> kategorien = null;
		
		plandaten = mandant.getPlandaten();
		kategorien = mandant.getKategorien();
		
		Map<String, Map<Kategorie, Double>> spalten = new TreeMap<String, Map<Kategorie, Double>>();
		Map<Kategorie, Double> zellen;

		for (Calendar aktDatum = (Calendar) von.clone(); aktDatum.before(bis); aktDatum
				.add(Calendar.DATE, 1)) {


			zellen = new TreeMap<Kategorie, Double>();

			for (Kategorie aktKategorie : kategorien) {

				double wert = 0.0;

				for (Plandatum aktPlandatum : plandaten) {
					if (aktKategorie.equals(aktPlandatum.getKategorie())) {
						if (pruefeDatumAufTurnus(aktDatum, aktPlandatum))
							wert += aktPlandatum.getBetrag();
					}
				}

				zellen.put(aktKategorie, wert);

			}

			spalten.put(df.format(aktDatum.getTime()), zellen);

		}

		return spalten;
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
