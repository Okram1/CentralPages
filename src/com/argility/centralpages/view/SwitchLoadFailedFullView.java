package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.SwitchLoadFailed;
import com.argility.centralpages.ui.SwitchLoadFailedTable;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class SwitchLoadFailedFullView extends SwitchLoadFailedView {

	public SwitchLoadFailedFullView(CentralpagesApplication app) {
		super(app);
		
		BeanItemContainer<SwitchLoadFailed> cont = new BeanItemContainer<SwitchLoadFailed>(SwitchLoadFailed.class, 
				dao.getAllSwitchLoadFailed());
		
		table = new SwitchLoadFailedTable(null, cont);
		table.setSortContainerPropertyId("dateTime");
		table.setSortAscending(false);
		
		setFirstComponent(table);
		setSplitPosition(100);
		
		setSizeFull();
		table.setSizeFull();
		
		table.addListener((Property.ValueChangeListener) this);
	}
	
}
