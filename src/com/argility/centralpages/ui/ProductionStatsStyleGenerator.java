package com.argility.centralpages.ui;

import com.argility.centralpages.data.ProductionStats;
import com.vaadin.ui.Table.CellStyleGenerator;

@SuppressWarnings("serial")
public class ProductionStatsStyleGenerator implements CellStyleGenerator {

	public static String highlight = "highlight";
	
	public String getStyle(Object itemId, Object propertyId) {
		ProductionStats stats = (ProductionStats)itemId;
		String style = null;
		
		if (propertyId == null) return null;
		
		if (stats.getStackTrace() != null && !"".equals(stats.getStackTrace())) {
			style = highlight;
		}
		
		return style;
	}

}
