package com.argility.centralpages.ui.nav;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Accordion;

@SuppressWarnings("serial")
public class SwitchingNavAccordion extends Accordion {

	private SwitchingTransTree swTranTree;
	private SwitchingNavigationTree swNavTree;
	private SwitchingErrorNavigationTree swErrorTree;
	
	public SwitchingNavAccordion() {
		swTranTree = new SwitchingTransTree();
		swNavTree = new SwitchingNavigationTree();
		swErrorTree = new SwitchingErrorNavigationTree();
		
		addTab(swNavTree, "Switching report", new ThemeResource("icons/22/svn-commit.png"));
		addTab(swTranTree, "Switching Transactions", new ThemeResource("icons/22/preferences-system-network-2.png"));
		addTab(swErrorTree, "Switching Errors", new ThemeResource("icons/22/db_remove.png"));
		
		//setSelectedTab(swTranTree);
		
		setHeight("50%");
	}

}
