package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.BranchProdDetailsDAO;
import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.BranchProdDetails;
import com.argility.centralpages.data.BranchProdDetailsContainer;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.form.BranchInfoForm;
import com.argility.centralpages.ui.form.BranchProdDetailsForm;
import com.argility.centralpages.ui.table.BranchProdDetailsTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.HorizontalSplitPanel;

@SuppressWarnings("serial")
public class BranchProdDetailsView extends AbstractVerticalSplitPanel implements
		Property.ValueChangeListener {

	protected transient Logger log = Logger
			.getLogger(this.getClass().getName());

	protected CentralpagesApplication app;
	protected SystemStatsDAO sysStatsDao;
	protected BranchProdDetailsDAO dao;
	protected BranchProdDetailsTable table;
	protected BranchProdDetailsForm form;

	public BranchProdDetailsView(CentralpagesApplication app) {
		this.app = app;
		dao = (BranchProdDetailsDAO) app.getSpringContext().getBean(
				"branchProdDetailsDAO");
		sysStatsDao = (SystemStatsDAO) app.getSpringContext().getBean(
				"systemStatsDAO");
		addStyleName("view");
	}

	public void wireCentralProcessCrashedData() {

		setStatsTable(dao.getCentralProcCrashedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "xoutQueued",
				"replDiff", "replProcess"});
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Repl Locked", "Last Replicated", "Repl file received",
				"Xout Queued", "Replication diff", "Process"});
		table.setSortContainerPropertyId("brReplLockDate");
		table.removeGeneratedColumn("brCde");

		wireTable(true);

	}

	private void wireTable(boolean fullSize) {
		//setFirstComponent(table);
		//if (fullSize)
		//	setSplitPosition(100);
		createSearchableTable(table, "brCde", "Enter branch code and hit enter to search.");
	}

	private BranchProdDetailsTable setStatsTable(List<BranchProdDetails> list) {
		BranchProdDetailsContainer cont = new BranchProdDetailsContainer(
				BranchProdDetails.class, list);
		table = new BranchProdDetailsTable(cont);

		table.addCountFooter("brCde");
		table.addListener((Property.ValueChangeListener) this);

		return table;
	}

	public void wireXoutReceivedNotProcessedData() {
		setStatsTable(dao.getXoutReceivedNotProcessedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "xoutQueued", "replDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Repl Locked", "Last Replicated", "Repl file received", "Xout Queued",
				"Replication Diff" });
		table.setSortContainerPropertyId("lastReplicated");
		table.removeGeneratedColumn("brCde");

		wireTable(true);
	}

	public void wireNotReplicatedForDaysData() {
		setStatsTable(dao.getNotReplicatedForDays());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "brReplUnlockDate", "xoutReceived", "xoutQueued", "replDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Repl Locked", "Last Replicated", "Replication done", "Repl file received", "Xout queued", 
				"Repl Difference" });
		table.setSortContainerPropertyId("brReplLockDate");
		table.removeGeneratedColumn("brCde");

		wireTable(true);
	}

	public void wireAllData() {
		setStatsTable(dao.getAllBranchProdDetails());

		table.setVisibleColumns(BranchProdDetailsContainer.NATURAL_COL_ORDER);
		table.setColumnHeaders(BranchProdDetailsContainer.COL_HEADERS_ENGLISH);

		table.setSortContainerPropertyId("brCde");

		//createSearchableTable(table, "brCde", "Enter branch code and hit enter to search.");
		
		wireTable(true);
	}

	public void wireImportSwitchingFailedData() {

		setStatsTable(dao.getSwImportFailedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"lastSwLoad", "swDiff", "swCrashed", "swCrashAudId" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Last Switching Load", "Transactions Outstanding", "Import Crashed",
				"Crash audit id" });
		table.setSortContainerPropertyId("swDiff");
		table.setSortAscending(false);

		// table.addGeneratedColumn("swCrashAudId", new
		// CrashedAuditColGenerator());

		wireTable(true);
	}

	public void wireHighSwitchingVolumesToImportData() {
		setStatsTable(dao.getSwImportBehindList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"brReplLockDate", "lastSwLoad", "swDiff", "swCrashed", "swCrashAudId" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Last Replication", "Last Switching load", "Transactions Outstanding",
				"Import Crashed", "Crash audit" });
		table.setSortContainerPropertyId("swDiff");
		table.setSortAscending(false);

		wireTable(true);
	}

	public void wireSwitchingNotImportedForDaysData() {
		setStatsTable(dao.getSwNotImportedForDaysList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"brReplLockDate", "replLocked", "xoutReceived", "lastSwLoad", "swDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Last Replicated", "Replication Locked", "Xout Received", "Last Switching Load",
				"Switching Trans Outstanding" });
		table.setSortContainerPropertyId("lastSwLoad");

		wireTable(true);
	}

	public void wireReplicatedAndNotImportedData() {
		setStatsTable(dao.getReplicateAndNotImportedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "lastSwLoad", "swDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Replication Locked", "Last Replicated", "Xout Received", "Last Switching Load",
				"Switching Trans Outstanding" });
		table.setSortContainerPropertyId("lastSwLoad");
		table.removeGeneratedColumn("brCde");

		wireTable(true);
	}

	public HorizontalSplitPanel getBottomPanel(BranchProdDetails statsProd) {
		HorizontalSplitPanel hsp = new HorizontalSplitPanel();

		BranchInfo brInfo = sysStatsDao.getBrInfo(statsProd.getBrCde());

		form = new BranchProdDetailsForm(statsProd);
		BranchInfoForm brForm = new BranchInfoForm(brInfo);

		hsp.setFirstComponent(form);
		hsp.setSecondComponent(brForm);

		return hsp;
	}

	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();
		if (property == table) {
			Item item = table.getItem(table.getValue());

			if (item == null) {
				setSplitPosition(100);
			} else {

				BranchProdDetails sp = (BranchProdDetails) table.getValue();
				HorizontalSplitPanel hsp = getBottomPanel(sp);

				setSecondComponent(hsp);
				setSplitPosition(40);
			}
		} else if (property == searchField) {
			applySearch();
		}
	}

}
