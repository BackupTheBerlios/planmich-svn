package org.schorpp.planmich.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.KategorieTyp;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Wiederholung;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;

public class LiquiplanServiceImpl implements LiquiplanService {

	SimpleDateFormat df = new SimpleDateFormat("MMM.yyyy");

	DecimalFormat nf = new DecimalFormat("###,##0.00");

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.schorpp.planmich.service.Liquidiplan#getPlanAsMap(java.util.Calendar,
	 *      java.util.Calendar)
	 */
	public String[][] calculatePlanAsMap(Mandant mandant, Date von, Date bis,
			List<SpaltenUeberschrift> colHeaders) {

		final Calendar vonDatum = Calendar.getInstance();
		vonDatum.setTime(von);

		final Calendar bisDatum = Calendar.getInstance();
		bisDatum.setTime(bis);

		Calendar tempvonDatum;

		List<Plandatum> plandaten = null;

		List<Kategorie> kategorien = null;

		plandaten = mandant.getPlandaten();
		kategorien = mandant.getKategorien();

		// Liste der Uberschriften L�schen
		colHeaders.clear();
		colHeaders.add(new SpaltenUeberschrift("Kategorie", "300", "left",
				false));
		colHeaders.add(new SpaltenUeberschrift("E/A", "30", "left", false));

		// Soviele Zeile wie Kategorien vorhanden sind
		final String daten[][] = new String[kategorien.size() + 2][1000];

		double endbestand = 0;
		double anfangsbestand = 0;

		int x = 0;

		tempvonDatum = (Calendar) vonDatum.clone();

		for (final Calendar aktDatum = (Calendar) vonDatum.clone(); aktDatum
				.before(bisDatum); aktDatum.add(Calendar.MONTH, 1)) {

			int y = 0;

			colHeaders.add(new SpaltenUeberschrift(df
					.format(aktDatum.getTime()), "400", "right", false));

			anfangsbestand = endbestand;

			for (final Kategorie aktKategorie : kategorien) {

				double wert = 0.0;

				for (final Plandatum aktPlandatum : plandaten)
					if (aktKategorie.equals(aktPlandatum.getKategorie()))
						for (final Calendar kanditatDatum = (Calendar) tempvonDatum
								.clone(); kanditatDatum.before(aktDatum); kanditatDatum
								.add(Calendar.DATE, 1))
							if (pruefeDatumAufTurnus(kanditatDatum,
									aktPlandatum))
								if (aktKategorie.getKategorieTyp() == KategorieTyp.Einnahme)
									wert += aktPlandatum.getBetrag();
								else
									wert -= aktPlandatum.getBetrag();

				daten[y][0] = aktKategorie.getName();
				daten[y][1] = aktKategorie.getKategorieTyp().name();
				daten[y][x + 2] = nf.format(wert);

				anfangsbestand += wert;

				y += 1;

			}

			daten[y + 1][x + 2] = nf.format(anfangsbestand);

			x += 1;

			endbestand = anfangsbestand;

			tempvonDatum = (Calendar) aktDatum.clone();

		}

		return daten;
	}

	private boolean pruefeDatumAufTurnus(Calendar aktDatum, Plandatum plandatum) {
		if (df.format(aktDatum.getTime()).equals(plandatum.getWertstellung()))
			return true;

		final Date plandatumDatum = plandatum.getWertstellung();

		final Calendar aktCalendar = Calendar.getInstance();
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

		return Math.abs(a.get(Calendar.DATE) - b.get(Calendar.DATE))
				% (dauer * 7) == 0;

		// return matchByWhatever(a, b, Calendar.WEEK_OF_YEAR, 1);
	}

	private boolean matchByMonth(Calendar a, Calendar b) {

		final int taga = a.get(Calendar.DATE);
		final int tagb = b.get(Calendar.DATE);

		return taga == tagb;

		// return Math.abs(a.get(Calendar.MONDAY) - b.get(Calendar.MONTH)) % 2
		// == 0;

		// return matchByMonth(a, b, 1);
	}

	private boolean matchByMonth(Calendar a, Calendar b, int dauer) {

		final int taga = a.get(Calendar.DATE);
		final int tagb = b.get(Calendar.DATE);

		return taga == tagb
				&& Math.abs(a.get(Calendar.MONDAY) - b.get(Calendar.MONTH))
						% dauer == 0;

		// return matchByWhatever(a, b, Calendar.MONTH, dauer);
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

}
