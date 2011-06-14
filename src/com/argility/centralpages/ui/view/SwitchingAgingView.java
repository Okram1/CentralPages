package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingAgingStatsDAO;
import com.argility.centralpages.data.SwitchingAgingCount;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.table.SwitchingAgingTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SwitchingAgingView extends AbstractVerticalSplitPanel implements Property.ValueChangeListener{
	
	private SwitchingAgingStatsDAO dao;
	private SwitchingAgingTable table;
	
	public SwitchingAgingView() {
		dao = (SwitchingAgingStatsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingAgingDAO");
	}
	
	public void wireAllAgingByBranchCode() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByBranch(), true);
		
		table.setVisibleColumns(SwitchingAgingTable.BR_COLUMNS);
		table.setColumnHeaders(SwitchingAgingTable.COL_HEADINGS_BR);
		
		createSingleColumnSearchableTable(table, "brCde", "Enter action type and hit enter to search");
	}
	
	public void wireAllAgingByActionType() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByType(), true);
		
		table.setVisibleColumns(SwitchingAgingTable.TYPE_COLUMNS);
		table.setColumnHeaders(SwitchingAgingTable.COL_HEADINGS_TYPE);
		
		createSingleColumnSearchableTable(table, "actTyp", "Enter action type and hit enter to search");
		
	}
	
	public void wireAllAgingByBranchAndType() {
		setSwitchingAgingTable(dao.getAllSwitchingAgingByBranchAndType(), false);
		createSingleColumnSearchableTable(table, "brCde", "Enter branch code and hit enter to search");
	}
	
	public void wireAgingByBranch(String brCde) {
		setSwitchingAgingTable(dao.getSwitchingAgingByBranch(brCde), false);
//		createSearchableTable(table, "brCde", "Enter branch code and hit enter to search");
		showTable(table);
	}
	
	public void setSwitchingAgingTable(List<SwitchingAgingCount> list, boolean selectable) {
		table = new SwitchingAgingTable(new BeanItemContainer<SwitchingAgingCount>(SwitchingAgingCount.class, list));

		if (selectable) {
			table.setSelectable(true);
			table.addListener(this);
			table.setImmediate(true);
		}
		
		table.setSizeFull();
	}
	
	private void createBottomAgingTable(List<SwitchingAgingCount> list) {
		SwitchingAgingTable bottomTable = 
			new SwitchingAgingTable(new BeanItemContainer<SwitchingAgingCount>(SwitchingAgingCount.class, list));
		
		bottomTable.setSizeFull();
		setSecondComponent(bottomTable);
		setSplitPosition(50);
	}
	
	private void showTable(Table t) {
		setFirstComponent(t);
		setSplitPosition(100);
	}

	public void valueChange(ValueChangeEvent event) {
		Property prop = event.getProperty();
		
		if (prop == table) {
			SwitchingAgingCount cnt = (SwitchingAgingCount)prop.getValue();
			if (cnt == null) {
				setSplitPosition(100);
				return;
			}
			
			if (cnt.getBrCde() != null && cnt.getActTyp() != null) {
				//createBottomAgingTable(dao.getSwitchingAgingByBranchAndType(cnt.getBrCde(), cnt.getActTyp()));
			} else if (cnt.getBrCde() != null) {
				createBottomAgingTable(dao.getSwitchingAgingByBranch(cnt.getBrCde()));
			} else if (cnt.getActTyp() != null) {
				createBottomAgingTable(dao.getSwitchingAgingByType(cnt.getActTyp()));
			}
		} 
	}

}
