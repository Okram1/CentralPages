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
		if (cont == null || property == null) {
			return;
		}
		setFooterVisible(true);
		setColumnFooter(property,"<b>" + cont.size() + " Rows<b/>");
	}
}
