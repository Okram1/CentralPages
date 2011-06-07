package com.argility.centralpages.ui;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class UucpStatusTable extends Table {

	public UucpStatusTable(Container cont) {
		
		setContainerDataSource(cont);
		
		setVisibleColumns(new Object[] {"brCde","central","lastUucpComm","message"});
		setColumnHeaders(new String[] {"Branch Code","Central","Last uucp communication","Status message"});
		
		setSizeFull();
		
		setCellStyleGenerator(new UucpStatusCellStyleGenerator());
	}
	
	public void addBrTotalCountFooter() {
		setFooterVisible(true);
		setColumnFooter("brCde", getContainerDataSource().size() + " Rows");
	}

	
}
