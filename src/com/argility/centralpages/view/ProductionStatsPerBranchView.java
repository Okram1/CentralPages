package com.argility.centralpages.view;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.data.ProductionStats;
import com.argility.centralpages.ui.ProductionStatsTable;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ProductionStatsPerBranchView extends ProductionStatsView {

	private TextField tf = new TextField("Branch: ");
	private Form form;

	public ProductionStatsPerBranchView(CentralpagesApplication app) {
		super(app);

		setFirstComponent(getBranchSearchForm());
		setSplitPosition(100);

		tf.addListener(this);
	}
	
	public ProductionStatsPerBranchView(CentralpagesApplication app, String brCde) {
		super(app);

		createTable(brCde);
	}

	private Form getBranchSearchForm() {

		form = new Form();
		form.addField("branch", tf);
		form.addField("submit", new Button("Search"));

		tf.addValidator(new RegexpValidator("[0-9][0-9][0-9][0-9]",
				"Must be 4 digits long!"));
		tf.setRequired(true);

		return form;
	}

	private void createTable(String brCde) {

		BeanItemContainer<ProductionStats> cont = new BeanItemContainer<ProductionStats>(
				ProductionStats.class,
				dao.getAllProductionStatsForBranch(brCde));

		table = new ProductionStatsTable(null, cont);
		table.setSortContainerPropertyId("dateTime");
		table.setSortAscending(false);

		setFirstComponent(table);
		setSplitPosition(100);

		setSizeFull();
		table.setSizeFull();

		table.addListener((Property.ValueChangeListener) this);
	}

	public void valueChange(ValueChangeEvent event) {
		Property property = event.getProperty();
		if (property == table) {
			super.valueChange(event);
		} else if (property == tf) {
			log.info("Property " + tf.getValue());
			if (form.isValid()) {
				createTable("" + tf.getValue());
			}
		}

	}
}
