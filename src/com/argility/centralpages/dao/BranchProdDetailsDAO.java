package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.BranchProdDetails;
//TODO - add javadoc
public interface BranchProdDetailsDAO {

	public List<BranchProdDetails> getAllBranchProdDetails();
	
	public List<BranchProdDetails> getSwImportFailedList();
	
	public List<BranchProdDetails> getSwImportBehindList();
	
	public List<BranchProdDetails> getReplicateAndNotImportedList();
	
	public List<BranchProdDetails> getSwNotImportedForDaysList();
	
	public List<BranchProdDetails> getCentralProcCrashedList();
	
	public List<BranchProdDetails> getXoutReceivedNotProcessedList();
	
	public List<BranchProdDetails> getNotReplicatedForDays();
}
