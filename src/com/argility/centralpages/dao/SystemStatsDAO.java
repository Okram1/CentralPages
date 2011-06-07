package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.UucpStatus;

public interface SystemStatsDAO {

	public List<UucpStatus> getAllUucpStatus();
	
	public List<UucpStatus> getUucpStatusProblems();
}
