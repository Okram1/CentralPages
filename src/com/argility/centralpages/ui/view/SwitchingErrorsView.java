package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingErrorsDAO;
import com.argility.centralpages.data.SwitchingErrors;
import com.argility.centralpages.ui.SwitchingErrorsTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
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
	
	public static final String BR_SEARCH_OPT = "Search using branch code";
	public static final String TYPE_SEARCH_OPT = "Search using action type";
	public static final String OBO_BR_SEARCH_OPT = "Search using obo branch code";
	public static final String ERROR_SEARCH_OPT = "Search using error message";
	public static final String AUDIT_SEARCH_OPT = "Search using audit id";
	public static final String SW_AUDIT_SEARCH_OPT = "Search using switch audit id";
	
	protected SwitchingErrorsDAO dao;
	private Form form;
	private NativeSelect select;
	private IntegerValidator intVal = new IntegerValidator("Only integers allowed");

	public SwitchingErrorsView() {
		
		dao = (SwitchingErrorsDAO)CentralpagesApplication.getInstance()
					.getSpringContext().getBean("switchingErrorsDAO");
		
		setSizeFull();
	}
	
	public void createCaptureForm() {
		form = new Form();
		
		select = new NativeSelect("Please select search type");
		select.setWidth("30%");
		select.addItem(TYPE_SEARCH_OPT);
		select.addItem(BR_SEARCH_OPT);
		select.addItem(OBO_BR_SEARCH_OPT);
		select.addItem(AUDIT_SEARCH_OPT);
		select.addItem(SW_AUDIT_SEARCH_OPT);
		select.addItem(ERROR_SEARCH_OPT);
		select.setNullSelectionAllowed(false);
		select.setValue(TYPE_SEARCH_OPT);
		select.addListener(this);
		
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
				} else if (searchType == ERROR_SEARCH_OPT) {
					wireByErrorMessage(searchValue);
				} else if (searchType == AUDIT_SEARCH_OPT) {
					wireByAudit(Integer.parseInt(searchValue));
				} else if (searchType == SW_AUDIT_SEARCH_OPT) {
					wireBySwAudit(Integer.parseInt(searchValue));
				}
				
			}
		});
		
		form.addField("combo", select);
		form.addField("search", field);
		form.addField("submit", submit);
		
		form.setImmediate(true);
		submit.setClickShortcut(KeyCode.ENTER);
		
		select.focus();
		
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
	
	public void wireByErrorMessage(String error) {
		createTable(dao.getSwitchingErrorsByError(error));
	}
	
	public void wireByAudit(Integer audit) {
		createTable(dao.getSwitchingErrorsByAudit(audit));
	}
	
	public void wireBySwAudit(Integer swAudit) {
		createTable(dao.getSwitchingErrorsBySwAudit(swAudit));
	}
	
	private void createTable(List<SwitchingErrors> errors) {
		BeanItemContainer<SwitchingErrors> cont = 
			new BeanItemContainer<SwitchingErrors>(SwitchingErrors.class, errors);
		SwitchingErrorsTable table = new SwitchingErrorsTable(null, cont);
		
		table.setSortContainerPropertyId("swAudId");
		table.setSortAscending(false);
		table.addListener(this);
		setFirstComponent(table);
		setSplitPosition(100);
	}

	public void valueChange(ValueChangeEvent event) {
		Property prod = event.getProperty();
		
		if (prod == select) {
			if (AUDIT_SEARCH_OPT.equals(prod.getValue()) || SW_AUDIT_SEARCH_OPT.equals(prod.getValue())) {
				form.getField("search").addValidator(intVal);
			} else {
				form.getField("search").removeValidator(intVal);
			}
		}
	}
}
