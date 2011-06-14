package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.BranchMeDetailsView;
import com.vaadin.event.ItemClickEvent;

@SuppressWarnings("serial")
public class MeReportNavTree extends AbstractNavigationTree {

	public static final Object ME_OUTSTANDING_ON_CENTRAL = "Instore ME missing on central";
	public static final Object ME_ON_CENTRAL_OUTSTANDING_ON_BATCH = "Central ME missing on batch";
	public static final Object ME_ON_CENTRAL_NOT_ROLLED = "Fin proc not rolled on central";
	
	private BranchMeDetailsView brMeDetView;
	
	public MeReportNavTree() {
		addItem(ME_OUTSTANDING_ON_CENTRAL);
		setChildrenAllowed(ME_OUTSTANDING_ON_CENTRAL, false);
		
		addItem(ME_ON_CENTRAL_OUTSTANDING_ON_BATCH);
		setChildrenAllowed(ME_ON_CENTRAL_OUTSTANDING_ON_BATCH, false);
		
		addItem(ME_ON_CENTRAL_NOT_ROLLED);
		setChildrenAllowed(ME_ON_CENTRAL_NOT_ROLLED, false);
	}
	
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();
		
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		if (itemId == ME_OUTSTANDING_ON_CENTRAL) {
			getBrMeDetView().wireBranchMonthendNotOnCentralData();
			app.setMainView(getBrMeDetView());
		} else if (itemId == ME_ON_CENTRAL_OUTSTANDING_ON_BATCH) {
			getBrMeDetView().wireMonthendOnCentralOutstandingOnBatch();
			app.setMainView(getBrMeDetView());
		} else if (itemId == ME_ON_CENTRAL_NOT_ROLLED) {
			getBrMeDetView().wireMonthendOnCentralNotRolled();
			app.setMainView(getBrMeDetView());
		}

	}

	public BranchMeDetailsView getBrMeDetView() {
		if (brMeDetView == null) {
			brMeDetView = new BranchMeDetailsView();
		}
		return brMeDetView;
	}

}
