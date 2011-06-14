package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Panel;

@SuppressWarnings("serial")
public class ConsProcessNavTree extends AbstractNavigationTree {

	public static final String MECONS_FAILED_PROCESSES = "Mecons crashed processes";
	
	public ConsProcessNavTree() {
		addItem(MECONS_FAILED_PROCESSES);
		setChildrenAllowed(MECONS_FAILED_PROCESSES, false);
	}
	
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();

		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		if (itemId == MECONS_FAILED_PROCESSES) {
			app.setMainView(new Panel("Work in progress"));
		}
	}

}
