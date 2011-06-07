package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwitchingTran;

public interface SwitchingTransDAO {

	public List<BranchCountsBean> getTotalsBySendingBranch();
	
	public List<BranchCountsBean> getTotalsByOboBranch();
	
	public List<ActionTypeCountBean> getTotalsByActionType();
	
	public List<ActionTypeCountBean> getAllByActionTypeForBranch(String brCde);
	
	public List<ActionTypeCountBean> getAllByActionTypeFromBranch(String brCde);
	
	public List<SwitchingTran> getSwitchTransToBranch(String brCde);
	
	public List<SwitchingTran> getSwitchTransFromBranch(String brCde);
	
	public List<SwitchingTran> getSwitchTransByActionType(Integer actTyp);
	
	public List<SwitchingTran> getSwitchTransByAuditId(Integer audId);
	
	public List<SwitchingTran> getSwitchTransBySwAuditId(Integer swAudId);
}
