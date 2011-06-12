package com.argility.centralpages.ui.table;

import com.argility.centralpages.data.ProductionStats;

@SuppressWarnings("serial")
public class ProductionStatsStyleGenerator extends AbstractCellStyleGenerator {

	public String getStyle(Object itemId, Object propertyId) {
		ProductionStats stats = (ProductionStats)itemId;
		String style = null;
		
		if (propertyId == null) return null;
		
		if (stats.getStackTrace() != null && !"".equals(stats.getStackTrace())) {
			style = PROBLEM;
		}
		
		return style;
	}

}
