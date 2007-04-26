package org.schorpp.planmich.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Plandatum;

public class PlanServiceImpl implements PlanService {

	List<Plandatum> plandaten = new ArrayList<Plandatum>();

	List<Kategorie> kategorien = new ArrayList<Kategorie>();

	SimpleDateFormat df = new SimpleDateFormat("yyMMdd");

	public void plandatumHinzufuegen(Plandatum p) {
		plandaten.add(p);
	}

	public void plandatumLoeschen(Plandatum p) {
		plandaten.remove(p);
	}

	public void kategorieHinzufuegen(Kategorie k) {
		kategorien.add(k);
	}

	public void kategorieLoeschen(Kategorie k) {
		kategorien.remove(k);
	}

	public Map getPlanAsList(Calendar von, Calendar bis) {

		Map<String, Map<Kategorie, Double>> spalten = new TreeMap<String, Map<Kategorie, Double>>();
		Map<Kategorie, Double> zellen;

		for (Calendar aktDatum = (Calendar) von.clone(); aktDatum.before(bis); aktDatum
				.add(Calendar.DATE, 1)) {

			zellen = new TreeMap<Kategorie, Double>();

			for (Kategorie aktKategorie : kategorien) {

				double wert = 0.0;

				for (Plandatum aktPlandatum : plandaten)
					if (aktKategorie.equals(aktPlandatum.getKategorie()))
						if (pruefeDatumAufTurnus(aktDatum, aktPlandatum))
							wert += aktPlandatum.getBetrag();

				zellen.put(aktKategorie, wert);

			}

			spalten.put(df.format(aktDatum.getTime()), zellen);

		}

		return spalten;
	}

	private boolean pruefeDatumAufTurnus(Calendar aktDatum, Plandatum plandatum) {
		if (df.format(aktDatum.getTime()).equals(plandatum.getWertstellung()))
			return true;

		Date plandatumDatum = null;

		plandatumDatum = plandatum.getWertstellung();

		Calendar aktCalendar = Calendar.getInstance();
		aktCalendar.setTime(plandatumDatum);

		switch (plandatum.getWiederholung()) {

		case 1: // kein
			return false;

		case 2: // täglich
			return true;

		case 3:
			return matchByWeek(aktDatum, aktCalendar);

		case 4:
			return matchByWeek(aktDatum, aktCalendar, 2);

		case 5: // Monatlich
			return matchByMonth(aktDatum, aktCalendar);

		case 6: // 2-monatlich
			return matchByMonth(aktDatum, aktCalendar, 2);

		case 7: // 3-monatlich
			return matchByMonth(aktDatum, aktCalendar, 3);

		case 8: // 6-monatlich
			return matchByMonth(aktDatum, aktCalendar, 6);

		case 9: // jaehrlich
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

/*	private Calendar clearTime(Calendar date) {
		date.clear(Calendar.MILLISECOND);
		date.clear(Calendar.SECOND);
		date.clear(Calendar.MINUTE);
		date.clear(Calendar.HOUR);

		return date;
	}*/


}
