package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.StatsProd;
import com.argility.centralpages.data.StatsProdContainer;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Property;

@SuppressWarnings("serial")
public class StatsProdOverviewView extends StatsProdView {

	public StatsProdOverviewView(CentralpagesApplication app) {
		super(app);
		
		StatsProdContainer cont = new StatsProdContainer(StatsProd.class, dao.getAllStatsProd());
		
		table = new StatsProdTable(cont);
		
		table.setVisibleColumns(StatsProdContainer.NATURAL_COL_ORDER);
		table.setColumnHeaders(StatsProdContainer.COL_HEADERS_ENGLISH);

		table.setSortContainerPropertyId("brCde");
		
		table.addBrTotalCountFooter(cont);

		setFirstComponent(table);
		setSplitPosition(100);
		
		table.addListener((Property.ValueChangeListener) this);
	}

}
