package com.argility.centralpages.ui.table;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public abstract class AbstractTable extends Table {

	protected transient Logger log = Logger
		.getLogger(this.getClass().getName());
	
	private Object totalProperty;
	
	/**
	 * Add a row count footer for the specified property, so total number of rows
	 * @param property
	 */
	public void addRowCountFooter(Object property) {
		Container cont = getContainerDataSource();
		Object col = property;
		
		if (cont == null) {
			return;
		}
		
		if (property == null && getVisibleColumns() != null) {
			col = getVisibleColumns()[0];
		} else if (property == null) {
			return;
		}
		
		setFooterVisible(true);
		setColumnFooter(col,"<b>" + cont.size() + " Rows<b/>");
	}
	
	/**
	 * Add a count of the total number of rows in the first columns footer
	 */
	public void addRowCountFooter() {
		addRowCountFooter(null);
	}
	
	/**
	 * add a sum of all columns at the bottom of the totalProperty
	 */
	public void addTotalSumFooter() {
		addTotalSumFooter(getTotalProperty());
	}
	
	/**
	 * Use this method to sum up all the values for a column and add a total footer
	 * @param property - column you want to sum up and 
	 */
	public void addTotalSumFooter(Object property) {
		Container cont = getContainerDataSource();
		int total = 0;
		
		if (property == null) return; 
		Class<?> type = cont.getType(property);
		
		// Only integer supported for now
		if (type != Integer.class) {
			totalProperty = null;
			return;
		}
		
		totalProperty = property;
		
		for (Iterator<?> iterator = cont.getItemIds().iterator(); iterator.hasNext();) {
			Object itemId = iterator.next();
			Item item = cont.getItem(itemId);
			Property prop = item.getItemProperty(property);
			total += Integer.parseInt(prop.getValue()+"");
		}

		if (totalProperty != null) {
			setFooterVisible(true);
			setColumnFooter(property,"<b>" + total + "<b/>");
		}

	}

	public Object getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(Object totalProperty) {
		this.totalProperty = totalProperty;
	}
}
