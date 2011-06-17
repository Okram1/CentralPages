package com.argility.centralpages.ui.nav;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;

@SuppressWarnings("serial")
public class CentralAccordion extends Accordion {

	public CentralAccordion() {

		addTab(new ReplicationNavigationTree(), "Replication", new ThemeResource("icons/22/document-save-as-3.png"));
		addTab(new CentralSystemNavTree(), "System", new ThemeResource("icons/22/document-save-as-2.png"));
		
		setHeight("50%");
	}

}
