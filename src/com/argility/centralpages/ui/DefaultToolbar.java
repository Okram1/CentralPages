package com.argility.centralpages.ui;

import com.argility.centralpages.CentralpagesApplication;
import com.argility.centralpages.ui.nav.ReplicationNavigationTree;
import com.argility.centralpages.ui.nav.SwitchingErrorNavigationTree;
import com.argility.centralpages.ui.nav.SwitchingNavigationTree;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;

@SuppressWarnings("serial")
public class DefaultToolbar extends HorizontalLayout implements Button.ClickListener{
	
	private Button switchingButton = new Button("Switching");
	private Button replicationButton = new Button("Replication");
	private Button switchingErrorsButton = new Button("Switching Errors");
	
	public DefaultToolbar() {
		
		switchingButton.setIcon(new ThemeResource("icons/32/reload.png"));
		switchingButton.addListener((Button.ClickListener)this);
		addComponent(switchingButton);
		
		replicationButton.setIcon(new ThemeResource("icons/32/note.png"));
		replicationButton.addListener((Button.ClickListener)this);
		addComponent(replicationButton);
		
		switchingErrorsButton.setIcon(new ThemeResource("icons/32/globe.png"));
		switchingErrorsButton.addListener((Button.ClickListener)this);
		addComponent(switchingErrorsButton);
		
		setMargin(true);
		setSpacing(true);
		
		setWidth("100%");
		
		Embedded em = new Embedded("", new ThemeResource("images/cp_logo_metallic.png"));
		em.setHeight("55px");
		addComponent(em);
		setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
		setExpandRatio(em, 1);
		
		setStyleName("toolbar");
	}

	public void buttonClick(ClickEvent event) {
		final Button source = event.getButton();
		
		CentralpagesApplication app = CentralpagesApplication.getInstance();
		
		if (source == switchingButton) {
			app.setNavigationTree(new SwitchingNavigationTree());
		} else if (source == replicationButton) {
			app.setNavigationTree(new ReplicationNavigationTree());
		} else if (source == switchingErrorsButton) {
//			app.setMainView(new Panel("Work in progress..."));
			app.setNavigationTree(new SwitchingErrorNavigationTree());
		}
	}
}
