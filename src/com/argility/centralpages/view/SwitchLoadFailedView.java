package com.argility.centralpages.view;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchLoadFailedDAO;
import com.argility.centralpages.ui.SwitchLoadFailedTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public abstract class SwitchLoadFailedView extends VerticalSplitPanel implements
	Property.ValueChangeListener {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected CentralpagesApplication app;
	protected SwitchLoadFailedDAO dao;
	
	protected SwitchLoadFailedTable table;
	
	public SwitchLoadFailedView(CentralpagesApplication app) {
		this.app = app;
		dao = (SwitchLoadFailedDAO)app.getSpringContext().getBean("switchLoadFailedDAO");
		
		setSizeFull();
		addStyleName("view");
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == table) {
			Item item = table.getItem(table.getValue());
			
			if (item == null) {
				setSplitPosition(100);
			} else {
				TextArea ta = new TextArea(item.getItemProperty("stackTrace"));
				
				ta.setReadOnly(true);
				ta.setSizeFull();
				setSecondComponent(ta);
				setSplitPosition(40);
			}
		}
		
	}
	
}
