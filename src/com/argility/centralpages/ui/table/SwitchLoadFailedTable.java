package com.argility.centralpages.ui.table;

import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class SwitchLoadFailedTable extends AbstractTable {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "audId", "actTyp", "actDesc", "dateTime", "oboBrCde", "oboAudId"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Audit", "ActTyp", "Action Description", "Date", "OBO Branch", "OBO Audit"
	};
	
	public SwitchLoadFailedTable(String caption, Container dataSource) {
	
		setCaption(caption);
		setContainerDataSource(dataSource);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setSelectable(true);
		setImmediate(true);
		
		addRowCountFooter("brCde");

	}
	
}
