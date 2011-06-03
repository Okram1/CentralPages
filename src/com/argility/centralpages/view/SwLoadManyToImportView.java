package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.StatsProd;
import com.argility.centralpages.data.StatsProdContainer;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class SwLoadManyToImportView extends StatsProdView {

	public SwLoadManyToImportView(CentralpagesApplication app) {
		super(app);

		StatsProdContainer cont = new StatsProdContainer(StatsProd.class,
				dao.getSwManyToImportList());

		table = new StatsProdTable(cont);

		table.setVisibleColumns(new String[] {"brCde","central","lastSwLoad","swDiff", "swCrashed"});
		table.setColumnHeaders(new String[] {"Branch","Central","Last Switching load","Trans os", "Import Crashed"});
		table.setSortContainerPropertyId("swDiff");
		table.setSortAscending(false);

		table.addBrTotalCountFooter(cont);

		setFirstComponent(table);
		setSplitPosition(100);

		table.addListener((Property.ValueChangeListener) this);
	}

}
