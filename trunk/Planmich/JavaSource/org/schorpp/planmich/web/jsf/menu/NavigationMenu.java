package org.schorpp.planmich.web.jsf.menu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;
import org.apache.myfaces.custom.navmenu.htmlnavmenu.HtmlCommandNavigationItem;
import org.apache.myfaces.custom.navmenu.jscookmenu.HtmlCommandJSCookMenu;
import org.schorpp.planmich.web.jsf.BaseBean;

public class NavigationMenu extends BaseBean {

	private List<NavigationMenuItem> menu = new ArrayList<NavigationMenuItem>();
	private NavigationMenuItem planMenu;
	
	private List<NavigationMenuItem> planContextMenu = new ArrayList<NavigationMenuItem>();
	
	public NavigationMenu() {
	
		planContextMenu.add(createMenuNavigationItem("Jahresplan", null));
		planContextMenu.add(createMenuNavigationItem("Diagramm", null));
		
		planMenu = createMenuNavigationItem("Plan",
				null);
		menu.add(planMenu);
		planMenu.add(createMenuNavigationItem("Jahresplan",
				"liquiplanUebersicht", "/pages/plan/uebersicht.jsp"));
		
		
		final NavigationMenuItem plandateMenu = createMenuNavigationItem(
				"Plandaten", null);
		menu.add(plandateMenu);

		final NavigationMenuItem kategorieMenu = createMenuNavigationItem(
				"Kategorien", null);
		menu.add(kategorieMenu);
		kategorieMenu.add(createMenuNavigationItem("Übersicht",
				"kategorieUebersicht", "/pages/kategorie/uebersicht.jsp"));

		kategorieMenu.add(createMenuNavigationItem("Neu",
				"#{kategorieBakingBean.clearKategorie}",
				"/pages/kategorie/kategorie.jsp"));

		final NavigationMenuItem mandantMenu = createMenuNavigationItem(
				"Mandanten", null);
		menu.add(mandantMenu);
		mandantMenu.add(createMenuNavigationItem("Mandantenübersicht",
				"mandantUebersicht", "/pages/mandant/uebersicht.jsp"));

		mandantMenu.add(createMenuNavigationItem("Neuer Mandant",
				"neuerMandant", "/pages/mandant/neu.jsp"));

		mandantMenu.add(createMenuNavigationItem("Mandant wechseln",
				"wechselnMandant", "/pages/mandant/selectMandant.jsp"));

		plandateMenu.add(createMenuNavigationItem("Übersicht",
				"plandatenUebersicht", "/pages/plandatum/uebersicht.jsp"));
		plandateMenu.add(createMenuNavigationItem("neues Plandatum",
				"neuesPlandatum", "/pages/plandatum/neu.jsp"));

	}
	
	public List getMenuItems() {
		return menu;
	}

	private static NavigationMenuItem createMenuNavigationItem(String label,
			String action) {
		final NavigationMenuItem item = new NavigationMenuItem(label, action);
		item.setActionListener("#{menu.actionListener}");
		item.setValue(label);
		return item;
	}

	private static NavigationMenuItem createMenuNavigationItem(String label,
			String action, String viewId) {
		final NavigationMenuItem item = new NavigationMenuItem(label, action);
		item.setActionListener("#{menu.actionListener}");
		item.setValue(label);
		item.setActiveOnViewIds(viewId);
		return item;
	}

	public String getAction1() {
		return "go_panelnavigation_1";
	}

	public String actionListener(ActionEvent event) {
		if (event.getComponent() instanceof HtmlCommandNavigationItem) {
			return getAction1();
		} else {
			final String outcome = (String) ((HtmlCommandJSCookMenu) event
					.getComponent()).getValue();
			return outcome;
		}
	}

	public String getAction2() {
		return "go_panelnavigation_2";
	}

	public String getAction3() {
		return "go_panelnavigation_3";
	}

	public String goHome() {
		return "go_home";
	}

	public boolean getDisabled() {
		return true;
	}
	
	
	public List<NavigationMenuItem> getContextMenu() {
		
		//der Namen der Seite
		final String viewId = getFacesContext().getViewRoot().getViewId();
		
		if(viewId.startsWith("/pages/plan/")) {
			return planContextMenu;
		}
			
		//Kein Menü
		return new ArrayList<NavigationMenuItem>();
	}
}
