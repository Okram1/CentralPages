package com.argility.centralpages.ui;

import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SwitchingErrorsTable extends Table {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "audId", "oboBrCde", "swAudId", "actTyp", "actDesc", "error"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Audit", "OBO br", "Sw audit", "Action typ", "Action Description", "Error"
	};
	
	public SwitchingErrorsTable() {
		new SwitchingErrorsTable(null);
	}

	public SwitchingErrorsTable(String caption) {
		new SwitchingErrorsTable(caption, null);
	}

	public SwitchingErrorsTable(String caption, Container dataSource) {
		super(caption, dataSource);
		
		setColumnCollapsingAllowed(true);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setSizeFull();
		
		addBrTotalCountFooter();
	}

	public void addBrTotalCountFooter() {
		setFooterVisible(true);
		setColumnFooter("brCde", getContainerDataSource().size() + " Rows");
	}
	
	public HorizontalLayout getFilters() {
		setFooterVisible(true);
		
		HorizontalLayout hl = new HorizontalLayout();
		
		final TextField brCdeText = new TextField("Branch");
		brCdeText.addListener(new TextChangeListener() {
			
			private SimpleStringFilter brCdeFilter;
			
			public void textChange(TextChangeEvent event) {
				Filterable f = (Filterable)getContainerDataSource();
				
				if (brCdeFilter != null) {
					f.removeContainerFilter(brCdeFilter);
				}
				
				brCdeFilter = new SimpleStringFilter("brCde", event.getText(), true, false);
				
				f.addContainerFilter(brCdeFilter);
			}
		});
		
		final TextField errorText = new TextField("Error");
		errorText.addListener(new TextChangeListener() {
			
			private SimpleStringFilter errorFilter;
			
			public void textChange(TextChangeEvent event) {
				Filterable f = (Filterable)getContainerDataSource();
				
				if (errorFilter != null) {
					f.removeContainerFilter(errorFilter);
				}
				
				errorFilter = new SimpleStringFilter("error", event.getText(), true, false);
				
				f.addContainerFilter(errorFilter);
			}
		});
		
		hl.addComponent(brCdeText);
		hl.addComponent(errorText);
		
		return hl;
	}
}
