package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwitchingErrorsView;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class ActTypCountTable extends Table implements ClickListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public ActTypCountTable(String caption, Container cont) {
		super();
		
		setContainerDataSource(cont);
		
		setVisibleColumns(new Object[] {"actTyp","actionDesc","count","error"});
		setColumnHeaders(new String[] {"Action Type","Transaction Description","Total", "Error Message"});
		
		addGeneratedColumn("actTyp", new ColumnGenerator() {
			
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Property prop =
		            source.getItem(itemId).getItemProperty(columnId);
				
				Button action = new Button(prop.getValue()+"", (ClickListener)source);
				action.setStyleName(Runo.BUTTON_LINK);
				return action;
			}
		});
		
		setColumnReorderingAllowed(true);
	}

	public void buttonClick(ClickEvent event) {
		log.info("Selected type " + event.getButton().getCaption());
		Integer actTyp = Integer.valueOf(event.getButton().getCaption());
		SwitchingErrorsView view = new SwitchingErrorsView();
		view.wireByActionType(actTyp);
		CentralpagesApplication.getInstance().setMainView(view, true);
	}
}
