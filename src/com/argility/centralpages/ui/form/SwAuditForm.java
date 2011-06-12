package com.argility.centralpages.ui.form;

import java.util.Date;

import org.apache.log4j.Logger;

import com.argility.centralpages.data.SwAudit;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;

@SuppressWarnings("serial")
public class SwAuditForm extends Form {

	public static final Object[] VISIBLE_PROPERTIES = {
		"swAudId", "audId", "swAudDte", "audTs", "actTyp", "usrId", "audDeviceId", 
		"fppCde", "audDocNo", "audDocTs", "brCde"
	};
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	
	public SwAuditForm(SwAudit data) {
		
		getLayout().setMargin(true);
		
		setItemDataSource(new BeanItem<SwAudit>(data));
		
		setVisibleItemProperties(VISIBLE_PROPERTIES);
		
		setReadOnly(true);
	}
	
	@Override
	protected void attachField(Object propertyId, Field field) {
		
		Field f = field;
		
		if (f.getType() == Date.class) {
			DateField df = (DateField)f;
			df.setResolution(DateField.RESOLUTION_MIN);
			f = df;
		}
		
		super.attachField(propertyId, f);
		
	}

}
