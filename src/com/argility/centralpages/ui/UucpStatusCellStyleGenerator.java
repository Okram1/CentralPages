package com.argility.centralpages.ui;

import com.argility.centralpages.data.UucpStatus;
import com.vaadin.ui.Table.CellStyleGenerator;

@SuppressWarnings("serial")
public class UucpStatusCellStyleGenerator implements CellStyleGenerator{

	public static String PROBLEM = "problem";
	public static String WARN = "warn";
	
	public String getStyle(Object itemId, Object propertyId) {
		
		UucpStatus status = (UucpStatus)itemId;
		
		if (propertyId == null) return null; 
		String prop = (String)propertyId;
		
		if (prop.equals("message") && status.getMessage() != null) {
			if(!status.getMessage().matches("Conversation complete")) {
				return PROBLEM;
			}
		}
		
		return null;
	}

}
