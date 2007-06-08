import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Wiederholung;
import org.schorpp.planmich.service.LiquiplanService;
import org.schorpp.planmich.service.MandantService;
import org.schorpp.planmich.web.jsf.liquiplan.SpaltenUeberschrift;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class PersistenceTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	Mandant m;

	private LiquiplanService planService;

	private static MandantService mandantService;

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}

	@Test
	public void test1() throws Exception {

		m = mandantService.getMandantByName("Test");
		assertTrue(m != null);
		
		System.out.println(m.getName());

		List<SpaltenUeberschrift> colHeaders = new ArrayList<SpaltenUeberschrift>();
		
		Calendar von = Calendar.getInstance();
		Calendar bis = Calendar.getInstance();
		
		bis.add(Calendar.DATE, 1);
		
		List<List> plan = planService.calculatePlanAsMap(m, von.getTime(), bis.getTime(), colHeaders);
		
		
		
	}

	public void setMandantSevice(MandantService mandantService) {
		PersistenceTest.mandantService = mandantService;
	}
	
	public void setPlanService(LiquiplanService planService) {
		this.planService = planService;
	}

}
