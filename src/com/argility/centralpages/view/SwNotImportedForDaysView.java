package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.StatsProd;
import com.argility.centralpages.data.StatsProdContainer;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class SwNotImportedForDaysView extends StatsProdView {

	public SwNotImportedForDaysView(CentralpagesApplication app) {
		super(app);
		
		StatsProdContainer cont = new StatsProdContainer(StatsProd.class,
				dao.getSwNotImportedForDaysList());
		
		table = new StatsProdTable(cont);
		
		table.setVisibleColumns(new String[] {"brCde","central","lastReplicated","xoutReceived", "lastSwLoad", "swDiff"});
		table.setColumnHeaders(new String[] {"Branch","Central","Last Replicated","Xout Received", "Last Switching Load", "Switching Trans Outstanding"});
		table.setSortContainerPropertyId("lastSwLoad");
		
		table.addBrTotalCountFooter(cont);
		
		setFirstComponent(table);
		setSplitPosition(100);

		table.addListener((Property.ValueChangeListener) this);
	}

}
