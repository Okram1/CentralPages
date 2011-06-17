package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.CentSystemDetailsView;
import com.vaadin.event.ItemClickEvent;

@SuppressWarnings("serial")
public class CentralSystemNavTree extends AbstractNavigationTree {

	public static final String CENTRAL_SYSTEM_STATUS = "Central System Overview";
	
	private CentSystemDetailsView centSysView;
	
	public CentralSystemNavTree() {
		addItem(CENTRAL_SYSTEM_STATUS);
		setChildrenAllowed(CENTRAL_SYSTEM_STATUS, false);
	}
	
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();

		CentralpagesApplication app = CentralpagesApplication.getInstance();

		if (itemId == CENTRAL_SYSTEM_STATUS) {
			getCentSysView().wireAllCentralSystemData();
			app.setMainView(getCentSysView());
		}

	}

	public CentSystemDetailsView getCentSysView() {
		if (centSysView == null) {
			centSysView = new CentSystemDetailsView();
		}
		return centSysView;
	}

}
