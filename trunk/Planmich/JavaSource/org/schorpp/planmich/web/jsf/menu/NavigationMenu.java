package org.schorpp.planmich.web.jsf.menu;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.custom.navmenu.NavigationMenuItem;
import org.apache.myfaces.custom.navmenu.htmlnavmenu.HtmlCommandNavigationItem;
import org.apache.myfaces.custom.navmenu.jscookmenu.HtmlCommandJSCookMenu;

public class NavigationMenu {
	private static final Log log = LogFactory.getLog(NavigationMenu.class);

	public List getMenuItems() {
		List<NavigationMenuItem> menu = new ArrayList<NavigationMenuItem>();

		NavigationMenuItem plandateMenu = createMenuNavigationItem("Plandaten",
				null);
		menu.add(plandateMenu);

		NavigationMenuItem kategorieMenu = createMenuNavigationItem(
				"Kategorien", null);
		menu.add(kategorieMenu);
		kategorieMenu.add(createMenuNavigationItem("�bersicht",
				"kategorieUebersicht", "/pages/kategorie/uebersicht.jsp"));

		kategorieMenu.add(createMenuNavigationItem("Neu", "#{kategorieBakingBean.clearKategorie}",
				"/pages/kategorie/kategorie.jsp"));

		NavigationMenuItem mandantMenu = createMenuNavigationItem("Mandanten",
				null);
		menu.add(mandantMenu);
		mandantMenu.add(createMenuNavigationItem("Mandanten�bersicht",
				"mandantUebersicht", "/pages/mandant/uebersicht.jsp"));

		mandantMenu.add(createMenuNavigationItem("Neuer Mandant", "neuerMandant",
				"/pages/mandant/neu.jsp"));

		mandantMenu.add(createMenuNavigationItem("Mandant wechseln", "wechselnMandant",
		"/pages/mandant/selectMandant.jsp"));
		
		plandateMenu.add(createMenuNavigationItem("�bersicht",
				"plandatenUebersicht", "/pages/plandatum/uebersicht.jsp"));
		plandateMenu.add(createMenuNavigationItem("neues Plandatum",
				"neuesPlandatum", "/pages/plandatum/neu.jsp"));

		return menu;
	}

	private static NavigationMenuItem createMenuNavigationItem(String label,
			String action) {
		NavigationMenuItem item = new NavigationMenuItem(label, action);
		item.setActionListener("#{menu.actionListener}");
		item.setValue(label);
		return item;
	}

	private static NavigationMenuItem createMenuNavigationItem(String label,
			String action, String viewId) {
		NavigationMenuItem item = new NavigationMenuItem(label, action);
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
			log.info("ActionListener: "
					+ ((HtmlCommandNavigationItem) event.getComponent())
							.getValue());
			return getAction1();
		} else {
			String outcome = (String) ((HtmlCommandJSCookMenu) event
					.getComponent()).getValue();
			log.info("ActionListener: " + outcome);
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
}
