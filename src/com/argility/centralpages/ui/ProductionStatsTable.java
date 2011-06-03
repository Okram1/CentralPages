package com.argility.centralpages.ui;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ProductionStatsTable extends Table {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "dateTime", "audId", "message"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Date", "audit", "Message"
	};
	
	public ProductionStatsTable() {
		new SwitchLoadFailedTable(null);
	}

	public ProductionStatsTable(String caption) {
		super(caption);
		new ProductionStatsTable(caption, null);
	}

	public ProductionStatsTable(String caption, Container dataSource) {
		super(caption, dataSource);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setSelectable(true);
		setImmediate(true);

	}
}
