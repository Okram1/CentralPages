package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.data.CentralSystemDetails;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.table.CentSystemStatusTable;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class CentSystemDetailsView extends AbstractVerticalSplitPanel {

	private SystemStatsDAO dao;
	private CentSystemStatusTable table;
	
	public CentSystemDetailsView() {
		dao = (SystemStatsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("systemStatsDAO");
	
		setSizeFull();
	}
	
	public void wireAllCentralSystemData() {
		table = createCentSysTable(dao.getCentralSystemDetails());
		createSingleColumnSearchableTable(table, "central", "Enter central and hit enter to search.");
	}
	
	private CentSystemStatusTable createCentSysTable(List<CentralSystemDetails> list) {
		BeanItemContainer<CentralSystemDetails> cont = 
			new BeanItemContainer<CentralSystemDetails>(CentralSystemDetails.class, list);
		
		table = new CentSystemStatusTable(cont);
		table.setSortContainerPropertyId("central");
		table.addRowCountFooter();
		
		return table;

	}
}
