package org.schorpp.planmich.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;

public interface LiquiplanService {

	/** 
	 * Liefert den fertigen Plan als Map
	 * @param von
	 * @param bis
	 * @return
	 */
	public Double[][] getPlanAsMap(Mandant mandant, Calendar von, Calendar bis);
	
}
