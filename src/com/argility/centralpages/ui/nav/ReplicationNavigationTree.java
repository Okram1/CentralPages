package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.CentNotReplicatedForDaysView;
import com.argility.centralpages.ui.view.CentProcessCrashedView;
import com.argility.centralpages.ui.view.CentXoutReceivedNotProcessedView;
import com.argility.centralpages.ui.view.StatsProdOverviewView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Panel;

public class ReplicationNavigationTree extends AbstractNavigationTree {

	public static final Object OVERVIEW = "Replication Overview";
	public static final Object CRASHED_PROC = "Crashed Processes";
	public static final Object XOUT_NOT_PROCESSED = "Xout file not processed";
	public static final Object NOT_REPLICATED_DAYS = "Not replicated for days";
	public static final Object CENT_PROD_LOG = "Central production log";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4107819961744435738L;
	
	public ReplicationNavigationTree(CentralpagesApplication app) {
		super(app);
		
		addItem(OVERVIEW);
		addItem(CRASHED_PROC);
		addItem(XOUT_NOT_PROCESSED);
		addItem(NOT_REPLICATED_DAYS);
		addItem(CENT_PROD_LOG);
	}

	public void itemClick(ItemClickEvent event) {
		
		Object itemId = event.getItemId();
		
		if (itemId == CRASHED_PROC) {
			app.setMainView(new CentProcessCrashedView(app));
		} else if (itemId == OVERVIEW) {
			app.setMainView(new StatsProdOverviewView(app));
		} else if (itemId == XOUT_NOT_PROCESSED) {
			app.setMainView(new CentXoutReceivedNotProcessedView(app));
		} else if (itemId == NOT_REPLICATED_DAYS) {
			app.setMainView(new CentNotReplicatedForDaysView(app));
		} else {
			app.setMainView(new Panel( itemId + " is work in progress..."));
		}
	}

}
