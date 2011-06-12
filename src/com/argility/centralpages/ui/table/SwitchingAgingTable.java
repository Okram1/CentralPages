package com.argility.centralpages.ui.table;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SwitchingAgingTable extends Table {

	public static final Object[] COLUMNS = new Object[] {
		"brCde","actTyp","actDesc","totalCount","total2days","total5days","total10days","total6months","total12months","total12monthsPlus"
	};
	
	public static final Object[] BR_COLUMNS = new Object[] {
		"brCde","totalCount","total2days","total5days","total10days","total6months","total12months","total12monthsPlus"
	};
	
	public static final Object[] TYPE_COLUMNS = new Object[] {
		"actTyp","actDesc","totalCount","total2days","total5days","total10days","total6months","total12months","total12monthsPlus"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"OBO Branch","Type","Transaction Description","Total","2 days","5 days","10 days","6 months","12 months","12 + months"
	};
	
	public static final String[] COL_HEADINGS_BR = new String[] {
		"OBO Branch","Total","2 days","5 days","10 days","6 months","12 months","12 + months"
	};
	
	public static final String[] COL_HEADINGS_TYPE = new String[] {
		"Type","Transaction Description","Total","2 days","5 days","10 days","6 months","12 months","12 + months"
	};
	
	public SwitchingAgingTable(Container cont) {
		setContainerDataSource(cont);
		
		setVisibleColumns(COLUMNS);
		setColumnHeaders(COL_HEADINGS);
		
		setSortContainerPropertyId("totalCount");
		setColumnExpandRatio("actDesc", 1f);
		setSortAscending(false);
		
		setCellStyleGenerator(new AgingTableCellStyleGenerator());
	}

}
