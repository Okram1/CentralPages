package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.StatsProd;
import com.argility.centralpages.data.StatsProdContainer;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class ImportSwitchingFailedView extends StatsProdView implements
		Property.ValueChangeListener {
	
	public ImportSwitchingFailedView(CentralpagesApplication app) {
		super(app);
		
		StatsProdContainer cont = new StatsProdContainer(StatsProd.class, dao.getSwImportFailedList());
		
		table = new StatsProdTable(cont);
		
		table.setVisibleColumns(new String[] {"brCde","central","lastSwLoad","swDiff", "swCrashAudId"});
		table.setColumnHeaders(new String[] {"Branch","Central","Last Switching Load","Trans os", "Crash audit id"});
		table.setSortContainerPropertyId("lastSwLoad");
		
		table.addBrTotalCountFooter(cont);

		setFirstComponent(table);
		setSplitPosition(100);
		
		table.addListener((Property.ValueChangeListener) this);
	}
	
}
