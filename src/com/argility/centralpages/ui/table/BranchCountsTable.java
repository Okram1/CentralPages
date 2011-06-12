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
public class BranchCountsTable extends Table implements ClickListener{
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public static final Object[] COL_ORDER = new Object[] {"brCde","count"};
	public static final String[] COL_HEADINGS = new String[] {"Branch Code","Total"};
	
	private boolean toBranchSearch;
	private boolean showErrors;
	
	public BranchCountsTable(boolean showErrors, Container cont) {
		super(null, cont);
		
		this.showErrors = showErrors;
		
		setVisibleColumns(COL_ORDER);
		setColumnHeaders(COL_HEADINGS);
		
		addGeneratedColumn("brCde", new ColumnGenerator() {
			
			public Component generateCell(Table source, Object itemId, Object columnId) {
				Property prop =
		            source.getItem(itemId).getItemProperty(columnId);
				
				Button action = new Button(prop.getValue()+"", (ClickListener)source);
				action.setDescription("Click to show more details");
				action.setStyleName(Runo.BUTTON_LINK);
				return action;
			}
		});
		
		setColumnReorderingAllowed(true);

	}

	public void buttonClick(ClickEvent event) {
		log.info("Selected branch " + event.getButton().getCaption());
		String branch = event.getButton().getCaption();
		if (showErrors) {
			showSwitchErrors(branch);
		} else {
			showSwitchingTrans(branch);
		}
	}

	private void showSwitchingTrans(String branch) {
		SwitchingTranView view = new SwitchingTranView();
		if (toBranchSearch) {
			view.wireForBranchByActionTypeData(branch);
		} else {
			view.wireFromBranchByActionTypeData(branch);
		}
		CentralpagesApplication.getInstance().setMainView(view, true);
	}
	
	private void showSwitchErrors(String branch) {
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