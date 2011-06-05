package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingErrorsDAO;
import com.argility.centralpages.data.SwitchingErrors;
import com.argility.centralpages.ui.SwitchingErrorsTable;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class SwitchingErrorsView extends VerticalSplitPanel implements ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public static final String BR_SEARCH_OPT = "Search using branch";
	public static final String TYPE_SEARCH_OPT = "Search using action type";
	public static final String OBO_BR_SEARCH_OPT = "Search using obo branch";
	
	protected SwitchingErrorsDAO dao;

	public SwitchingErrorsView() {
		
		dao = (SwitchingErrorsDAO)CentralpagesApplication.getInstance()
					.getSpringContext().getBean("switchingErrorsDAO");
		
		setSizeFull();
	}
	
	public void createCaptureForm() {
		final Form form = new Form();
		
		final NativeSelect select = new NativeSelect("Please select search type");
		select.setWidth("30%");
		select.addItem(TYPE_SEARCH_OPT);
		select.addItem(BR_SEARCH_OPT);
		select.addItem(OBO_BR_SEARCH_OPT);
		select.setNullSelectionAllowed(false);
		select.setValue(TYPE_SEARCH_OPT);
		
		final TextField field = new TextField("Search value");
		field.setColumns(20);
		field.setInputPrompt("Enter a search field");
		field.setRequired(true);
		field.setRequiredError("Required field");
		field.setValidationVisible(true);
		
		Button submit = new Button("Search");
		submit.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				log.info("Search " + select.getValue());
				log.info("Search " + select.getCaption());
				log.info("Search val " + field.getValue());
				
				if (!form.isValid()) return;
				
				Object searchType = select.getValue();
				String searchValue = (String)field.getValue();
				
				if (searchType == TYPE_SEARCH_OPT) {
					wireByActionType(Integer.parseInt(searchValue));
				} else if (searchType == BR_SEARCH_OPT) {
					wireByFromBranch(searchValue);
				} else if (searchType == OBO_BR_SEARCH_OPT) {
					wireByToBranch(searchValue);
				}
			}
		});
		
		form.addField("combo", select);
		form.addField("search", field);
		form.addField("submit", submit);
		
		form.setImmediate(true);
		
		setFirstComponent(form);
		setSplitPosition(100);
	}
	
	public void showSearchForm() {
		createCaptureForm();
	}
	
	public void wireByActionType(Integer actTyp) {
		createTable(dao.getSwitchingErrorsByType(actTyp));
	}
	
	public void wireByFromBranch(String brCde) {
		createTable(dao.getSwitchingErrorsFromBranch(brCde));
	}
	
	public void wireByToBranch(String brCde) {
		createTable(dao.getSwitchingErrorsToBranch(brCde));
	}
	
	private void createTable(List<SwitchingErrors> errors) {
		BeanItemContainer<SwitchingErrors> cont = 
			new BeanItemContainer<SwitchingErrors>(SwitchingErrors.class, errors);
		SwitchingErrorsTable table = new SwitchingErrorsTable(null, cont);
		
		table.addListener(this);
		setFirstComponent(table);
		setSplitPosition(100);
	}

	public void valueChange(ValueChangeEvent event) {
		//TODO Handle click event and show sw_audit entry, including xml
	}
}
