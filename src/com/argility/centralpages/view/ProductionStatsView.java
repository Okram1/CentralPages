package com.argility.centralpages.view;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.ProductionStatsDAO;
import com.argility.centralpages.ui.ProductionStatsTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class ProductionStatsView extends VerticalSplitPanel implements
	Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected CentralpagesApplication app;
	protected ProductionStatsDAO dao;
	
	protected ProductionStatsTable table;
	
	public ProductionStatsView(CentralpagesApplication app) {
		this.app = app;
		dao = (ProductionStatsDAO)app.getSpringContext().getBean("productionStatsDAO");
		
		setSizeFull();
		addStyleName("view");
	}
	
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		int splitPos = 60;
		TextArea ta = null;
		
		if (property == table) {
			Item item = table.getItem(table.getValue());
			
			if (item == null) {
				setSplitPosition(100);
			} else {
				Property prop = item.getItemProperty("stackTrace");
				if (prop == null || "".equals(prop.getValue())) {
					prop.setValue("Log entry is just a notification, there is no error stack trace");
					splitPos = 90;
				} 
				
				ta = new TextArea(prop);
				ta.setWidth("100%");
				ta.setReadOnly(true);
				ta.setSizeFull();
				setSecondComponent(ta);
				setSplitPosition(splitPos);
			}
		}
		
	}

}
