package com.argility.centralpages.ui.table;

import com.argility.centralpages.data.UucpStatus;

@SuppressWarnings("serial")
public class UucpStatusCellStyleGenerator extends AbstractCellStyleGenerator {

	public String getStyle(Object itemId, Object propertyId) {
		
		UucpStatus status = (UucpStatus)itemId;
		
		if (propertyId == null) return null; 
		String prop = (String)propertyId;
		
		if (prop.equals("message") && status.getMessage() != null) {
			if(status.getMessage().matches("Talking")) {
				return WARN;
			} else if(!status.getMessage().matches("Conversation complete")) {
				return PROBLEM;
			}
		}
		
		return null;
	}

}
