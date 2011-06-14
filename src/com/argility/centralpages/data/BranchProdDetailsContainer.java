package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Collection;

import com.vaadin.data.util.BeanItemContainer;

@SuppressWarnings("serial")
public class BranchProdDetailsContainer extends BeanItemContainer<BranchProdDetails> implements
		Serializable {

	public static final Object[] NATURAL_COL_ORDER = new Object[] {
		"brCde","central","replLocked","brReplLockDate","xoutReceived", "xoutQueued", "replProcess", 
		"replAuditUpTo", "replUpTo", "replDiff", "lastSwLoad", "swAudUpTo", "swDiff", "swCrashed", "swCrashAudId"
	};
	
	public static final String[] COL_HEADERS_ENGLISH = new String[] {
		"Branch","Central","Rep Locked","Last Replicated","Xout Received","Xout Queued", "Repl Process", 
		"Repl Audit UpTo", "Repl UpTo", "Repl Diff", "Last Sw Load", "Sw Aud UpTo", "Sw Diff", "Sw Crashed", "Error AudId"
	};
	
	public BranchProdDetailsContainer(Class<? super BranchProdDetails> type,
			Collection<? extends BranchProdDetails> collection)
			throws IllegalArgumentException {
		super(type, collection);
		
	}

	

}
