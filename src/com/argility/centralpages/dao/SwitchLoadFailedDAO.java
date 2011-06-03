package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.SwitchLoadFailed;

public interface SwitchLoadFailedDAO {

	public List<SwitchLoadFailed> getAllSwitchLoadFailed();
	
	public List<SwitchLoadFailed> getSwitchLoadFailedFromBranch(String brCde);
	
	public List<SwitchLoadFailed> getSwitchLoadFailedBranchAudit(String brCde, Integer audId);
}
