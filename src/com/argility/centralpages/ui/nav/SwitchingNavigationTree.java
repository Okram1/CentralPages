package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.ProductionStatsView;
import com.argility.centralpages.ui.view.BranchProdDetailsView;
import com.argility.centralpages.ui.view.SwitchImportFailedView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Panel;

public class SwitchingNavigationTree extends AbstractNavigationTree {

	public static final Object SW_OVERVIEW = "Overview";
	public static final Object SW_LOAD_CRASHED = "Import into switching failed";
	public static final Object SW_IMPORT_FROM_CENTRAL_BEHIND = "Import from central behind";
	public static final Object SW_REPLICATED_AND_NOT_LOADED = "Replicated and not imported";
	public static final Object SW_NOT_LOADED_FOR_DAYS = "Days since last import";
	public static final Object SW_LOAD_TRANS_SKIPPED = "Search skipped audits";
	
	public static final Object SW_PRODUCTION_LOG = "Search production log";
	
	private BranchProdDetailsView view = null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7397892538375894658L;

	public SwitchingNavigationTree() {
		
		setCaption("Switching");
		
		addItem(SW_OVERVIEW);
		//setItemIcon(SW_OVERVIEW, new ThemeResource(ICO));
		setChildrenAllowed(SW_OVERVIEW, false);
		
		addItem(SW_LOAD_CRASHED);
		//setItemIcon(SW_LOAD_CRASHED, new ThemeResource(ICO));
		setChildrenAllowed(SW_LOAD_CRASHED, false);
		
		addItem(SW_IMPORT_FROM_CENTRAL_BEHIND);
		//setItemIcon(SW_LOAD_LARGE_DIFF, new ThemeResource(ICO));
		setChildrenAllowed(SW_IMPORT_FROM_CENTRAL_BEHIND, false);
		
		addItem(SW_REPLICATED_AND_NOT_LOADED);
		//setItemIcon(SW_REPLICATED_AND_NOT_LOADED, new ThemeResource(ICO));
		setChildrenAllowed(SW_REPLICATED_AND_NOT_LOADED, false);
		
		addItem(SW_NOT_LOADED_FOR_DAYS);
		//setItemIcon(SW_NOT_LOADED_FOR_DAYS, new ThemeResource(ICO));
		setChildrenAllowed(SW_NOT_LOADED_FOR_DAYS, false);
		
		addItem(SW_PRODUCTION_LOG);
		//setItemIcon(SW_PRODUCTION_LOG, new ThemeResource("icons/edit-find-and-replace.png"));
		setItemIcon(SW_PRODUCTION_LOG, new ThemeResource(CentralpagesApplication.SEARCH_ICON));
		setChildrenAllowed(SW_PRODUCTION_LOG, false);

		addItem(SW_LOAD_TRANS_SKIPPED);
		setChildrenAllowed(SW_LOAD_TRANS_SKIPPED, false);
		setItemIcon(SW_LOAD_TRANS_SKIPPED, new ThemeResource(CentralpagesApplication.SEARCH_ICON));
		
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
		} else if (itemId == SW_IMPORT_FROM_CENTRAL_BEHIND) {
			getStatsProdView().wireHighSwitchingVolumesToImportData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_REPLICATED_AND_NOT_LOADED) {
			getStatsProdView().wireReplicatedAndNotImportedData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_NOT_LOADED_FOR_DAYS) {
			getStatsProdView().wireSwitchingNotImportedForDaysData();
			app.setMainView(getStatsProdView());
		} else if (itemId == SW_LOAD_TRANS_SKIPPED) {
			app.setMainView(new SwitchImportFailedView());
		} else if (itemId == SW_PRODUCTION_LOG) {
			app.setMainView(new ProductionStatsView());
			//app.setMainView(new ProductionStatsPerBranchView(app));
		} else {
			app.setMainView(new Panel(itemId + " is work in progress..."));
		}
		
	}
	
	public BranchProdDetailsView getStatsProdView() {
		if (view == null) {
			view = new BranchProdDetailsView();
		}
		return view;
	}
	
}
