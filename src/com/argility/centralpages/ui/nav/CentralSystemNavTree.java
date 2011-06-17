package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.CentralSystemView;
import com.vaadin.event.ItemClickEvent;

@SuppressWarnings("serial")
public class CentralSystemNavTree extends AbstractNavigationTree {

	public static final String CENTRAL_SYSTEM_STATUS = "Central System Overview";
	public static final Object UUCP_STATUS = "UUCP Status";
	public static final Object UUCP_STATUS_ALL_CENTRAL = "Central uucp status";
	public static final Object UUCP_STATUS_ALL_SWITCHING = "Switching uucp status";
	public static final Object UUCP_STATUS_PROBLEMS = "Show problems";
	
	private CentralSystemView centSysView;
	
	public CentralSystemNavTree() {
		addItem(CENTRAL_SYSTEM_STATUS);
		setChildrenAllowed(CENTRAL_SYSTEM_STATUS, false);
		
		addItem(UUCP_STATUS);
		
		addItem(UUCP_STATUS_ALL_CENTRAL);
		setChildrenAllowed(UUCP_STATUS_ALL_CENTRAL, false);
		setParent(UUCP_STATUS_ALL_CENTRAL, UUCP_STATUS);
		
		addItem(UUCP_STATUS_ALL_SWITCHING);
		setChildrenAllowed(UUCP_STATUS_ALL_SWITCHING, false);
		setParent(UUCP_STATUS_ALL_SWITCHING, UUCP_STATUS);
		
		addItem(UUCP_STATUS_PROBLEMS);
		setChildrenAllowed(UUCP_STATUS_PROBLEMS, false);
		setParent(UUCP_STATUS_PROBLEMS, UUCP_STATUS);
		
		expandItem(UUCP_STATUS);
	}
	
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();

		CentralpagesApplication app = CentralpagesApplication.getInstance();

		if (itemId == CENTRAL_SYSTEM_STATUS) {
			getCentSysView().wireAllCentralSystemData();
			app.setMainView(getCentSysView());
		} else if (itemId == UUCP_STATUS) {
			expandItem(UUCP_STATUS);
		} else if (itemId == UUCP_STATUS_ALL_CENTRAL) {
			getCentSysView().wireAllCentralUucpData();
			app.setMainView(getCentSysView());
		} else if (itemId == UUCP_STATUS_ALL_SWITCHING) {
			getCentSysView().wireAllSwitchingUucpData();
			app.setMainView(getCentSysView());
		} else if (itemId == UUCP_STATUS_PROBLEMS) {
			getCentSysView().wireProblemUucpData();
			app.setMainView(getCentSysView());
		}

	}

	public CentralSystemView getCentSysView() {
		if (centSysView == null) {
			centSysView = new CentralSystemView();
		}
		return centSysView;
	}
	
}
