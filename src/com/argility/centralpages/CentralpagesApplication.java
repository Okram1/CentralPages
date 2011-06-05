package com.argility.centralpages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.argility.centralpages.ui.DefaultToolbar;
import com.argility.centralpages.ui.nav.AbstractNavigationTree;
import com.argility.centralpages.ui.nav.SwitchingNavigationTree;
import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CentralpagesApplication extends Application implements
		HttpServletRequestListener {

	private static ThreadLocal<CentralpagesApplication> threadLocal = new ThreadLocal<CentralpagesApplication>();

	protected transient Logger log = Logger
			.getLogger(this.getClass().getName());

	private VerticalLayout verticalLayout = new VerticalLayout();
	private HorizontalSplitPanel horizontalSplit = new HorizontalSplitPanel();
	private Component topView = new Panel("Top view initialized");

	private transient SpringContextHelper springContext;

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

	public static CentralpagesApplication getInstance() {
		return threadLocal.get();
	}

	// Set the current application instance
	public static void setInstance(CentralpagesApplication application) {
		if (getInstance() == null) {
			threadLocal.set(application);
		}
	}

	private void initLayout() {
		Window mainWindow = new Window("CentralPages Application");
		setMainWindow(mainWindow);

		verticalLayout.setSizeFull();

		verticalLayout.addComponent(topView);
		verticalLayout.addComponent(horizontalSplit);

		verticalLayout.setExpandRatio(horizontalSplit, 1);
		horizontalSplit
				.setSplitPosition(200, HorizontalSplitPanel.UNITS_PIXELS);

		getMainWindow().setContent(verticalLayout);

	}

	private void initDefaultView() {
		setNavigationTree(new SwitchingNavigationTree());
		// setTopView(new Panel("Top View Panel"));
		setTopView(new DefaultToolbar());
	}

	public void setTopView(Component component) {
		verticalLayout.replaceComponent(topView, component);
		topView = component;
	}

	public void setNavigationTree(AbstractNavigationTree tree) {
		horizontalSplit.setFirstComponent(tree);
	}

	public void setMainView(Component component) {
		setMainView(component, false);
	}
	
	public void setMainView(Component component, boolean resetMenu) {
		Component c = horizontalSplit.getFirstComponent();
		if (c instanceof AbstractNavigationTree) {
			
			Tree tree = (Tree)c;
			log.info("mmm " + tree.getData());
			log.info("me " + tree.getItem(tree.getData()));
		
			Item item = tree.getItem(tree.getData());
			if (item != null) {
				tree.unselect(tree.getData());
			}
		}
		
		horizontalSplit.setSecondComponent(component);
	}

	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		CentralpagesApplication.setInstance(this);
	}

	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		threadLocal.remove();

	}

}
