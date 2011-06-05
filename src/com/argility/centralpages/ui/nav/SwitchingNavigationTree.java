package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.ProdStatsView;
import com.argility.centralpages.ui.view.StatsProdView;
import com.argility.centralpages.ui.view.SwitchLoadFailedFullView;
import com.argility.centralpages.ui.view.SwitchLoadFailedPerBranchView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Panel;

public class SwitchingNavigationTree extends AbstractNavigationTree {

	public static final Object SW_OVERVIEW = "Switching overview";
	public static final Object SW_LOAD_CRASHED = "Import failed";
	public static final Object SW_LOAD_LARGE_DIFF = "Many outstanding to import";
	public static final Object SW_REPLICATED_AND_NOT_LOADED = "Replicated and not imported";
	public static final Object SW_NOT_LOADED_FOR_DAYS = "Not imported for days";
	public static final Object SW_LOAD_TRANS_SKIPPED = "Crashed and skipped audits";
	public static final Object SW_LOAD_TRANS_SKIPPED_ALL = "Show All Audits";
	public static final Object SW_LOAD_TRANS_SKIPPED_BRANCH = "Search By Branch ";
	
	public static final Object SW_PRODUCTION_LOG = "Search production log";
	public static final Object SW_PRODUCTION_LOG_BY_BR = "Search By Branch code";
	public static final Object SW_PRODUCTION_LOG_ALL = "Show All";
	
	private StatsProdView view = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7397892538375894658L;

	public SwitchingNavigationTree() {
		
		setCaption("Switching");
		
		addItem(SW_OVERVIEW);
		setChildrenAllowed(SW_OVERVIEW, false);
		
		addItem(SW_LOAD_CRASHED);
		setChildrenAllowed(SW_LOAD_CRASHED, false);
		
		addItem(SW_LOAD_LARGE_DIFF);
		setChildrenAllowed(SW_LOAD_LARGE_DIFF, false);
		
		addItem(SW_REPLICATED_AND_NOT_LOADED);
		setChildrenAllowed(SW_REPLICATED_AND_NOT_LOADED, false);
		
		addItem(SW_NOT_LOADED_FOR_DAYS);
		setChildrenAllowed(SW_NOT_LOADED_FOR_DAYS, false);
		
		addItem(SW_LOAD_TRANS_SKIPPED);
		addItem(SW_LOAD_TRANS_SKIPPED_ALL);
		setParent(SW_LOAD_TRANS_SKIPPED_ALL, SW_LOAD_TRANS_SKIPPED);
		
		addItem(SW_LOAD_TRANS_SKIPPED_BRANCH);
		setParent(SW_LOAD_TRANS_SKIPPED_BRANCH, SW_LOAD_TRANS_SKIPPED);
		
		setChildrenAllowed(SW_LOAD_TRANS_SKIPPED_ALL, false);
		setChildrenAllowed(SW_LOAD_TRANS_SKIPPED_BRANCH, false);
		
		addItem(SW_PRODUCTION_LOG);
		setChildrenAllowed(SW_PRODUCTION_LOG, false);
		
	}

	public void itemClick(ItemClickEvent event) {
		// TODO Auto-generated method stub
		Object itemId = event.getItemId();
		//app.getMainWindow()
		//	.showNotification("You clicked 2: " + itemId.toString(), Notification.TYPE_TRAY_NOTIFICATION);
		
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		view = getStatsProdView();
		
		if (itemId == SW_OVERVIEW) {
			getStatsProdView().wireAllData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_LOAD_CRASHED) {
			getStatsProdView().wireImportSwitchingFailedData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_LOAD_LARGE_DIFF) {
			getStatsProdView().wireHighSwitchingVolumesToImportData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_REPLICATED_AND_NOT_LOADED) {
			getStatsProdView().wireReplicatedAndNotImportedData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_NOT_LOADED_FOR_DAYS) {
			getStatsProdView().wireSwitchingNotImportedForDaysData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_LOAD_TRANS_SKIPPED_ALL) {
			app.setMainView(new SwitchLoadFailedFullView(app));
		} else if (itemId == SW_LOAD_TRANS_SKIPPED_BRANCH) {
			app.setMainView(new SwitchLoadFailedPerBranchView(app));
		} else if (itemId == SW_LOAD_TRANS_SKIPPED) {
			expandItem(SW_LOAD_TRANS_SKIPPED);
		} else if (itemId == SW_PRODUCTION_LOG) {
			app.setMainView(new ProdStatsView());
			//app.setMainView(new ProductionStatsPerBranchView(app));
		} else {
			app.setMainView(new Panel(itemId + " is work in progress..."));
		}
		
	}
	
	public StatsProdView getStatsProdView() {
		if (view == null) {
			view = new StatsProdView(CentralpagesApplication.getInstance());
		}
		return view;
	}
	
}
