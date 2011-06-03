package com.argility.centralpages.ui;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SwitchLoadFailedTable extends Table {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "audId", "actTyp", "actDesc", "dateTime", "oboBrCde", "oboAudId"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Audit", "ActTyp", "Action Description", "Date", "OBO Branch", "OBO Audit"
	};
	
	public SwitchLoadFailedTable() {
		new SwitchLoadFailedTable(null);
	}

	public SwitchLoadFailedTable(String caption) {
		super(caption);
		new SwitchLoadFailedTable(caption, null);
	}

	public SwitchLoadFailedTable(String caption, Container dataSource) {
		super(caption, dataSource);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setSelectable(true);
		setImmediate(true);

	}

}
