package com.argility.centralpages.ui.table;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwitchingErrorsView;
import com.argility.centralpages.ui.view.SwitchingTranView;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class ActTypCountTable extends AbstractTable implements ClickListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private boolean linkToErrors = true;
	
	public ActTypCountTable(boolean linkToErrors, Container cont) {
		super();
		
		this.linkToErrors = linkToErrors;
		
		setContainerDataSource(cont);
		
		if (linkToErrors) {
			setVisibleColumns(new Object[] {"actTyp","actionDesc","count","error"});
			setColumnHeaders(new String[] {"Action Type","Transaction Description","Total", "Error Message"});
		} else {
			setVisibleColumns(new Object[] {"actTyp","actionDesc","count"});
			setColumnHeaders(new String[] {"Action Type","Transaction Description","Total"});
		}
		
		setColumnExpandRatio("actionDesc", 1f);
		
		addGeneratedColumn("actTyp", new ColumnGenerator() {
			
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Property prop =
		            source.getItem(itemId).getItemProperty(columnId);
				
				Button action = new Button(prop.getValue()+"", (ClickListener)source);
				action.setDescription("Click to display all action type " + prop.getValue() + " transactions.");
				action.setStyleName(Runo.BUTTON_LINK);
				return action;
			}
		});
		
		setSizeFull();
		setColumnReorderingAllowed(true);
		
		addRowCountFooter();
	}

	public void buttonClick(ClickEvent event) {
		String value = event.getButton().getCaption();
		
		log.info("Selected type " + value);
		
		if (linkToErrors) {
			Integer actTyp = Integer.valueOf(event.getButton().getCaption());
			SwitchingErrorsView view = new SwitchingErrorsView();
			view.wireByActionType(actTyp);
			CentralpagesApplication.getInstance().setMainView(view, true);
		} else {
			SwitchingTranView view = new SwitchingTranView();
			Integer actTyp = Integer.parseInt(value);
			view.wireSwitchingByActionTypeData(actTyp);
			CentralpagesApplication.getInstance().setMainView(view, true);
		}
	}
}
