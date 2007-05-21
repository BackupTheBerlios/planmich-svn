package org.schorpp.planmich.web.jsf;

import java.util.ArrayList;
import java.util.List;

import org.schorpp.planmich.domain.Turnus;
import org.schorpp.planmich.domain.Wiederholung;

public class TurnusListe {

	List<Turnus> turnus = new ArrayList<Turnus>();

	public TurnusListe() {
		turnus.add(new Turnus(Wiederholung.KEINE, "Nie"));
		turnus.add(new Turnus(Wiederholung.TAEGLICH, "T�glich"));
		turnus.add(new Turnus(Wiederholung.WOECHENTLICH, "W�chentlich"));
		turnus
				.add(new Turnus(Wiederholung.ZWEIWOECHENTLICH,
						"Vierzehnt�glich"));
		turnus.add(new Turnus(Wiederholung.MONATLICH, "Monatlich"));
		turnus.add(new Turnus(Wiederholung.ZWEIMONATLICH, "Zweimonatlich"));
		turnus
				.add(new Turnus(Wiederholung.VIERTELJAEHRLICH,
						"Viertelj�hrlich"));
		turnus.add(new Turnus(Wiederholung.HALBJAEHRLICH, "Halbj�hrlich"));
		turnus.add(new Turnus(Wiederholung.JAEHRLICH, "J�hrlich"));
	}

	public List<Turnus> getTurnus() {
		return turnus;
	}
}
