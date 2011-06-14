package com.argility.centralpages.ui.table;

import org.apache.log4j.Logger;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public abstract class AbstractTable extends Table {

	protected transient Logger log = Logger
		.getLogger(this.getClass().getName());
	
	public void addCountFooter(Object property) {
		Container cont = getContainerDataSource();
		Object col = property;
		
		if (cont == null) {
			return;
		}
		
		if (property == null && getVisibleColumns() != null) {
			col = getVisibleColumns()[0];
		} else {
			return;
		}
		
		setFooterVisible(true);
		setColumnFooter(col,"<b>" + cont.size() + " Rows<b/>");
	}
	
	public void addCountFooter() {
		addCountFooter(null);
	}
}
