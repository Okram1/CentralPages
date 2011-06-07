package com.argility.centralpages.ui;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.ProductionStatsView;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
public class BranchStatsProdColGenerator implements ColumnGenerator, ClickListener {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public Component generateCell(Table source, Object itemId, Object columnId) {
		
		Property prop =
            source.getItem(itemId).getItemProperty(columnId);
		
		Object value = prop.getValue();
		if (value != null) {
			Button action = new Button(value+"", (ClickListener)this);
			action.setDescription("Click to display the full log for branch " + value);
			//action.setIcon(new ThemeResource("icons/magnifier.png"));
			action.setStyleName(Runo.BUTTON_LINK);
			return action;
		}
		
		TextField tf = new TextField(prop);
		tf.setReadOnly(true);

		return tf;
	}

	public void buttonClick(ClickEvent event) {
		log.info("Selected branch " + event.getButton().getCaption());
		String brCde = event.getButton().getCaption();
		
		ProductionStatsView view = new ProductionStatsView();
		view.wireSearchByBranch(brCde);
		
		CentralpagesApplication.getInstance().setMainView(view, true);
		
	}

}
