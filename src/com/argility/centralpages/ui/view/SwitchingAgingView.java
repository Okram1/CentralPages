package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingAgingStatsDAO;
import com.argility.centralpages.data.SwitchingAgingCount;
import com.argility.centralpages.ui.SwitchingAgingTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class SwitchingAgingView extends VerticalSplitPanel {
	
	private SwitchingAgingStatsDAO dao;
	private SwitchingAgingTable table;
	
	public SwitchingAgingView() {
		dao = (SwitchingAgingStatsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingAgingDAO");
	}
	
	public void wireAllAgingByBranchCode() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByBranch());
		
		table.setVisibleColumns(SwitchingAgingTable.BR_COLUMNS);
		table.setColumnHeaders(SwitchingAgingTable.COL_HEADINGS_BR);
		
		showTable(table);
	}
	
	public void wireAllAgingByActionType() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByType());
		
		table.setVisibleColumns(SwitchingAgingTable.TYPE_COLUMNS);
		table.setColumnHeaders(SwitchingAgingTable.COL_HEADINGS_TYPE);
		
		showTable(table);
	}
	
	public void wireAllAgingByBranchAndType() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByBranchAndType());
		
		showTable(table);
	}
	
	public void wireAgingByBranch(String brCde) {
		setSwitchingAgingTable(dao.getSwitchingAgingByBranch(brCde));
		
		showTable(table);
	}
	
	public void setSwitchingAgingTable(List<SwitchingAgingCount> list) {
		table = new SwitchingAgingTable(new BeanItemContainer<SwitchingAgingCount>(SwitchingAgingCount.class, list));

		table.setSizeFull();
	}
	
	private void showTable(Table t) {
		setFirstComponent(t);
		setSplitPosition(100);
	}

}
