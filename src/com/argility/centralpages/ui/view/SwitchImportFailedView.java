package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchLoadFailedDAO;
import com.argility.centralpages.data.SwitchLoadFailed;
import com.argility.centralpages.ui.SwitchLoadFailedTable;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.IntegerValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Form;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class SwitchImportFailedView extends VerticalSplitPanel implements
	Property.ValueChangeListener{

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected SwitchLoadFailedDAO dao;
	protected SwitchLoadFailedTable table;
	
	public SwitchImportFailedView() {
		dao = (SwitchLoadFailedDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchLoadFailedDAO");
		
		setSizeFull();
		
		setFirstComponent(new SearchForm());
		setSplitPosition(100);
	}
	
	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == table) {
			Item item = table.getItem(table.getValue());
			
			if (item == null) {
				setSplitPosition(100);
			} else {
				TextArea ta = new TextArea(item.getItemProperty("stackTrace"));
				
				ta.setReadOnly(true);
				ta.setSizeFull();
				setSecondComponent(ta);
				setSplitPosition(40);
			}
		}
	}
	
	private void createTable(List<SwitchLoadFailed> list) {
		
		BeanItemContainer<SwitchLoadFailed> cont = new BeanItemContainer<SwitchLoadFailed>(SwitchLoadFailed.class, 
				list);
		
		table = new SwitchLoadFailedTable(null, cont);
		table.setSortContainerPropertyId("dateTime");
		table.setSortAscending(false);
		
		setFirstComponent(table);
		setSplitPosition(100);
		
		setSizeFull();
		table.setSizeFull();
		
		table.addListener((Property.ValueChangeListener) this);
	}
	
	class SearchForm extends Form implements
		Property.ValueChangeListener{
		
		public static final String BR_SEARCH_OPT = "Search using branch code";
		public static final String TYPE_SEARCH_OPT = "Search using action type";
		public static final String OBO_BR_SEARCH_OPT = "Search using obo branch code";
		public static final String AUDIT_SEARCH_OPT = "Search using audit id";
		
		private NativeSelect select;
		private TextField search;
		private IntegerValidator intVal = new IntegerValidator("Must be an integer value");
		
		SearchForm() {
			
			select = new NativeSelect("Select search option");
			
			select.setWidth("30%");
			select.addItem(TYPE_SEARCH_OPT);
			select.addItem(BR_SEARCH_OPT);
			select.addItem(OBO_BR_SEARCH_OPT);
			select.addItem(AUDIT_SEARCH_OPT);
			
			select.setNullSelectionAllowed(false);
			select.setValue(TYPE_SEARCH_OPT);
			
			select.addListener((Property.ValueChangeListener)this);

			search = new TextField("Search value ");
			search.setColumns(20);
			search.setInputPrompt("Enter a search field");
			search.setRequired(true);
			search.setRequiredError("Required field");
			search.setValidationVisible(true);

			Button submit = new Button("Search");
			submit.addListener(new ClickListener() {
				
				public void buttonClick(ClickEvent event) {
					log.info("Search val " + search.getValue());
					
					if (!isValid()) return;
					
					Object searchType = select.getValue();
					String searchValue = (String)search.getValue();
					
					if (searchType == TYPE_SEARCH_OPT) {
						createTable(dao.getSwitchLoadFailedByActionType(Integer.parseInt(searchValue)));
					} else if (searchType == BR_SEARCH_OPT) {
						createTable(dao.getSwitchLoadFailedFromBranch(searchValue));
					} else if (searchType == OBO_BR_SEARCH_OPT) {
						createTable(dao.getSwitchLoadFailedToBranch(searchValue));
						CentralpagesApplication.getInstance()
								.getMainWindow().showNotification("Please keep in mind that not all " +
								"crashed transactions log the obo branch.", 
								Notification.TYPE_TRAY_NOTIFICATION);
					} else if (searchType == AUDIT_SEARCH_OPT) {
						createTable(dao.getSwitchLoadFailedByAudit(Integer.parseInt(searchValue)));
					} 
				}
			});
			
			addField("combo", select);
			addField("search", search);
			addField("submit", submit);
			
			submit.setIcon(new ThemeResource("icons/Search.png"));
			
			setImmediate(true);
			submit.setClickShortcut(KeyCode.ENTER);
			
			select.focus();
			
		}
		
		public void valueChange(Property.ValueChangeEvent event) {
			Property prop = event.getProperty();
			
			log.info("AUDIT_SEARCH_OPT " + AUDIT_SEARCH_OPT);
			
			if (prop.getValue() == AUDIT_SEARCH_OPT) {
				log.info("clicked " + AUDIT_SEARCH_OPT);
				search.addValidator(intVal);
			} else {
				search.removeValidator(intVal);
			}
			
		}

	}
}
