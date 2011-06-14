package com.argility.centralpages.ui.table;

import com.argility.centralpages.data.BranchMeDetails;


@SuppressWarnings("serial")
public class BranchMeDetailsCellStyleGen extends AbstractCellStyleGenerator {

	public String getStyle(Object itemId, Object propertyId) {
		BranchMeDetails row = (BranchMeDetails)itemId;
		
		String style = null;
		
		if (propertyId == null) {
			return style;
		}
		
		if (propertyId.equals("replLocked")) {
			if (row.getReplLocked() != null && row.getReplLocked()) {
				if (row.getReplProcess() != null && "CRASH".matches(row.getReplProcess())) {
					style = PROBLEM;
				} else {
					style = WARN;
				}
			}
		} else if (propertyId.equals("replProcess")) {
			if (row.getReplProcess() != null && row.getReplProcess().matches(".*CRASH.*")) {
				style = PROBLEM;
			}
		} else if (propertyId.equals("uucpMessage")) {
			if (row.getUucpMessage() != null) {
				if ("Talking".matches(row.getUucpMessage())) {
					style = WARN;
				} else if (!"Conversation complete".matches(row.getUucpMessage())) {
					style = PROBLEM;
				}
			}
		}

		return style;
	}

}
