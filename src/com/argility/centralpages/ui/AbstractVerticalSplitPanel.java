package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class AbstractVerticalSplitPanel extends VerticalSplitPanel {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected TextField searchField = new TextField();
	protected Table searchTable;
	
	public void createSearchableTable(Table table, String searchProp, String searchPrompt) {
		log.info("createSearchableTable");
		searchTable = table;
		
		VerticalLayout vl = new VerticalLayout();
		vl.setSizeFull();
		vl.setMargin(false);
		vl.setSpacing(false);
		
		searchField.removeListener((Property.ValueChangeListener)this);
		
		searchField.setCaption(null);
		searchField.setValue("");
		searchField.setInputPrompt(searchPrompt);
		searchField.setColumns(30);
		searchField.setData(searchProp);
		searchField.setImmediate(true);
		
		vl.addComponent(searchField);
		vl.addComponent(searchTable);
		
		vl.setExpandRatio(searchTable, 1f);
		
		searchField.addListener((Property.ValueChangeListener)this);
		
		setFirstComponent(vl);
		setSplitPosition(100);
	}
	
	protected void applySearch() {
		log.info("applySearch " + searchTable);
		if (searchTable == null) {
			return;
		}
		
		Container c = searchTable.getContainerDataSource();
		if (c instanceof BeanItemContainer<?>) {
			((BeanItemContainer<?>)c).removeAllContainerFilters();
			((BeanItemContainer<?>)c).addContainerFilter(searchField.getData(),
					searchField.getValue()+"" ,true, true);
		}
	}
}
