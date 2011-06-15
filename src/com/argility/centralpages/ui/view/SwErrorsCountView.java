package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingErrorsDAO;
import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.ui.AbstractVerticalSplitPanel;
import com.argility.centralpages.ui.table.ActTypCountTable;
import com.argility.centralpages.ui.table.BranchCountsTable;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window.Notification;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;

@SuppressWarnings("serial")
public class SwErrorsCountView extends AbstractVerticalSplitPanel {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected SwitchingErrorsDAO dao = null;
	
	private ActTypCountTable table;
	private BranchCountsTable countTbl;
	
	public SwErrorsCountView() {
		dao = (SwitchingErrorsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingErrorsDAO");
		
		setSizeFull();
	}

	/**
	 * Show table of all failing transactions grouped by error message and type
	 */
	public void wireCountByErrorData() {
		initTable(dao.getActionTypeTotalsWithError());
		
		CentralpagesApplication.getInstance().getMainWindow().showNotification(
				"This is an hourly snapshot view and not realtime.", 
				Notification.TYPE_TRAY_NOTIFICATION);
		
		createSelectSearchableTable(table, 
				new String[] {"actTyp","error"}, 
				new String[] {"Action Type","Error"});
	}
	
	/**
	 * Show table of all failing transactions grouped by action type
	 */
	public void wireCountByActionTypeData() {
		initTable(dao.getActionTypeTotals());
		
		table.setVisibleColumns(new Object[] {"actTyp","actionDesc","count"});
		table.setColumnHeaders(new String[] {"Action Type","Transaction Description","Total"});
		
		table.addTotalSumFooter("count");
		
		CentralpagesApplication.getInstance().getMainWindow().showNotification(
				"This is an hourly snapshot view and not realtime.", 
				Notification.TYPE_TRAY_NOTIFICATION);
		
		createSingleColumnSearchableTable(table, "actTyp", "Enter action type and hit enter to search.");
		
	}
	
	/**
	 * Show table of all failing transactions grouped obo branch
	 */
	public void wireToBranchCounts() {
		initSwErrCntTable(dao.getTotalsByReceivingBranch());
		
		countTbl.setToBranchSearch(true);
		countTbl.setColumnHeaders(new String[] {"Branch Code","Total"});
		
		countTbl.addTotalSumFooter("count");
		
		createSingleColumnSearchableTable(countTbl, "brCde", "Enter branch code and hit enter to search.");
	}
	
	/**
	 * Show table of all failing transactions grouped by sending branch
	 */
	public void wireFromBranchCounts() {
		initSwErrCntTable(dao.getTotalsBySendingBranch());
		countTbl.setToBranchSearch(false);
		countTbl.addTotalSumFooter("count");
		
		createSingleColumnSearchableTable(countTbl, "brCde", "Enter branch code and hit enter to search.");
	}
	
	/**
	 * Create an ActTypCountTable using the provided list as the data to display
	 * @param list
	 */
	private void initTable(List<ActionTypeCountBean> list) {
		BeanItemContainer<ActionTypeCountBean> cont = 
			new BeanItemContainer<ActionTypeCountBean>(ActionTypeCountBean.class, list);
		table = new ActTypCountTable(true, cont);
	}
	
	/**
	 * Create the BranchCountsTable using the provided list as the data
	 * @param list
	 */
	public void initSwErrCntTable(List<BranchCountsBean> list) {
		BeanItemContainer<BranchCountsBean> cont = 
			new BeanItemContainer<BranchCountsBean>(BranchCountsBean.class, list);
		countTbl = new BranchCountsTable(true , cont);
		countTbl.setSizeFull();
	}
	
	/**
	 * This field factory allows us to make the action type a clickable link 
	 * @author marko.salic
	 *
	 */
	class MyFieldFactory extends DefaultFieldFactory {
		
		SwErrorsCountView view = null;
		public MyFieldFactory(final SwErrorsCountView view) {
			this.view = view;
		}
		
		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {
			log.info("propertyId " + propertyId);
			log.info("");
			if (propertyId.equals("actTyp")) {
				Button b = new Button(item.getItemProperty(propertyId).getValue()+"", (ClickListener) view);
				return b;
			}
			
			Field f = super.createField(item, propertyId, uiContext);
			return f;
		}
	}
}
