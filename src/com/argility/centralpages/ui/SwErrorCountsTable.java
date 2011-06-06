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
public class SwErrorCountsTable extends Table implements ClickListener{
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	private boolean toBranchSearch;
	
	public SwErrorCountsTable(Container cont) {
		super(null, cont);
		
		setVisibleColumns(new Object[] {"brCde","count"});
		setColumnHeaders(new String[] {"Branch Code","Total"});
		
		addGeneratedColumn("brCde", new ColumnGenerator() {
			
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
		log.info("Selected branch " + event.getButton().getCaption());
		String branch = event.getButton().getCaption();
		SwitchingErrorsView view = new SwitchingErrorsView();
		if (toBranchSearch) {
			view.wireByToBranch(branch);
		} else {
			view.wireByFromBranch(branch);
		}
		CentralpagesApplication.getInstance().setMainView(view, true);
	}

	public boolean isToBranchSearch() {
		return toBranchSearch;
	}

	public void setToBranchSearch(boolean toBranchSearch) {
		this.toBranchSearch = toBranchSearch;
	}

}
