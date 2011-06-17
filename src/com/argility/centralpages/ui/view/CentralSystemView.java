package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.CentralSystemDetails;
import com.argility.centralpages.data.UucpStatus;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.form.BranchInfoForm;
import com.argility.centralpages.ui.table.CentSystemStatusTable;
import com.argility.centralpages.ui.table.UucpStatusTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class CentralSystemView extends AbstractVerticalSplitPanel 
	implements Property.ValueChangeListener{

	private SystemStatsDAO dao;
	private CentSystemStatusTable centSysTable;
	private UucpStatusTable uucpStatusTable;
	
	public CentralSystemView() {
		dao = (SystemStatsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("systemStatsDAO");
	
		setSizeFull();
	}
	
	public void wireAllCentralSystemData() {
		centSysTable = createCentSysTable(dao.getCentralSystemDetails());
		createSingleColumnSearchableTable(centSysTable, "central", "Enter central and hit enter to search.");
	}
	
	public void wireAllCentralUucpData() {
		uucpStatusTable = createUucpStatusTable(dao.getAllCentralUucpStatus());
		createSelectSearchableTable(uucpStatusTable, 
				new String[] {"brCde","central"}, 
				new String[] {"Branch","Central"});
	}
	
	public void wireAllSwitchingUucpData() {
		uucpStatusTable = createUucpStatusTable(dao.getAllSwitchingUucpStatus());
		createSelectSearchableTable(uucpStatusTable, 
				new String[] {"brCde","central"}, 
				new String[] {"Branch","Central"});
	}
	
	public void wireProblemUucpData() {
		uucpStatusTable = createUucpStatusTable(dao.getUucpStatusProblems());
		createSelectSearchableTable(uucpStatusTable, 
				new String[] {"brCde","central"}, 
				new String[] {"Branch","Central"});
	}
	
	private CentSystemStatusTable createCentSysTable(List<CentralSystemDetails> list) {
		BeanItemContainer<CentralSystemDetails> cont = 
			new BeanItemContainer<CentralSystemDetails>(CentralSystemDetails.class, list);
		
		centSysTable = new CentSystemStatusTable(cont);
		centSysTable.setSortContainerPropertyId("central");
		centSysTable.addRowCountFooter();
		
		centSysTable.setSelectable(true);
		centSysTable.setImmediate(true);
		centSysTable.addListener(this);
		
		return centSysTable;
	}
	
	private UucpStatusTable createUucpStatusTable(List<UucpStatus> errors) {
		BeanItemContainer<UucpStatus> cont = 
			new BeanItemContainer<UucpStatus>(UucpStatus.class, errors);
		uucpStatusTable = new UucpStatusTable(cont);
		
		uucpStatusTable.setSortContainerPropertyId("central");
		uucpStatusTable.addRowCountFooter();
		
		uucpStatusTable.setSelectable(true);
		uucpStatusTable.setImmediate(true);
		uucpStatusTable.addListener(this);
		
		return uucpStatusTable;
	}

	private BranchInfoForm createBranchInfoForm(String brCde) {
		BranchInfo brInfo = dao.getBrInfo(brCde);
		if (brInfo != null) {
			return new BranchInfoForm(brInfo);
		}
		return null;
	}
	
	public void valueChange(ValueChangeEvent event) {
		Property prop = event.getProperty();
		
		if (prop == centSysTable) {
			if (centSysTable.getValue() == null) {
				setSplitPosition(100);
				return;
			}
			
			CentralSystemDetails dets = (CentralSystemDetails)centSysTable.getValue();
			uucpStatusTable = createUucpStatusTable(dao.getUucpStatusForCentral(dets.getCentral()));
			uucpStatusTable.setSelectable(false);
			setSecondComponent(uucpStatusTable);
			setSplitPosition(50);
			
		} else if (prop == uucpStatusTable) {
			if (uucpStatusTable.getValue() == null) {
				setSplitPosition(100);
				return;
			}
			
			UucpStatus status = (UucpStatus)uucpStatusTable.getValue();
			
			BranchInfoForm form = createBranchInfoForm(status.getBrCde());
			if (form == null) {
				TextArea ta = new TextArea();
				ta.setValue("No such branch in the branch table: " + status.getBrCde());
				ta.setReadOnly(true);
				ta.setSizeFull();
				setSecondComponent(ta);
			} else {
				setSecondComponent(form);
			}
			setSplitPosition(50);
		}
	}
}
