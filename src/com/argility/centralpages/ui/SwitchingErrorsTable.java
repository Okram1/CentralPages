package com.argility.centralpages.ui;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SwitchingErrorsTable extends Table {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "audId", "oboBrCde", "swAudId", "actTyp", "actDesc", "error"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Audit", "OBO br", "Sw audit", "Action typ", "Action Description", "Error"
	};
	
	public SwitchingErrorsTable() {
		new SwitchingErrorsTable(null);
	}

	public SwitchingErrorsTable(String caption) {
		new SwitchingErrorsTable(caption, null);
	}

	public SwitchingErrorsTable(String caption, Container dataSource) {
		super(caption, dataSource);
		
		setColumnCollapsingAllowed(true);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setCaption("Act Description");
		setSizeFull();
		
		addBrTotalCountFooter(dataSource);
	}

	public void addBrTotalCountFooter(Container cont) {
		setFooterVisible(true);
		setColumnFooter("brCde", cont.size() + " Rows");
	}
}
