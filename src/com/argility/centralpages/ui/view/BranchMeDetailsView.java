package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.BranchMeDetailsDAO;
import com.argility.centralpages.dao.BranchProdDetailsDAO;
import com.argility.centralpages.data.BranchMeDetails;
import com.argility.centralpages.data.BranchProdDetails;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.form.BranchProdDetailsForm;
import com.argility.centralpages.ui.table.BranchMeDetailsTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;

/**
 * This the the main ME details view, use it to display various monthend stats
 * @author marko.salic
 *
 */
@SuppressWarnings("serial")
public class BranchMeDetailsView extends AbstractVerticalSplitPanel implements Property.ValueChangeListener{

	private BranchMeDetailsDAO dao;
	protected BranchProdDetailsDAO brProdDao;
	private BranchMeDetailsTable brMeDetsTable;
	
	private BranchProdDetailsForm form;
	
	public BranchMeDetailsView() {
		dao = (BranchMeDetailsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("branchMeDetailsDAO");
		
		brProdDao = (BranchProdDetailsDAO) CentralpagesApplication.getInstance()
			.getSpringContext().getBean("branchProdDetailsDAO");

	}
	
	/**
	 * Show us all the branches where the monthend is outstanding on central
	 */
	public void wireBranchMonthendNotOnCentralData() {
		brMeDetsTable = createBranchMeDetailsTable(dao.getOutstandingOnCentralList());
		
		brMeDetsTable.setVisibleColumns(new Object[] {"brCde","central","meInstoreStatus",
				"meInstoreLastUpdate","lastUucpComm","uucpMessage"});
		brMeDetsTable.setColumnHeaders(new String[] {"Branch","Central","Instore Status",
				"Last instore update","Last UUCP comm","UUCP message"});
		
		createSingleColumnSearchableTable(brMeDetsTable, "brCde", "Enter branch code and hit enter to search");
	}
	
	/**
	 * Show us all the branches where we received the ME on central but the import on batch is still outstanding
	 */
	public void wireMonthendOnCentralOutstandingOnBatch() {
		brMeDetsTable = createBranchMeDetailsTable(dao.getOnCentralAndOutstandingOnBatchList());
		
		brMeDetsTable.setVisibleColumns(new Object[] {"brCde","central","brBatch","replLocked",
				"xoutQueued","replProcess","meCopyQueued","meDumpCopiedToBatch","meDumpWaitingOnBatch"});
		brMeDetsTable.setColumnHeaders(new String[] {"Branch","Central","Batch","Repl locked",
				"Repl file waiting","Replication process","ME dump copy queued","ME dump copied","ME dump waiting on batch"});
		
		createSelectSearchableTable(brMeDetsTable, 
				new String[] {"brCde","central","brBatch","replLocked",
								"xoutQueued","meCopyQueued","meDumpCopiedToBatch","meDumpWaitingOnBatch"}, 
				new String[] {"Branch","Central","Batch","Replication locked",
								"Repl file waiting","ME dump copy queued","ME dump copied","ME dump waiting on batch"});
	}
	
	/**
	 * Show all branches that are still outstanding to roll fin proc per on central
	 */
	public void wireMonthendOnCentralNotRolled() {
		brMeDetsTable = createBranchMeDetailsTable(dao.getOnCentralNotRolledList());
		
		brMeDetsTable.setVisibleColumns(new Object[] {"brCde","central","replLocked",
				"xoutQueued","replProcess","meReplicateStarted",
				"importedOnBatch", "consProcsDone", "consProcsFailed"});
		brMeDetsTable.setColumnHeaders(new String[] {"Branch","Central","Replication locked",
				"Repl file waiting","Replication process","ME replicate date",
				"Imported on batch","ME procs done","ME procs failed"});
		
		createSelectSearchableTable(brMeDetsTable, 
				new String[] {"brCde","central","replLocked","importedOnBatch"}, 
				new String[] {"Branch","Central","Replication Locked","Imported on batch"});
	}
	
	/**
	 * Create the BranchMeDetailsTable using the provided data
	 * @param list
	 * @return
	 */
	public BranchMeDetailsTable createBranchMeDetailsTable(List<BranchMeDetails> list) {
		
		BeanItemContainer<BranchMeDetails> cont = new BeanItemContainer<BranchMeDetails>(BranchMeDetails.class, list);
		
		brMeDetsTable = new BranchMeDetailsTable(cont);
		brMeDetsTable.setSizeFull();
		brMeDetsTable.setColumnReorderingAllowed(true);
		
		brMeDetsTable.addListener((Property.ValueChangeListener) this);
		brMeDetsTable.setSelectable(true);
		brMeDetsTable.setImmediate(true);
		brMeDetsTable.addRowCountFooter("brCde");

		return brMeDetsTable;
	}

	/**
	 * Handle the value change events from the BranchMeDetailsTable table
	 */
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		
		if (property == brMeDetsTable) {
			BranchMeDetails dets = (BranchMeDetails)brMeDetsTable.getValue();
			
			if (dets == null) {
				setSplitPosition(100);
			} else {
				BranchProdDetails prodDets = brProdDao.getProdDetailsForBranch(dets.getBrCde());
				form = new BranchProdDetailsForm(prodDets);
				setSecondComponent(form);
				setSplitPosition(40);
				
				//brMeDetsTable.scrollAndSetFirstItem(dets);
			}
		} 
		
	}
}
