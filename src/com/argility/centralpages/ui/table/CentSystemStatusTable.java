package com.argility.centralpages.ui.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class CentSystemStatusTable extends AbstractTable {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"central", "updateDate", "comms", "jbossRunning", "dbRunning","cronRunning",
		"jmsQueueSize","replFilesToProcess"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Central", "Update Date", "comms", "Jboss running", "DB running", "Cron running", 
		"JMS queue size", "Replication files to process"
	};

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	public CentSystemStatusTable(Container cont) {
		setContainerDataSource(cont);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setCellStyleGenerator(new CentSystemDetailsCellStyleGen());
		setSizeFull();
	}
	
	@Override
	protected String formatPropertyValue(Object rowId, Object colId,
			com.vaadin.data.Property property) {
		
		if (property == null) {
			return super.formatPropertyValue(rowId, colId, property);
		}
		
		if (property.getValue() != null && property.getType() == Date.class) {
			return sdf.format(property.getValue());
		}

		if (property.getValue() != null && property.getType() == Boolean.class) {
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
