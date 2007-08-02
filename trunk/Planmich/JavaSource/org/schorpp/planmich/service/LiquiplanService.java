package org.schorpp.planmich.service;

import java.util.Date;
import java.util.List;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;

public interface LiquiplanService {

	/**
	 * Liefert den fertigen Plan als Map
	 * 
	 * @param von
	 * @param bis
	 * @param colHeaders
	 * @return
	 */
	public boolean calculatePlanAsMap(Mandant mandant, Date von, Date bis,
			List<SpaltenUeberschrift> colHeaders, List<List> spalteEinnahmen, List<List> spalteAusgaben, List<String> salden);

	
	
}
