package com.argility.centralpages.ui.table;

import com.argility.centralpages.data.SwitchingAgingCount;

@SuppressWarnings("serial")
public class AgingTableCellStyleGenerator extends AbstractCellStyleGenerator {

	public String getStyle(Object itemId, Object propertyId) {
		SwitchingAgingCount cnt = (SwitchingAgingCount)itemId;
		String style = null;
		
		if (propertyId == null || cnt == null) {
			return style;
		}
		
		if (cnt.getBrCde() != null &&
				propertyId.equals("totalCount") && 
				cnt.getTotalCount() > 10000) {
			style = PROBLEM;
		} else if (propertyId.equals("total12monthsPlus") && cnt.getTotal12monthsPlus() > 500) {
			style = PROBLEM;
		} else if (cnt.getBrCde() != null && 
				propertyId.equals("total5days") && 
				cnt.getTotal5days() > 3000) {
			style = PROBLEM;
		}
		
		return style;
	}

}
