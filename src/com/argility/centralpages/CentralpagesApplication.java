package com.argility.centralpages;

import org.apache.log4j.Logger;

import com.argility.centralpages.ui.DefaultToolbar;
import com.argility.centralpages.ui.nav.AbstractNavigationTree;
import com.argility.centralpages.ui.nav.SwitchingNavigationTree;
import com.vaadin.Application;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CentralpagesApplication extends Application {
	
	protected transient Logger log = Logger
		.getLogger(this.getClass().getName());
	
	private VerticalLayout verticalLayout = new VerticalLayout();
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel(); 
	private Component topView = new Panel("Top view initialized");
	
	private SpringContextHelper springContext;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 276098922348675616L;

	@Override
	public void init() {
		
		setTheme("centralpages");
		
		initLayout();
		initDefaultView();
	}
	
	public SpringContextHelper getSpringContext() {
		if (springContext == null) {
			springContext = new SpringContextHelper(this);
		}
		
		return springContext;
	}
	
	private void initLayout() {
		Window mainWindow = new Window("CentralPages Application");
		setMainWindow(mainWindow);
		
		verticalLayout.setSizeFull();
		
		verticalLayout.addComponent(topView);
		verticalLayout.addComponent(horizontalSplit);
		
		verticalLayout.setExpandRatio(horizontalSplit, 1);
		horizontalSplit.setSplitPosition(200, 
				HorizontalSplitPanel.UNITS_PIXELS);
		
		getMainWindow().setContent(verticalLayout);
		
	}
	
	private void initDefaultView() {
		setNavigationTree(new SwitchingNavigationTree(this));
		//setTopView(new Panel("Top View Panel"));
		setTopView(new DefaultToolbar(this));
	}
	
	public void setTopView(Component component) {
		verticalLayout.replaceComponent(topView, component);
		topView = component;
	}
	
	public void setNavigationTree(AbstractNavigationTree tree) {
		horizontalSplit.setFirstComponent(tree);
	}
	
	public void setMainView(Component component) {
		horizontalSplit.setSecondComponent(component);
	}
	
}
