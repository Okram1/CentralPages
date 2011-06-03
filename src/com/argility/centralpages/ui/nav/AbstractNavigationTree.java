package com.argility.centralpages.ui.nav;

import com.argility.centralpages.CentralpagesApplication;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Tree;

@SuppressWarnings("serial")
public abstract class AbstractNavigationTree extends Tree implements ItemClickListener{

	protected CentralpagesApplication app;
	
	public AbstractNavigationTree(CentralpagesApplication app) {
		this.app = app;
		
		addListener((ItemClickListener)this);
	}
}
