import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.domain.Plandatum;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;



public class MandantDAOTest extends AbstractTransactionalDataSourceSpringContextTests {

	private MandantDAO mandantDao;
	

	
	public void testSelectByNameOneResult() {
		
		Mandant m = mandantDao.getMandantById(1);
		
		Plandatum p = m.getPlandaten().get(0);
		
		assertEquals("test", p.getName());
	}
	
	public void setMandantDao(MandantDAO mandantDao) {
		this.mandantDao = mandantDao;
	}

		
}
