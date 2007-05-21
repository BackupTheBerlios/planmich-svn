package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.List;

import org.schorpp.planmich.domain.Turnus;
import org.schorpp.planmich.domain.Wiederholung;

public class TurnusListe {

	List<Turnus> turnus = new ArrayList<Turnus>();

	public TurnusListe() {
		turnus.add(new Turnus(Wiederholung.KEINE, "Nie"));
		turnus.add(new Turnus(Wiederholung.TAEGLICH, "Täglich"));
		turnus.add(new Turnus(Wiederholung.WOECHENTLICH, "Wöchentlich"));
		turnus
				.add(new Turnus(Wiederholung.ZWEIWOECHENTLICH,
						"Vierzehntäglich"));
		turnus.add(new Turnus(Wiederholung.MONATLICH, "Monatlich"));
		turnus.add(new Turnus(Wiederholung.ZWEIMONATLICH, "Zweimonatlich"));
		turnus
				.add(new Turnus(Wiederholung.VIERTELJAEHRLICH,
						"Vierteljährlich"));
		turnus.add(new Turnus(Wiederholung.HALBJAEHRLICH, "Halbjährlich"));
		turnus.add(new Turnus(Wiederholung.JAEHRLICH, "Jährlich"));
	}

	public List<Turnus> getTurnus() {
		return turnus;
	}
}
