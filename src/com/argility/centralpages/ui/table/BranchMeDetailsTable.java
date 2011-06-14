package com.argility.centralpages.ui.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.vaadin.data.Container;

@SuppressWarnings("serial")
public class BranchMeDetailsTable extends AbstractTable {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	
	public BranchMeDetailsTable(Container cont) {
		setContainerDataSource(cont);
		
		addCountFooter("brCde");
		
		setCellStyleGenerator(new BranchMeDetailsCellStyleGen());
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
	}
}
