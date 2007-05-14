import junit.framework.TestCase;

import org.schorpp.planmich.dao.TurnusDAO;
import org.schorpp.planmich.domain.Turnus;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


public class FillTurnus extends TestCase {
	
	public void test() {
		
		ClassPathResource res = new ClassPathResource("applicationContext.xml");
		
		XmlBeanFactory factory = new XmlBeanFactory(res);
		
		TurnusDAO turnusDAO = (TurnusDAO) factory.getBean("turnusDAO");
		
		Turnus t = new Turnus();
		t.setName("Test");
		
		turnusDAO.save(t);
	}

}
