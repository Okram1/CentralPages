package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwErrorsCountView;
import com.argility.centralpages.ui.view.SwitchingErrorsView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.terminal.ThemeResource;

@SuppressWarnings("serial")
public class SwitchingErrorNavigationTree extends AbstractNavigationTree {

	public static final Object ACT_TYP_COUNT = "Totals by action type";
	public static final Object ERROR_COUNT = "Totals by error type";
	public static final Object BR_COUNT = "Totals by branch code";
	public static final Object OBO_BR_COUNT = "Totals by obo branch";
	public static final Object SW_ERR_OVERVIEW = "Search switching errors";
	
	private SwErrorsCountView actTypView;
	
	public SwitchingErrorNavigationTree() {
		
		addItem(ACT_TYP_COUNT);
		setChildrenAllowed(ACT_TYP_COUNT, false);
		
		addItem(ERROR_COUNT);
		setChildrenAllowed(ERROR_COUNT, false);
		
		addItem(BR_COUNT);
		setChildrenAllowed(BR_COUNT, false);
		
		addItem(OBO_BR_COUNT);
		setChildrenAllowed(OBO_BR_COUNT, false);
		
		addItem(SW_ERR_OVERVIEW);
		setItemIcon(SW_ERR_OVERVIEW, new ThemeResource("icons/edit-find-and-replace.png"));
		setChildrenAllowed(SW_ERR_OVERVIEW, false);
	}

	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();
		
		setData(itemId);
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		if (itemId == ACT_TYP_COUNT) {
			getActTypView().wireCountByActionTypeData();
			app.setMainView(getActTypView());
		} else if (itemId == ERROR_COUNT) {
			//TODO somehow show notification to wait
			//Notification note = new Notification("HI", Notification.TYPE_ERROR_MESSAGE);
			//note.setPosition(Notification.POSITION_CENTERED_TOP);
			//app.getMainWindow().showNotification(note);
			//app.getMainWindow().showNotification("Please wait...", Notification.TYPE_HUMANIZED_MESSAGE);
			getActTypView().wireCountByErrorData();
			app.setMainView(getActTypView());
		} else if (itemId == SW_ERR_OVERVIEW) {
			SwitchingErrorsView view = new SwitchingErrorsView();
			view.createCaptureForm();
			app.setMainView(view);
		} else if (itemId == BR_COUNT) {
			getActTypView().wireFromBranchCounts();
			app.setMainView(getActTypView());
		} else if (itemId == OBO_BR_COUNT) {
			getActTypView().wireToBranchCounts();
			app.setMainView(getActTypView());
		}
	}

	private SwErrorsCountView getActTypView() {
		if (actTypView == null) {
			actTypView = new SwErrorsCountView();
		}
		return actTypView;
	}
}
