package org.schorpp.planmich.web.jsf;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.schorpp.planmich.dao.MandantDAO;
import org.schorpp.planmich.domain.Mandant;
import org.schorpp.planmich.service.LiquiplanService;


public class LiquiplanBakingBean extends BaseBean {

	private LiquiplanService service;

	private MandantDAO mandantDAO;
	
	private Calendar von = Calendar.getInstance();
	
	private Calendar bis = Calendar.getInstance();
	
	public void setService(LiquiplanService service) {
		this.service = service;
	}

	public void setMandantDAO(MandantDAO mandantDAO) {
		this.mandantDAO = mandantDAO;
	}
	
	public List getPlanData() {
		Mandant mandant = mandantDAO.getMandantById((Integer) getFromSession("Mandant"));
		
		bis.add(Calendar.DAY_OF_MONTH, 12);
		//return null;
		
		Map plan = service.getPlanAsMap(mandant, von, bis);
		
		
		Iterator it = plan.keySet().iterator();
		while(it.hasNext()) {
			String datum = (String) it.next();
			
			System.out.print(datum + " ");
			
			Iterator kt = ((Map)plan.get(datum)).keySet().iterator();
			
			while(kt.hasNext()) {
				org.schorpp.planmich.domain.Kategorie kat = (org.schorpp.planmich.domain.Kategorie)kt.next();
				System.out.print(kat.getName() + "   "); System.out.print(((Map)plan.get(datum)).get(kat));
				
			}
			
			
			
			System.out.println();
		}
		
		return null;
	}

	public Calendar getBis() {
		return bis;
	}

	public void setBis(Calendar bis) {
		this.bis = bis;
	}

	public Calendar getVon() {
		return von;
	}

	public void setVon(Calendar von) {
		this.von = von;
	}
	
}
