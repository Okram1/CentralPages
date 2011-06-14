package com.argility.centralpages.ui.nav;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;

@SuppressWarnings("serial")
public class MeConsAccordion extends Accordion {

	public MeConsAccordion() {
		addTab(new MeReportNavTree(), "Monthend Reports",  new ThemeResource("icons/22/db_update.png"));
		
		setHeight("50%");
	}
}
