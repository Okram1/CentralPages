package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingTransDAO;
import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwitchingTran;
import com.argility.centralpages.ui.ActTypCountTable;
import com.argility.centralpages.ui.BranchCountsTable;
import com.argility.centralpages.ui.SwitchingTranTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class SwitchingTranView extends VerticalSplitPanel implements Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private SwitchingTransDAO dao;
	private SwitchingTranTable table;
	private ActTypCountTable actTypCountTable;
	private BranchCountsTable countTbl;
	
	public SwitchingTranView() {
		dao = (SwitchingTransDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingTransDAO");
		
		setSizeFull();
	}
	
	public void wireSwitchingForBranchData(String brCde) {
		createSwitchTranTable(dao.getSwitchTransToBranch(brCde));
	}
	
	public void wireSwitchingFromBranchData(String brCde) {
		createSwitchTranTable(dao.getSwitchTransFromBranch(brCde));
	}
	
	public void wireSwitchingByActionTypeData(Integer actTyp) {
		createSwitchTranTable(dao.getSwitchTransByActionType(actTyp));
	}
	
	public void wireSwitchingByAuditData(Integer audId) {
		createSwitchTranTable(dao.getSwitchTransByAuditId(audId));
	}
	
	public void wireTotalByActionTypeData() {
		log.info("wireTotalByActionTypeData()");
		createActTypCountTable(dao.getTotalsByActionType());
		wireTable(actTypCountTable);
	}
	
	public void wireTotalBySendingBranchData() {
		countTbl = createSwCountsTable(dao.getTotalsBySendingBranch(), false, true);
		wireTable(countTbl);
	}
	
	public void wireTotalByOboBranchData() {
		countTbl = createSwCountsTable(dao.getTotalsByOboBranch(), true, true);
		wireTable(countTbl);
	}
	
	public void wireForBranchByActionTypeData(String oboBrCde) {
		createActTypCountTable(dao.getAllByActionTypeForBranch(oboBrCde));
		wireTable(actTypCountTable);
	}
	
	public void wireFromBranchByActionTypeData(String brCde) {
		createActTypCountTable(dao.getAllByActionTypeFromBranch(brCde));
		wireTable(actTypCountTable);
	}
	
	public void createSwitchTranTable(List<SwitchingTran> list) {
		BeanItemContainer<SwitchingTran> cont = new BeanItemContainer<SwitchingTran>(SwitchingTran.class, list);
		table = new SwitchingTranTable(cont);
		
		table.setSizeFull();
		table.setColumnReorderingAllowed(true);
		
		table.addBrTotalCountFooter();
		
		wireTable(table);
	}
	
	public ActTypCountTable createActTypCountTable(List<ActionTypeCountBean> list) {
		log.info("createActTypCountTable()");
		BeanItemContainer<ActionTypeCountBean> cont =
			new BeanItemContainer<ActionTypeCountBean>(ActionTypeCountBean.class, list);
		actTypCountTable = new ActTypCountTable(false, cont);
		
		actTypCountTable.setSizeFull();
		actTypCountTable.setColumnReorderingAllowed(true);
		
		return actTypCountTable;
	}
	
	public BranchCountsTable createSwCountsTable(List<BranchCountsBean> list, boolean toBranch, boolean selectable) {
		BeanItemContainer<BranchCountsBean> cont = 
			new BeanItemContainer<BranchCountsBean>(BranchCountsBean.class, list);
		countTbl = new BranchCountsTable(false , cont);
		
		countTbl.setToBranchSearch(toBranch);
		countTbl.setColumnReorderingAllowed(true);
		
		countTbl.setSelectable(selectable);
		
		if (selectable) {
			countTbl.addListener((Property.ValueChangeListener) this);
			countTbl.setImmediate(true);
		}
		
		countTbl.setSizeFull();
		
		return countTbl;
	}
	
	private void wireTable(Table tbl) {
		setFirstComponent(tbl);
		setSplitPosition(100);
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		
		log.info("HERE " + property);
		
		if (property == countTbl) {
			log.info("Right table");
			Item item = countTbl.getItem(countTbl.getValue());
			
			log.info("Item is " + item);
			
			if (item == null) {
				setSplitPosition(100);
			} else {
				if (countTbl.isToBranchSearch()) {
					String oboBrCde = ""+item.getItemProperty("brCde").getValue();
					actTypCountTable = createActTypCountTable(dao.getAllByActionTypeForBranch(oboBrCde));
				} else {
					String brCde = ""+item.getItemProperty("brCde").getValue();
					actTypCountTable = createActTypCountTable(dao.getAllByActionTypeFromBranch(brCde));
				}
				
				setSecondComponent(actTypCountTable);
				setSplitPosition(40);
			}
		}
	}
}
