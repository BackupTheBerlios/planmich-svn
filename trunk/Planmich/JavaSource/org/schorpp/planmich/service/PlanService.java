package org.schorpp.planmich.service;
import java.util.Calendar;
import java.util.Map;

public interface PlanService {

	public Map getPlanAsList(Calendar von, Calendar bis);
	
}
