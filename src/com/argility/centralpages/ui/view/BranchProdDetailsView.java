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
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window.Notification;

/**
 * Main production details view
 * @author marko.salic
 *
 */
@SuppressWarnings("serial")
public class BranchProdDetailsView extends AbstractVerticalSplitPanel implements
		Property.ValueChangeListener {

	protected transient Logger log = Logger
			.getLogger(this.getClass().getName());

	protected SystemStatsDAO sysStatsDao;
	protected BranchProdDetailsDAO dao;
	protected BranchProdDetailsTable table;
	protected BranchProdDetailsForm form;

	public BranchProdDetailsView() {
		dao = (BranchProdDetailsDAO) CentralpagesApplication.getInstance().
			getSpringContext().getBean("branchProdDetailsDAO");
		
		sysStatsDao = (SystemStatsDAO) CentralpagesApplication.getInstance().
			getSpringContext().getBean("systemStatsDAO");
		
		addStyleName("view");
	}

	/**
	 * Show branches with crashed central processes
	 */
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
		createSingleColumnSearchableTable(table, "brCde", "Enter branch code and hit enter to search.");
	}

	private BranchProdDetailsTable setStatsTable(List<BranchProdDetails> list) {
		BranchProdDetailsContainer cont = new BranchProdDetailsContainer(
				BranchProdDetails.class, list);
		table = new BranchProdDetailsTable(cont);

		table.addRowCountFooter("brCde");
		table.addListener((Property.ValueChangeListener) this);

		return table;
	}

	public void wireXoutReceivedNotProcessedData() {
		setStatsTable(dao.getXoutReceivedNotProcessedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "xoutQueued", "replDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Repl Locked", "Last Replicated", "Repl file received", "Xout Queued",
				"Repl audit Diff" });
		table.setSortContainerPropertyId("lastReplicated");
		table.removeGeneratedColumn("brCde");

		createSelectSearchableTable(table,
				new String[] {"brCde", "central"},
				new String[] {"Branch", "Central"});
	}

	public void wireNotReplicatedForDaysData() {
		setStatsTable(dao.getNotReplicatedForDays());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "xoutQueued", "replDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Repl Locked", "Last Replicated", "Repl file received", "Xout queued", 
				"Repl audit diff" });
		table.setSortContainerPropertyId("brReplLockDate");
		table.removeGeneratedColumn("brCde");

		wireTable(true);
	}

	public void wireAllData() {
		setStatsTable(dao.getAllBranchProdDetails());

		table.setVisibleColumns(BranchProdDetailsContainer.NATURAL_COL_ORDER);
		table.setColumnHeaders(BranchProdDetailsContainer.COL_HEADERS_ENGLISH);

		table.setSortContainerPropertyId("brCde");

		createSelectSearchableTable(table,
				new String[] {"brCde", "central"},
				new String[] {"Branch", "Central"});
		
	}

	public void wireImportSwitchingFailedData() {

		setStatsTable(dao.getSwImportFailedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"lastSwLoad", "swDiff", "swCrashed", "swCrashAudId" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Last Switching Load", "Switch audit diff", "Import Crashed",
				"Crash audit id" });
		table.setSortContainerPropertyId("swDiff");
		table.setSortAscending(false);
	
		wireTable(true);
	}

	public void wireHighSwitchingVolumesToImportData() {
		setStatsTable(dao.getSwImportFromCentralBehindList());

		table.setVisibleColumns(new String[] { "brCde", "central", "replLocked",
				"brReplLockDate", "xoutReceived", "lastSwLoad", "swDiff", "swCrashed", "swCrashAudId" });
		table.setColumnHeaders(new String[] { "Branch", "Central", "Repl locked",
				"Last Replication", "Last replication file", "Last Switching load", "Switch audit diff",
				"Import Crashed", "Crash audit" });
		table.setSortContainerPropertyId("swDiff");
		table.setSortAscending(false);

		CentralpagesApplication.getInstance().getMainWindow().showNotification("Please note, not all " +
				"'switch audit diff' transactions are switching transactions.", 
				Notification.TYPE_TRAY_NOTIFICATION);
		
		createSelectSearchableTable(table,
				new String[] {"brCde", "central", "replLocked"},
				new String[] {"Branch", "Central", "Replication Locked"});
	}

	public void wireSwitchingNotImportedForDaysData() {
		setStatsTable(dao.getSwNotImportedForDaysList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"brReplLockDate", "replLocked", "xoutReceived", "lastSwLoad", "swDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Last Replicated", "Replication Locked", "Xout Received", "Last Switching Load",
				"Switching audit difference" });
		table.setSortContainerPropertyId("lastSwLoad");

		wireTable(true);
	}

	public void wireReplicatedAndNotImportedData() {
		setStatsTable(dao.getReplicateAndNotImportedList());

		table.setVisibleColumns(new String[] { "brCde", "central",
				"replLocked", "brReplLockDate", "xoutReceived", "lastSwLoad", "swDiff" });
		table.setColumnHeaders(new String[] { "Branch", "Central",
				"Replication Locked", "Last Replicated", "Xout Received", "Last Switching Load",
				"Switching audit difference" });
		table.setSortContainerPropertyId("lastSwLoad");
		table.removeGeneratedColumn("brCde");

		wireTable(true);
	}

	public HorizontalSplitPanel getBottomPanel(BranchProdDetails statsProd) {
		HorizontalSplitPanel hsp = new HorizontalSplitPanel();

		BranchInfoForm brForm = null;
		BranchInfo brInfo = sysStatsDao.getBrInfo(statsProd.getBrCde());

		form = new BranchProdDetailsForm(statsProd);
		
		hsp.setFirstComponent(form);
		
		if (brInfo != null) {
			brForm = new BranchInfoForm(brInfo);
			hsp.setSecondComponent(brForm);
		} else {
			hsp.setSecondComponent(new Panel("Unable to get branch profile for branch " + statsProd.getBrCde()));
		}
		
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
				
				//table.scrollAndSetFirstItem(sp);
			}
		} 
	}

}
