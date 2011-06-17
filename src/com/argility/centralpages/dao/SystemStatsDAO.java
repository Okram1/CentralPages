package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.CentralSystemDetails;
import com.argility.centralpages.data.UucpStatus;

public interface SystemStatsDAO {

	public List<UucpStatus> getAllCentralUucpStatus();
	
	public List<UucpStatus> getAllSwitchingUucpStatus();
	
	public List<UucpStatus> getUucpStatusProblems();
	
	public List<UucpStatus> getUucpStatusForCentral(String central);
	
	public BranchInfo getBrInfo(String brCde);
	
	public List<CentralSystemDetails> getCentralSystemDetails();
}
