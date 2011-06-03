package com.argility.centralpages.ui.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.StatsProd;
import com.argility.centralpages.data.StatsProdContainer;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class CentXoutReceivedNotProcessedView extends StatsProdView {

	public CentXoutReceivedNotProcessedView(CentralpagesApplication app) {
		super(app);
		
		StatsProdContainer cont = new StatsProdContainer(StatsProd.class, dao.getXoutReceivedNotProcessedList());
		
		table = new StatsProdTable(cont);
		
		table.setVisibleColumns(new String[] {"brCde","central","lastReplicated","xoutReceived", "replDiff"});
		table.setColumnHeaders(new String[] {"Branch","Central","Last Replicated","Xout Received", "Repl Diff"});
		
		table.setSortContainerPropertyId("lastReplicated");
		
		table.addBrTotalCountFooter(cont);
		
		setFirstComponent(table);
		setSplitPosition(100);

		table.addListener((Property.ValueChangeListener) this);

	}

}
