package com.argility.centralpages.ui;

import org.joda.time.DateTime;

import com.argility.centralpages.data.StatsProd;
import com.vaadin.ui.Table.CellStyleGenerator;

@SuppressWarnings("serial")
public class StatsTableCellStyleGenerator implements CellStyleGenerator {

	public static String PROBLEM = "problem";
	public static String WARN = "warn";
	
	public String getStyle(Object itemId, Object propertyId) {
		StatsProd row = (StatsProd)itemId;
		String style = null;
		
		if (propertyId == null) return null; 
		
		if (propertyId.equals("swCrashAudId")) {
			if (row.getSwCrashAudId() != null && row.getSwCrashAudId() > 0) {
				style = PROBLEM;
			}
		} else if (propertyId.equals("swDiff")) {
			if (row.getSwDiff() > 6000) {
				style = PROBLEM;
			} else if (row.getSwDiff() > 3000) {
				style = WARN;
			}
		} else if (propertyId.equals("swCrashed")) {
			if (row.getSwCrashed()) {
				style = PROBLEM;
			}
		} else if (propertyId.equals("replDiff")) {
			if (row.getReplDiff() != null) {
				if (row.getReplDiff() > 5000) {
					style = PROBLEM;
				} else if (row.getReplDiff() > 1000) {
					style = WARN;
				}
			}
		} else if (propertyId.equals("replProcess")) {
			if (row.getReplProcess() != null && row.getReplProcess().matches(".*CRASH.*")) {
				style = PROBLEM;
			}
		} else if (propertyId.equals("triadProcess")) {
			if (row.getTriadProcess() != null && row.getTriadProcess().matches(".*CRASH.*")) {
				style = PROBLEM;
			}
		} else if (propertyId.equals("lastReplicated")) {
			if (row.getLastReplicated() != null && row.getXoutReceived() != null) {
				DateTime lastRepl = new DateTime(row.getLastReplicated().getTime());
				DateTime xoutRec = new DateTime(row.getXoutReceived().getTime());
				
				if (lastRepl.plusDays(2).isBefore(xoutRec.getMillis())) {
					style = PROBLEM;
				} else if (lastRepl.plusDays(1).isBefore(xoutRec.getMillis())) {
					style = WARN;
				}
			}
		/*} else if (propertyId.equals("lastSwLoad")) {
			if (row.getLastReplicated() != null && row.getLastSwLoad() != null) {
				DateTime lastRepl = new DateTime(row.getLastReplicated().getTime());
				DateTime swLoad = new DateTime(row.getLastSwLoad().getTime());
				
				if (swLoad.minusDays(1).isBefore(lastRepl.getMillis())) {
					style = errorField;
				}
			}*/
		}
		
		return style;
	}

}
