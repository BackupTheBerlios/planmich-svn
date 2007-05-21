import org.schorpp.planmich.service.MandantService;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class TransactionTest extends
		AbstractTransactionalDataSourceSpringContextTests {

	private MandantService mandantService;

	private boolean isTxActive;

	public void testAnnotatedTx() throws Exception {
		mandantService.createMandant("Markus");
		assertTrue(isTxActive);
	}

	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		isTxActive = false;
	}

	@Override
	protected void onSetUpInTransaction() throws Exception {
		isTxActive = true;
	}

	public void setMandantSevice(MandantService mandantService) {
		this.mandantService = mandantService;
	}

	@Override
	protected String[] getConfigLocations() {
		return new String[] { "applicationContext.xml" };
	}

}
