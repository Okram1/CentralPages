package com.argility.centralpages.ui.view;

import java.util.List;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.data.UucpStatus;
import com.argility.centralpages.ui.UucpStatusTable;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class UucpStatusView extends VerticalSplitPanel {

	private SystemStatsDAO dao;
	private UucpStatusTable table;
	
	public static String PROBLEM = "problem";
	public static String WARN = "warn";
	
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
		
		setFirstComponent(table);
		setSplitPosition(100);
	}

}
