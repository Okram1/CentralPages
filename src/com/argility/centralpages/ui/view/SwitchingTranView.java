package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingAgingStatsDAO;
import com.argility.centralpages.dao.SwitchingTransDAO;
import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwAudit;
import com.argility.centralpages.data.SwitchingAgingCount;
import com.argility.centralpages.data.SwitchingTran;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.SwAuditHorizontalSplit;
import com.argility.centralpages.ui.form.SwitchingTransSearchForm;
import com.argility.centralpages.ui.table.ActTypCountTable;
import com.argility.centralpages.ui.table.BranchCountsTable;
import com.argility.centralpages.ui.table.SwitchingAgingTable;
import com.argility.centralpages.ui.table.SwitchingTranTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class SwitchingTranView extends AbstractVerticalSplitPanel implements Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private SwitchingTransDAO dao;
	private SwitchingAgingStatsDAO agingDao;
	
	private SwitchingTranTable table;
	private ActTypCountTable actTypCountTable;
	private BranchCountsTable countTbl;
	private SwitchingAgingTable agingTable;
	private SwitchingTransSearchForm searchForm;
	
	public SwitchingTranView() {
		dao = (SwitchingTransDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingTransDAO");
		
		agingDao = (SwitchingAgingStatsDAO)CentralpagesApplication.getInstance()
		.getSpringContext().getBean("switchingAgingDAO");
		
		setSizeFull();
	}
	
	public void wireSearchForm() {
		if (searchForm == null) {
			searchForm = new SwitchingTransSearchForm();
		}
		
		setFirstComponent(searchForm);
		setSplitPosition(100);
		
	}
	
	public void wireSwitchingOboBranchData(String brCde) {
		table = createSwitchTranTable(dao.getSwitchTransToBranch(brCde));
		createMultiSearchTranTable(table);
	}
	
	public void wireSwitchingFromBranchData(String brCde) {
		table = createSwitchTranTable(dao.getSwitchTransFromBranch(brCde));
		createMultiSearchTranTable(table);
	}
	
	public void wireSwitchingByActionTypeData(Integer actTyp) {
		table = createSwitchTranTable(dao.getSwitchTransByActionType(actTyp));
		createMultiSearchTranTable(table);
	}
	
	public void wireSwitchingByAuditData(Integer audId) {
		table = createSwitchTranTable(dao.getSwitchTransByAuditId(audId));
		createMultiSearchTranTable(table);
	}
	
	public void wireTotalByActionTypeData() {
		actTypCountTable = createActTypCountTable(dao.getTotalsByActionType(), true);
		createSingleColumnSearchableTable(actTypCountTable, "actTyp", "Enter action type and hit enter to search");
	}
	
	public void wireTotalBySendingBranchData() {
		countTbl = createSwCountsTable(dao.getTotalsBySendingBranch(), false, true);
		countTbl.setSelectable(false);
		createSingleColumnSearchableTable(countTbl, "brCde", "Enter branch code and hit enter to search");
	}
	
	public void wireTotalByOboBranchData() {
		countTbl = createSwCountsTable(dao.getTotalsByOboBranch(), true, true);
		createSingleColumnSearchableTable(countTbl, "brCde", "Enter branch code and hit enter to search");
	}
	
	public void wireForBranchByActionTypeData(String oboBrCde) {
		actTypCountTable = createActTypCountTable(dao.getAllByActionTypeForBranch(oboBrCde), false);
		createSingleColumnSearchableTable(actTypCountTable, "actTyp", "Enter action type and hit enter to search");
		//wireTable(actTypCountTable);
	}
	
	public void createMultiSearchTranTable(SwitchingTranTable table) {
		createSelectSearchableTable(table,
				toStringArray(SwitchingTranTable.COL_NATURAL_ORDER), 
				SwitchingTranTable.HEADING_NAMES);
	}
	
	
	public void wireFromBranchByActionTypeData(String brCde) {
		actTypCountTable = createActTypCountTable(dao.getAllByActionTypeFromBranch(brCde), false);
		createSingleColumnSearchableTable(actTypCountTable, "actTyp", "Enter action type and hit enter to search");
		//wireTable(actTypCountTable);
	}
	
	public SwitchingTranTable createSwitchTranTable(List<SwitchingTran> list) {
		BeanItemContainer<SwitchingTran> cont = new BeanItemContainer<SwitchingTran>(SwitchingTran.class, list);
		table = new SwitchingTranTable(cont);
		
		table.setSizeFull();
		table.setColumnReorderingAllowed(true);
		
		table.setNullSelectionAllowed(true);
		
		table.addListener((Property.ValueChangeListener) this);
		table.setSelectable(true);
		table.setImmediate(true);
		table.addCountFooter("swAudId");
		
		return table;
	}
	
	public ActTypCountTable createActTypCountTable(List<ActionTypeCountBean> list, boolean selectable) {
		log.info("createActTypCountTable()");
		BeanItemContainer<ActionTypeCountBean> cont =
			new BeanItemContainer<ActionTypeCountBean>(ActionTypeCountBean.class, list);
		actTypCountTable = new ActTypCountTable(false, cont);
		
		actTypCountTable.setSizeFull();
		actTypCountTable.setColumnReorderingAllowed(true);
		
		if (selectable) {
			actTypCountTable.setSelectable(true);
			actTypCountTable.addListener((Property.ValueChangeListener)this);
			actTypCountTable.setImmediate(true);
		}
		
		return actTypCountTable;
	}
	
	public SwitchingAgingTable createSwitchingAgingTable(List<SwitchingAgingCount> list) {
		log.info("createSwitchingAgingTable()");
		BeanItemContainer<SwitchingAgingCount> cont =
			new BeanItemContainer<SwitchingAgingCount>(SwitchingAgingCount.class, list);
		agingTable = new SwitchingAgingTable(cont);
		
		agingTable.setSizeFull();
		agingTable.setColumnReorderingAllowed(true);
		
		return agingTable;
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
	
	private void setSwAuditSplit(SwitchingTran tran) {
		SwAudit swAud = dao.getSwAudit(tran.getSwAudId());
		SwAuditHorizontalSplit swSplit = new SwAuditHorizontalSplit(swAud);
		setSecondComponent(swSplit);
		setSplitPosition(40);

	}
	
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		
		log.info("Clicked property " + property);
		
		if (property == countTbl) {
			log.info("Right table");
			Item item = countTbl.getItem(countTbl.getValue());
			
			log.info("Item is " + item);
			
			if (item == null) {
				setSplitPosition(100);
			} else {
				if (countTbl.isToBranchSearch()) {
					String oboBrCde = ""+item.getItemProperty("brCde").getValue();
					//actTypCountTable = createActTypCountTable(dao.getAllByActionTypeForBranch(oboBrCde));
					
					agingTable = createSwitchingAgingTable(agingDao.getSwitchingAgingByBranch(oboBrCde));
					setSecondComponent(agingTable);
				} else {
					String brCde = ""+item.getItemProperty("brCde").getValue();
					actTypCountTable = createActTypCountTable(dao.getAllByActionTypeFromBranch(brCde), false);
					setSecondComponent(actTypCountTable);
				}
				
				setSplitPosition(40);
			}
		} else if (property == table) {
			SwitchingTran tran = (SwitchingTran)property.getValue();
			
			if (tran == null) {
				setSplitPosition(100);
				return;
			}
			
			setSwAuditSplit(tran);
			
		} else if (property == actTypCountTable) {
			ActionTypeCountBean bean = (ActionTypeCountBean)property.getValue();
			if (bean == null) {
				setSplitPosition(100);
				return;
			}
			agingTable = createSwitchingAgingTable(agingDao.getSwitchingAgingByType(bean.getActTyp()));
			setSecondComponent(agingTable);
			setSplitPosition(50);
		} 
	}
}
