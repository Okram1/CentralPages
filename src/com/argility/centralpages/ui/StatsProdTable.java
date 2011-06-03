package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.data.StatsProdContainer;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class StatsProdTable extends Table {
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public StatsProdTable(StatsProdContainer cont) {
		
		setSizeFull();
		
		setSelectable(true);
		setImmediate(true);
		
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);
		
		setContainerDataSource(cont);
		
		//addStyleName("view");
	}
	
	public void addBrTotalCountFooter(StatsProdContainer cont) {
		setFooterVisible(true);
		setColumnFooter("brCde", cont.size() + " Rows");
	}
}
