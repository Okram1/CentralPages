package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.SwitchLoadFailed;

public interface SwitchLoadFailedDAO {

	public List<SwitchLoadFailed> getAllSwitchLoadFailed();
	
	public List<SwitchLoadFailed> getSwitchLoadFailedFromBranch(String brCde);
	
	public List<SwitchLoadFailed> getSwitchLoadFailedToBranch(String brCde);
	
	public List<SwitchLoadFailed> getSwitchLoadFailedByAudit(Integer audId);
	
	public List<SwitchLoadFailed> getSwitchLoadFailedByActionType(Integer audId);
}
