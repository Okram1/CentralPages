package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.SwitchingAgingCount;

public interface SwitchingAgingStatsDAO {

	public List<SwitchingAgingCount> getAllSwitchingAgingByBranch();
	
	public List<SwitchingAgingCount> getAllSwitchingAgingByBranchAndType();
	
	public List<SwitchingAgingCount> getAllSwitchingAgingByType();
	
	public List<SwitchingAgingCount> getSwitchingAgingByType(Integer actTyp);
	
	public List<SwitchingAgingCount> getSwitchingAgingByBranch(String brCde);
	
	public List<SwitchingAgingCount> getSwitchingAgingByBranchAndType(String brCde, Integer actTyp);
	
}
