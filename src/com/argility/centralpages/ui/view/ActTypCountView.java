package com.argility.centralpages.ui.view;

import java.util.List;

import org.apache.log4j.Logger;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.dao.SwitchingErrorsDAO;
import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.ui.ActTypCountTable;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.VerticalSplitPanel;

@SuppressWarnings("serial")
public class ActTypCountView extends VerticalSplitPanel {

	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	protected CentralpagesApplication app = null;
	protected SwitchingErrorsDAO dao = null;
	
	private ActTypCountTable table;
	
	public ActTypCountView() {
		dao = (SwitchingErrorsDAO)CentralpagesApplication.getInstance()
			.getSpringContext().getBean("switchingErrorsDAO");
		
		setSizeFull();
	}

	public void wireCountByErrorData() {
		initTable(dao.getActionTypeTotalsWithError());
		
		wireTable();
	}
	
	public void wireCountByActionTypeData() {
		initTable(dao.getActionTypeTotals());
		
		table.setVisibleColumns(new Object[] {"actTyp","actionDesc","count"});
		table.setColumnHeaders(new String[] {"Action Type","Transaction Description","Total"});
		
		wireTable();
	}
	
	public void initTable(List<ActionTypeCountBean> list) {
		BeanItemContainer<ActionTypeCountBean> cont = 
			new BeanItemContainer<ActionTypeCountBean>(ActionTypeCountBean.class, list);
		table = new ActTypCountTable(null, cont);
	}
	
	private void wireTable() {
		setFirstComponent(table);
		table.setSizeFull();
		setSplitPosition(100);
	}
	
	class MyFieldFactory extends DefaultFieldFactory {
		
		ActTypCountView view = null;
		public MyFieldFactory(final ActTypCountView view) {
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