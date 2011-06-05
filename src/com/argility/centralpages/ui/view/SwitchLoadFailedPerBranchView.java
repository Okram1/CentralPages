package com.argility.centralpages.ui.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.SwitchLoadFailed;
import com.argility.centralpages.ui.SwitchLoadFailedTable;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SwitchLoadFailedPerBranchView extends SwitchLoadFailedView {

	private TextField tf = new TextField("Branch: ");
	private Form form;
	
	public SwitchLoadFailedPerBranchView(CentralpagesApplication app) {
		super(app);
				
		setFirstComponent(getBranchSearchForm());
		setSplitPosition(100);
		
		tf.addListener(this);
	}
	
	private Form getBranchSearchForm() {
		
		form = new Form();
		
		final TextField tf = new TextField("Branch: ");
		tf.setColumns(20);
		
		tf.addValidator(new RegexpValidator("[0-9][0-9][0-9][0-9]", "Must be 4 digits long!"));
		tf.setRequired(true);
		tf.setRequiredError("Required field");
		tf.setInputPrompt("Enter a 4 digit branch");

		Button b = new Button("Search", new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				if (!tf.isValid()) {
					return;
				}
				
				createTable(tf.getValue()+"");
				
			}
		});
		
		form.addField("branch", tf);
		form.addField("submit", b);

		return form;
	}

	private void createTable(String brCde) {
		
		BeanItemContainer<SwitchLoadFailed> cont = new BeanItemContainer<SwitchLoadFailed>(SwitchLoadFailed.class, 
				dao.getSwitchLoadFailedFromBranch(brCde));
		
		table = new SwitchLoadFailedTable(null, cont);
		table.setSortContainerPropertyId("dateTime");
		table.setSortAscending(false);
		
		setFirstComponent(table);
		setSplitPosition(100);
		
		setSizeFull();
		table.setSizeFull();
		
		table.addListener((Property.ValueChangeListener) this);
	}
	
}
