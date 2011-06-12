package com.argility.centralpages.ui.form;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.view.SwitchingTranView;
import com.vaadin.data.Property;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SwitchingTransSearchForm extends Form implements Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public static final String BR_SEARCH_OPT = "Search using branch code";
	public static final String TYPE_SEARCH_OPT = "Search using action type";
	public static final String OBO_BR_SEARCH_OPT = "Search using obo branch code";
	public static final String ERROR_SEARCH_OPT = "Search using error message";
	public static final String AUDIT_SEARCH_OPT = "Search using audit id";
	public static final String SW_AUDIT_SEARCH_OPT = "Search using switch audit id";
	
	private NativeSelect select = new NativeSelect("Please select a search type");
	private SwitchingTranView view;
	
	public SwitchingTransSearchForm() {
		select.setWidth("30%");
		select.addItem(TYPE_SEARCH_OPT);
		select.addItem(BR_SEARCH_OPT);
		select.addItem(OBO_BR_SEARCH_OPT);
		select.addItem(AUDIT_SEARCH_OPT);
		//select.addItem(SW_AUDIT_SEARCH_OPT);
		select.setNullSelectionAllowed(false);
		select.setValue(TYPE_SEARCH_OPT);
		//select.addListener((ValueChangeListener)this);
		
		final TextField field = new TextField("Search value");
		field.setColumns(20);
		field.setInputPrompt("Enter a search field");
		field.setRequired(true);
		field.setRequiredError("Required field");
		field.setValidationVisible(true);
		
		Button submit = new Button("Search");
		submit.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				log.info("Search val " + field.getValue());
				
				if (!isValid()) return;
				
				Object searchType = select.getValue();
				String searchValue = (String)field.getValue();
				
				if (searchType == TYPE_SEARCH_OPT) {
					wireByActionType(Integer.parseInt(searchValue));
				} else if (searchType == BR_SEARCH_OPT) {
					wireByFromBranch(searchValue);
				} else if (searchType == OBO_BR_SEARCH_OPT) {
					wireByToBranch(searchValue);
				} else if (searchType == AUDIT_SEARCH_OPT) {
					wireByAudit(Integer.parseInt(searchValue));
				} else if (searchType == SW_AUDIT_SEARCH_OPT) {
					wireBySwAudit(Integer.parseInt(searchValue));
				}
			}
		});
		
		addField("combo", select);
		addField("search", field);
		addField("submit", submit);
		
		submit.setIcon(new ThemeResource("icons/Search.png"));
		
		setImmediate(true);
		submit.setClickShortcut(KeyCode.ENTER);
		
		select.focus();

	}
	
	private void wireBySwAudit(int parseInt) {
		//getView().wi
		//TODO add this function
	}

	private void wireByAudit(int parseInt) {
		getView().wireSwitchingByAuditData(parseInt);
		CentralpagesApplication.getInstance().setMainView(getView());
	}

	private void wireByToBranch(String searchValue) {
		getView().wireSwitchingForBranchData(searchValue);
		CentralpagesApplication.getInstance().setMainView(getView());
	}

	private void wireByFromBranch(String searchValue) {
		getView().wireSwitchingFromBranchData(searchValue);
		CentralpagesApplication.getInstance().setMainView(getView());
	}

	private void wireByActionType(int parseInt) {
		getView().wireSwitchingByActionTypeData(parseInt);
		CentralpagesApplication.getInstance().setMainView(getView());
	}

	public SwitchingTranView getView() {
		if (view == null) {
			view = new SwitchingTranView();
		}
		return view;
	}
		
}
