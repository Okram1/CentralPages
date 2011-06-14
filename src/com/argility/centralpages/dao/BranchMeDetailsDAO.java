package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.BranchMeDetails;

public interface BranchMeDetailsDAO {

	public List<BranchMeDetails> getOutstandingOnCentralList();
	
	public List<BranchMeDetails> getOnCentralAndOutstandingOnBatchList();
	
	public List<BranchMeDetails> getOnCentralNotRolledList();
}
