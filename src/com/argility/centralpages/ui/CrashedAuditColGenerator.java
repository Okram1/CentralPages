package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.ProductionStatsView;
import com.vaadin.data.Property;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class CrashedAuditColGenerator implements ColumnGenerator, ClickListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public Component generateCell(Table source, Object itemId, Object columnId) {
		Property prop =
            source.getItem(itemId).getItemProperty(columnId);
		
		String value = prop.getValue() + "";
		
		int audit = 0;
		try {
			audit = Integer.parseInt(value);
		} catch (Exception e) {}
		
		if (prop.getValue() != null && audit > 0) {
			
			Button action = new Button(value+"", (ClickListener)this);
			action.setIcon(new ThemeResource("icons/magnifier.png"));
			action.setStyleName(Runo.BUTTON_LINK);
			return action;
		}
		
		TextField tf = new TextField(prop);
		tf.setReadOnly(true);
		
		return tf;
	}

	public void buttonClick(ClickEvent event) {
		log.info("Selected audit " + event.getButton().getCaption());
		Integer audId = Integer.parseInt(event.getButton().getCaption());
		
		ProductionStatsView view = new ProductionStatsView();
		view.wireSearchByAudit(audId);
		
		CentralpagesApplication.getInstance().setMainView(view, true);
		
	}

}
