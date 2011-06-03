package com.argility.centralpages.view;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.StatsProdDAO;
import com.argility.centralpages.ui.StatsProdForm;
import com.argility.centralpages.ui.StatsProdTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public abstract class StatsProdView extends VerticalSplitPanel implements
	Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected CentralpagesApplication app;
	protected StatsProdDAO dao;
	protected StatsProdTable table;
	protected StatsProdForm form;
	
	public StatsProdView(CentralpagesApplication app) {
		this.app = app;
		dao = (StatsProdDAO)app.getSpringContext().getBean("statsProdDAO");
		addStyleName("view");
	}
	
	public void valueChange(ValueChangeEvent event) {
		
		Property property = event.getProperty();
		if (property == table) {
			Item item = table.getItem(table.getValue());
			if (form == null) {
				form = new StatsProdForm();
			} 
			
			form.setItemDataSource(item);
			setSecondComponent(form);

			if (item == null) {
				setSplitPosition(100);
			} else {
				setSplitPosition(40);
			}
		}
	}

}
