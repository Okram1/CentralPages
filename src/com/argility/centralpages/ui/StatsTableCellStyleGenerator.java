package com.argility.centralpages.ui;

import com.argility.centralpages.data.StatsProd;
import com.vaadin.ui.Table.CellStyleGenerator;

@SuppressWarnings("serial")
public class StatsTableCellStyleGenerator implements CellStyleGenerator {

	public static String errorField = "highlight";
	
	public String getStyle(Object itemId, Object propertyId) {
		StatsProd row = (StatsProd)itemId;
		String style = null;
		
		if (propertyId == null) return null; 
		
		if (propertyId.equals("swCrashAudId")) {
			if (row.getSwCrashAudId() != null && row.getSwCrashAudId() > 0) {
				style = errorField;
			}
		} else if (propertyId.equals("swDiff")) {
			if (row.getSwDiff() > 5000) {
				style = errorField;
			}
		} else if (propertyId.equals("swCrashed")) {
			if (row.getSwCrashed()) {
				style = errorField;
			}
		} else if (propertyId.equals("replDiff")) {
			if (row.getReplDiff() != null && row.getReplDiff() > 1000) {
				style = errorField;
			}
		}
		
		return style;
	}

}
