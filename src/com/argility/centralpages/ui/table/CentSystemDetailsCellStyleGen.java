package com.argility.centralpages.ui.table;

import com.argility.centralpages.data.CentralSystemDetails;

@SuppressWarnings("serial")
public class CentSystemDetailsCellStyleGen extends AbstractCellStyleGenerator {
	
	public String getStyle(Object itemId, Object propertyId) {
		CentralSystemDetails row = (CentralSystemDetails)itemId;
		String style = null;
		
		if (propertyId == null) return null; 

		if (propertyId.equals("comms")) {
			if (row.getComms() == null || !row.getComms()) {
				style = PROBLEM;
			} else {
				style = OK;
			}
		} else if (propertyId.equals("jbossRunning")) {
			if (row.getJbossRunning() == null || !row.getJbossRunning()) {
				style = PROBLEM;
			} else {
				style = OK;
			}
		} else if (propertyId.equals("dbRunning")) {
			if (row.getDbRunning() == null || !row.getDbRunning()) {
				style = PROBLEM;
			} else {
				style = OK;
			}
		} else if (propertyId.equals("cronRunning")) {
			if (row.getCronRunning() == null || !row.getCronRunning()) {
				style = PROBLEM;
			} else {
				style = OK;
			}
		} else if (propertyId.equals("jmsQueueSize")) {
			if (row.getJmsQueueSize() != null) {
				if (row.getJmsQueueSize() > 50) {
					style = PROBLEM;
				} else if (row.getJmsQueueSize() > 20) {
					style = WARN;
				}
			}
		} else if (propertyId.equals("replFilesToProcess")) {
			if (row.getReplFilesToProcess() != null) {
				if (row.getReplFilesToProcess() > 50) {
					style = PROBLEM;
				} else if (row.getReplFilesToProcess() > 30) {
					style = WARN;
				}
			}
		}
		
		return style;
	}

}
