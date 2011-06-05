package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.StatsProdView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Panel;

public class ReplicationNavigationTree extends AbstractNavigationTree {

	public static final Object OVERVIEW = "Replication Overview";
	public static final Object CRASHED_PROC = "Crashed Processes";
	public static final Object XOUT_NOT_PROCESSED = "Xout file not processed";
	public static final Object NOT_REPLICATED_DAYS = "Not replicated for days";
	public static final Object CENT_PROD_LOG = "Central production log";

	private StatsProdView view = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4107819961744435738L;

	public ReplicationNavigationTree() {

		addItem(OVERVIEW);
		setChildrenAllowed(OVERVIEW, false);
		
		addItem(CRASHED_PROC);
		setChildrenAllowed(CRASHED_PROC, false);
		
		addItem(XOUT_NOT_PROCESSED);
		setChildrenAllowed(XOUT_NOT_PROCESSED, false);
		
		addItem(NOT_REPLICATED_DAYS);
		setChildrenAllowed(NOT_REPLICATED_DAYS, false);
		
		addItem(CENT_PROD_LOG);
		setChildrenAllowed(CENT_PROD_LOG, false);
		
	}

	public void itemClick(ItemClickEvent event) {

		Object itemId = event.getItemId();

		CentralpagesApplication app = CentralpagesApplication.getInstance();
		view = getView();

		if (itemId == CRASHED_PROC) {
			view.wireCentralProcessCrashedData();
			app.setMainView(view);
		} else if (itemId == OVERVIEW) {
			view.wireAllData();
			app.setMainView(view);
		} else if (itemId == XOUT_NOT_PROCESSED) {
			view.wireXoutReceivedNotProcessedData();
			app.setMainView(view);
		} else if (itemId == NOT_REPLICATED_DAYS) {
			view.wireNotReplicatedForDaysData();
			app.setMainView(view);
		} else {
			app.setMainView(new Panel(itemId + " is work in progress..."));
		}
	}

	public StatsProdView getView() {
		if (view == null) {
			view = new StatsProdView(CentralpagesApplication.getInstance());
		}
		return view;
	}

}
