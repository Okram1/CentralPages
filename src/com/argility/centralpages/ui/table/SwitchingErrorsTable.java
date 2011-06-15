package com.argility.centralpages.ui.table;

import com.vaadin.data.Container;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SwitchingErrorsTable extends AbstractTable {

	public static final Object[] COL_NATURAL_ORDER = new Object[] {
		"brCde", "audId", "oboBrCde", "swAudId", "actTyp", "actDesc", "error"
	};
	
	public static final String[] COL_HEADINGS = new String[] {
		"Branch", "Audit", "OBO br", "Sw audit", "Action typ", "Action Description", "Error"
	};
	
	public SwitchingErrorsTable(String caption, Container dataSource) {

		setContainerDataSource(dataSource);
		setColumnCollapsingAllowed(true);
		
		setVisibleColumns(COL_NATURAL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		setSizeFull();
		
		addRowCountFooter();
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
