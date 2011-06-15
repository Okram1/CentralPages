package com.argility.centralpages.ui.nav;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwitchingAgingView;
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
	
	public static final String AGING_TOT_ACT_TYP = "Aging switching by action type";
	public static final String AGING_TOT_OBO_BRANCH = "Aging switching by obo branch";
	public static final String AGING_TOT_BRANCH_TYPE = "Aging switching by branch/type";
	
	public static final String SW_TRAN_SEARCH = "Search transactions";
	//public static final String TOT_BY_ACT_TYP = "Totals by action type";
	
	
	private SwitchingTranView swTranView;
	private SwitchingAgingView agingView;
	
	public SwitchingTransTree() {
		
		addItem(TOT_BY_ACT_TYP);
		setChildrenAllowed(TOT_BY_ACT_TYP, false);
		
		addItem(TOT_BY_BRANCH);
		setChildrenAllowed(TOT_BY_BRANCH, false);
		
		addItem(TOT_BY_OBO_BRANCH);
		setChildrenAllowed(TOT_BY_OBO_BRANCH, false);
		
		addItem(AGING_TOT_ACT_TYP);
		setChildrenAllowed(AGING_TOT_ACT_TYP, false);
		
		addItem(AGING_TOT_OBO_BRANCH);
		setChildrenAllowed(AGING_TOT_OBO_BRANCH, false);
		
		addItem(AGING_TOT_BRANCH_TYPE);
		setChildrenAllowed(AGING_TOT_BRANCH_TYPE, false);
		
		addItem(SW_TRAN_SEARCH);
		setItemIcon(SW_TRAN_SEARCH, new ThemeResource(CentralpagesApplication.SEARCH_ICON));
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
		} else if (itemId == AGING_TOT_ACT_TYP) {
			getAgingView().wireAllAgingByActionType();
			app.setMainView(getAgingView());
		} else if (itemId == AGING_TOT_OBO_BRANCH) {
			getAgingView().wireAllAgingByOboBranchCode();
			app.setMainView(getAgingView());
		} else if (itemId == AGING_TOT_BRANCH_TYPE) {
			getAgingView().wireAllAgingByBranchAndType();
			app.setMainView(getAgingView());
		}  
	}

	public SwitchingTranView getSwTranView() {
		if (swTranView == null) {
			swTranView = new SwitchingTranView(); 
		}
		return swTranView;
	}
	
	public SwitchingAgingView getAgingView() {
		if (agingView == null) {
			agingView = new SwitchingAgingView();
		}
		return agingView;
	}

}
