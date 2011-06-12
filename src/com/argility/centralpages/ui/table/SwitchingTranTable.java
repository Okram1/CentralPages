package com.argility.centralpages.ui.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Container;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class SwitchingTranTable extends Table {

	public static final Object[] COL_HEADINGS  = new Object[] {
		"swAudId","brCde","audId","audTs","oboBrCde","actTyp","actDesc","swAudDte"
	};
	
	public static final String[] HEADING_NAMES  = new String[] {
		"Switch aud","Branch","Audit","Audit timestamp","OBO Branch",
		"Action Type","Action Description","Import timestamp"
	};
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	public SwitchingTranTable(Container cont) {
		
		setContainerDataSource(cont);
		
		setVisibleColumns(COL_HEADINGS);
		setColumnHeaders(HEADING_NAMES);
		
		setSortContainerPropertyId("swAudId");
		setSortAscending(false);
		
		setSizeFull();
	}
	
	public void addBrTotalCountFooter() {
		setFooterVisible(true);
		setColumnFooter("swAudId", getContainerDataSource().size() + " Rows");
	}
	
	@Override
	protected String formatPropertyValue(Object rowId, Object colId,
			com.vaadin.data.Property property) {
		if (property.getType() == Date.class && property.getValue() != null) {
			return sdf.format(property.getValue());
		}

		if (property.getType() == Boolean.class) {
			boolean val = (Boolean) property.getValue();
			if (val) {
				return "YES";
			} else {
				return "NO";
			}
		}

		return super.formatPropertyValue(rowId, colId, property);
	};
}
