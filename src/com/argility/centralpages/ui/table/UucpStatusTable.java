package com.argility.centralpages.ui.table;

import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class UucpStatusTable extends AbstractTable {

	public UucpStatusTable(Container cont) {
		
		setContainerDataSource(cont);
		
		setVisibleColumns(new Object[] {"brCde","central","lastUucpComm","message"});
		setColumnHeaders(new String[] {"Branch Code","Central","Last uucp communication","Status message"});
		
		setSizeFull();
		addCountFooter("brCde");
		
		setCellStyleGenerator(new UucpStatusCellStyleGenerator());
	}
		
}
