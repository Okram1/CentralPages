package com.argility.centralpages.ui.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.argility.centralpages.data.BranchProdDetailsContainer;
import com.argility.centralpages.ui.BranchStatsProdColGenerator;
import com.argility.centralpages.ui.CrashedAuditColGenerator;

@SuppressWarnings("serial")
public class BranchProdDetailsTable extends AbstractTable {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

	public BranchProdDetailsTable(BranchProdDetailsContainer cont) {

		setSizeFull();

		setSelectable(true);
		setImmediate(true);

		setColumnCollapsingAllowed(true);
		setColumnReorderingAllowed(true);

		setContainerDataSource(cont);

		setCellStyleGenerator(new BranchProdDetsTableCellStyleGenerator());
		
		addGeneratedColumn("swCrashAudId", new CrashedAuditColGenerator());
		addGeneratedColumn("brCde", new BranchStatsProdColGenerator());
		
		addCountFooter("brCde");
		// addStyleName("view");
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
