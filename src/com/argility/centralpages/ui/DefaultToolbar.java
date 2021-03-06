package com.argility.centralpages.ui;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.nav.CentralAccordion;
import com.argility.centralpages.ui.nav.MeConsAccordion;
import com.argility.centralpages.ui.nav.SwitchingNavAccordion;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DefaultToolbar extends HorizontalLayout implements Button.ClickListener{
	
	private Button switchingButton = new Button("Switching");
	private Button centralButton = new Button("Central");
	//private Button switchingErrorsButton = new Button("Switching Errors");
	//private Button switchingTransButton = new Button("Switching Transactions");
	
	private Button meConsButton = new Button("ME Cons");
	
	public DefaultToolbar() {
		
		switchingButton.setStyleName("toolbar-selected");
		switchingButton.setIcon(new ThemeResource("icons/switching.png"));
		switchingButton.addListener((Button.ClickListener)this);
		addComponent(switchingButton);
		
		centralButton.setIcon(new ThemeResource("icons/replication.png"));
		centralButton.addListener((Button.ClickListener)this);
		addComponent(centralButton);
		
		//switchingErrorsButton.setIcon(new ThemeResource("icons/sw_errors.png"));
		//switchingErrorsButton.addListener((Button.ClickListener)this);
		//addComponent(switchingErrorsButton);
		
		//switchingTransButton.setIcon(new ThemeResource("icons/switching2.png"));
		//switchingTransButton.addListener((Button.ClickListener)this);
		//addComponent(switchingTransButton);
		
		meConsButton.setIcon(new ThemeResource("icons/32/db_update.png"));
		meConsButton.addListener((Button.ClickListener)this);
		addComponent(meConsButton);
		
		setMargin(true);
		setSpacing(true);
		
		setWidth("100%");
		
		String image = "cooltext528009102.png";
		image = "cp_logo_metallic.png";
		//image = "ehl-centralpages.png";
		Embedded em = new Embedded(null, new ThemeResource("images/" + image));
		em.setHeight("63px");
		addComponent(em);
		setComponentAlignment(em, Alignment.TOP_RIGHT);
		setExpandRatio(em, 1);
		
		setStyleName("toolbar");
	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		switchingButton.setStyleName("toolbar-deselected");
		centralButton.setStyleName("toolbar-deselected");
		//switchingErrorsButton.setStyleName("toolbar-deselected");
		//switchingTransButton.setStyleName("toolbar-deselected");
		meConsButton.setStyleName("toolbar-deselected");
		
		source.setStyleName("toolbar-selected");
		
		if (source == switchingButton) {
			//app.setNavigationComponent(new SwitchingNavigationTree());
			app.setNavigationComponent(new SwitchingNavAccordion());
		} else if (source == centralButton) {
			//app.setNavigationComponent(new ReplicationNavigationTree());
			app.setNavigationComponent(new CentralAccordion());
			
		} else if (source == meConsButton) {
			app.setNavigationComponent(new MeConsAccordion());
		}
	}
}
