package com.argility.centralpages.ui.nav;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwitchingTranView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.ThemeResource;

@SuppressWarnings("serial")
public class SwitchingTransTree extends AbstractNavigationTree {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public static final String TOT_BY_ACT_TYP = "Totals by action type";
	public static final String TOT_BY_BRANCH = "Totals by sending branch";
	public static final String TOT_BY_OBO_BRANCH = "Totals by obo branch";
	public static final String SW_TRAN_SEARCH = "Search transactions";
	//public static final String TOT_BY_ACT_TYP = "Totals by action type";
	
	
	private SwitchingTranView swTranView;
	
	public SwitchingTransTree() {
		
		addItem(TOT_BY_ACT_TYP);
		setChildrenAllowed(TOT_BY_ACT_TYP, false);
		
		addItem(TOT_BY_BRANCH);
		setChildrenAllowed(TOT_BY_BRANCH, false);
		
		addItem(TOT_BY_OBO_BRANCH);
		setChildrenAllowed(TOT_BY_OBO_BRANCH, false);
		
		addItem(SW_TRAN_SEARCH);
		setItemIcon(SW_TRAN_SEARCH, new ThemeResource("icons/edit-find-and-replace.png"));
		setChildrenAllowed(SW_TRAN_SEARCH, false);
		
		addListener((ItemClickListener)this);
	}

	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();
		log.info("Clicked on " + itemId);
		
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		if (itemId == TOT_BY_ACT_TYP) {
			getSwTranView().wireTotalByActionTypeData();
			app.setMainView(getSwTranView());
		} else if (itemId == TOT_BY_BRANCH) {
			getSwTranView().wireTotalBySendingBranchData();
			app.setMainView(getSwTranView());
		} else if (itemId == TOT_BY_OBO_BRANCH) {
			getSwTranView().wireTotalByOboBranchData();
			app.setMainView(getSwTranView());
		} else if (itemId == SW_TRAN_SEARCH) {
			getSwTranView().wireSearchForm();
			app.setMainView(getSwTranView());
		}  
	}

	public SwitchingTranView getSwTranView() {
		if (swTranView == null) {
			swTranView = new SwitchingTranView(); 
		}
		return swTranView;
	}

}
