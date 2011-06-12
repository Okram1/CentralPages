package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.BranchProdDetails;

/**
 * 
 * Use this DAO to get production stats for branches
 * 
 */
public interface BranchProdDetailsDAO {

	/**
	 * This method returns production details for all branches
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getAllBranchProdDetails();

	/**
	 * This method returns production details for all branches that crashed importing 
	 * transactions into switching
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getSwImportFailedList();

	/**
	 * Production details for branches that still have many transactions outstanding
	 * to import into switching from central
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getSwImportBehindList();

	/**
	 * Branches that have been replicated after the last switching import and still
	 * need to import into switching 
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getReplicateAndNotImportedList();

	/**
	 * Branches that have not been imported into switching for a few days
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getSwNotImportedForDaysList();

	/**
	 * Branches where replication or other processing has crashed on central
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getCentralProcCrashedList();

	/**
	 * Branches where we received a new xout file on central but its still not been processed,
	 * default time check is 12 hrs
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getXoutReceivedNotProcessedList();

	/**
	 * Branches that have not been replicated for days
	 * <p>
	 * 
	 * @param 
	 * @return List<BranchProdDetails> object holding the branch details
	 */
	public List<BranchProdDetails> getNotReplicatedForDays();
}
