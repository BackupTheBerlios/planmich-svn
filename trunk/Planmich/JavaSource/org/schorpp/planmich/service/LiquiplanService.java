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
	public String[][] calculatePlanAsMap(Mandant mandant, Date von, Date bis,
			List<SpaltenUeberschrift> colHeaders);

}
