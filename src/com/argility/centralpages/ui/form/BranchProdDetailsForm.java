package com.argility.centralpages.ui.form;

import org.apache.log4j.Logger;

import com.argility.centralpages.data.BranchProdDetails;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class BranchProdDetailsForm extends Form {

	public static final Object[] VISIBLE_PROPERTIES = {
		"brCde", "central", "brBatch", "fppCde", "meRolledDate",
		"brReplLockDate", "xoutReceived", "replProcess", "replLocked", "xoutQueued", 
		"replAuditUpTo", "replUpTo", "replDiff", 
		"brReplUnlockDate", "triadLive", "triadLocked", "meReplicateStartedDate", 
		"lastSwLoad", "swAudUpTo", "swDiff", "swCrashed", "swCrashAudId"
	};
	
	protected transient Logger log = Logger.getLogger(this.getClass().getName());
	
	public BranchProdDetailsForm() {
		new BranchProdDetailsForm(new BranchProdDetails());
	}
	
	public BranchProdDetailsForm(BranchProdDetails data) {
		
		setItemDataSource(new BeanItem<BranchProdDetails>(data));
		
		setVisibleItemProperties(VISIBLE_PROPERTIES);
		
		setReadOnly(true);
	}
	
	@Override
	protected void attachField(Object propertyId, Field field) {
		if (field instanceof TextField) {
			((TextField) field).setColumns(20);
		}
		if (field instanceof DateField) {
			((DateField) field).setResolution(DateField.RESOLUTION_MIN);
		}
		super.attachField(propertyId, field);
		
		/*if (propertyId.equals("brCde")) {
            ourLayout.addComponent(field, 0, 0);
        } else if (propertyId.equals("central")) {
            ourLayout.addComponent(field, 1, 0);
        }else if (propertyId.equals("lastReplicated")) {
            ourLayout.addComponent(field, 0, 1);
        } else if (propertyId.equals("xoutReceived")) {
            ourLayout.addComponent(field, 1, 1);
        } else if (propertyId.equals("replProcess")) {
            ourLayout.addComponent(field, 0, 2);
        } else if (propertyId.equals("triadProcess")) {
            ourLayout.addComponent(field, 1, 2);
        } else if (propertyId.equals("replAuditUpTo")) {
            ourLayout.addComponent(field, 0, 3);
        } else if (propertyId.equals("replUpTo")) {
            ourLayout.addComponent(field, 1, 3);
        } else if (propertyId.equals("replDiff")) {
            ourLayout.addComponent(field, 0, 4);
        } else if (propertyId.equals("lastSwLoad")) {
            ourLayout.addComponent(field, 1, 4);
        } else if (propertyId.equals("swAudUpTo")) {
            ourLayout.addComponent(field, 0, 5);
        } else if (propertyId.equals("swDiff")) {
            ourLayout.addComponent(field, 1, 5);
        } else if (propertyId.equals("swCrashed")) {
            ourLayout.addComponent(field, 0, 6);
        } else if (propertyId.equals("swCrashAudId")) {
            ourLayout.addComponent(field, 1, 6);
        }  */
        
    }

}
