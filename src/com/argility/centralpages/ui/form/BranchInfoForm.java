package com.argility.centralpages.ui.form;

import org.apache.log4j.Logger;

import com.argility.centralpages.data.BranchInfo;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class BranchInfoForm extends Form {

	public static final Object[] VISIBLE_PROPERTIES = {
		"brCde","brDesc","brPhone","brActive","brTrading","grpCde","ctryCde","brClosedDate"
	};
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public BranchInfoForm(BranchInfo brInfo) {
		Item i = new BeanItem<BranchInfo>(brInfo);
		
		setItemDataSource(i);
		
		setVisibleItemProperties(VISIBLE_PROPERTIES);
		
		setReadOnly(true);

	}
	
	@Override
	protected void attachField(Object propertyId, Field field) {
		if (field instanceof TextField) {
			((TextField) field).setColumns(20);
		}
		super.attachField(propertyId, field);
	}
}
