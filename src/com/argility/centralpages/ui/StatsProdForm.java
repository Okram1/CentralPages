package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.data.StatsProd;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public class StatsProdForm extends Form {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public StatsProdForm() {
		new StatsProdForm(new StatsProd());
	}
	
	public StatsProdForm(StatsProd data) {
		new StatsProdForm(new BeanItem<StatsProd>(data));
	}

	public StatsProdForm(Item item) {
		setItemDataSource(item);
		//setCaption("  Details ");
		setReadOnly(true);
	}
	
	@Override
	public void setItemDataSource(Item newDataSource) {
		super.setItemDataSource(newDataSource);
		setReadOnly(true);
		//setCaption("  Details ");
	} 
}
