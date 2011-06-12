package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.UucpStatus;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.form.BranchInfoForm;
import com.argility.centralpages.ui.table.UucpStatusTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.TextArea;

@SuppressWarnings("serial")
public class UucpStatusView extends AbstractVerticalSplitPanel implements Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private SystemStatsDAO dao;
	private UucpStatusTable table;
	
	public UucpStatusView() {
		dao = (SystemStatsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("systemStatsDAO");
		
		setSizeFull();
	}
	
	public void wireShowAllData() {
		createTable(dao.getAllUucpStatus());
	}
	
	public void wireShowProblemData() {
		createTable(dao.getUucpStatusProblems());
	}
	
	private void createTable(List<UucpStatus> errors) {
		BeanItemContainer<UucpStatus> cont = 
			new BeanItemContainer<UucpStatus>(UucpStatus.class, errors);
		table = new UucpStatusTable(cont);
		
		table.setSortContainerPropertyId("central");
		table.addBrTotalCountFooter();
		
		table.setSelectable(true);
		table.setImmediate(true);
		table.addListener(this);
		
		createSearchableTable(table, "brCde", "Entere branch code and hit enter to search.");
		
		//setFirstComponent(table);
		//setSplitPosition(100);
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
		if (prop == table) {
			if (table.getValue() == null) {
				setSplitPosition(100);
				return;
			}
			
			UucpStatus status = (UucpStatus)table.getValue();
			
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
		} else if (prop == searchField) {
			applySearch();
		}
	}

}
