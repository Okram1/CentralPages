package com.argility.centralpages.ui.table;

import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class ProductionStatsTable extends AbstractTable {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "dateTime", "audId", "message"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Date", "Audit", "Message"
	};
	
	public ProductionStatsTable(String caption, Container dataSource) {

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
