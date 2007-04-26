
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.schorpp.planmich.domain.Kategorie;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.schorpp.planmich.domain.Wiederholung;
import org.schorpp.planmich.service.MandantService;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;


public class PersistenceTest extends AbstractTransactionalDataSourceSpringContextTests {

	Mandant m;
	
	private static MandantService mandantService;
	
	@Override
	protected String[] getConfigLocations() {
		return new String[]{"applicationContext.xml"};
	}
	
	@Test
	public void test1() throws Exception {
		
		Plandatum p = new Plandatum();
		p.setName("Gehalt");
		p.setBetrag(100.00);
		p.setWertstellung(new Date());
		p.setWiederholung(Wiederholung.NO_RECURRENCE);
		p.setKategorie(new Kategorie("Gehalt"));
		
		m = new Mandant("Markus Schorpp");
		m.addPlandatum(p);
		m.addPlandatum(p);
		
		// Mandant hinzufügen
		mandantService.add(m);

		// Prüfen, ob der Mandant in der Liste der Mandanten ist
		assertTrue(mandantService.getAll().contains(m));
		
		// Mandant anhand des Namens holen
		Mandant mandant = mandantService.getMandantByName("Markus Schorpp");
		assertTrue(mandant != null);
		
		// Plandaten holen, Liste darf nicht leer sein
		List<Plandatum> plandaten = mandant.getPlandaten();
		assertFalse(plandaten.isEmpty());
		
		// Erstes Plandautm holen und prüfen, ob es das oben angelegte ist.
		Plandatum plandatum = plandaten.get(0);
		assertEquals(p, plandatum);
		
		mandant = mandantService.getMandantByName("Mickey Mouse");
		assertTrue(mandant == null);
		
	}	
	
	public void setMandantSevice(MandantService mandantService) {
		PersistenceTest.mandantService = mandantService;
	}
	
}
