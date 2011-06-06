package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwitchingErrors;


public interface SwitchingErrorsDAO {

	public List<ActionTypeCountBean> getActionTypeTotals() ;  
	
	public List<SwitchingErrors> getSwitchingErrorsByType(Integer actTyp);
	
	public List<SwitchingErrors> getSwitchingErrorsFromBranch(String brCde);
	
	public List<SwitchingErrors> getSwitchingErrorsToBranch(String brCde);
	
	public List<ActionTypeCountBean> getActionTypeTotalsWithError();

	public List<SwitchingErrors> getSwitchingErrorsByError(String error);
	
	public List<SwitchingErrors> getSwitchingErrorsByAudit(Integer audit);
	
	public List<SwitchingErrors> getSwitchingErrorsBySwAudit(Integer swAudit);
	
	public List<BranchCountsBean> getTotalsBySendingBranch();
	
	public List<BranchCountsBean> getTotalsByReceivingBranch();
	
}
