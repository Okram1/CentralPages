package com.argility.centralpages.ui.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.ProductionStats;
import com.argility.centralpages.ui.ProductionStatsTable;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class ProductionStatsAllView extends ProductionStatsView {

	public ProductionStatsAllView(CentralpagesApplication app) {
		super(app);
		
		BeanItemContainer<ProductionStats> cont = new BeanItemContainer<ProductionStats>(ProductionStats.class, 
				dao.getAllProductionStats());
		
		table = new ProductionStatsTable(null, cont);
		table.setSortContainerPropertyId("dateTime");
		table.setSortAscending(false);
		
		setFirstComponent(table);
		setSplitPosition(100);
		
		setSizeFull();
		table.setSizeFull();
		
		table.addListener((Property.ValueChangeListener) this);
	}

}
