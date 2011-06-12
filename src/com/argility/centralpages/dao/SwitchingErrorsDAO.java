package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwAudit;
import com.argility.centralpages.data.SwitchingErrors;


public interface SwitchingErrorsDAO {

	/**
	 * Returns total failing switching grouped by action type
	 * <p>
	 * 
	 * @param 
	 * @return List<ActionTypeCountBean>
	 */
	public List<ActionTypeCountBean> getActionTypeTotals() ;  
	
	/**
	 * Returns all failing transactions of a specified action type
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsByType(Integer actTyp);
	
	/**
	 * Return all transaction failing from a single branch
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsFromBranch(String brCde);
	
	/**
	 * Return all transaction failing going to a single branch
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsToBranch(String brCde);
	
	/**
	 * Returns total failing switching grouped by error type and action type
	 * <p>
	 * 
	 * @param 
	 * @return List<ActionTypeCountBean>
	 */
	public List<ActionTypeCountBean> getActionTypeTotalsWithError();

	/**
	 * Return all failing transactions with a certain error type
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsByError(String error);
	
	/**
	 * Return all transaction failing by audit
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsByAudit(Integer audit);
	
	/**
	 * Return all transaction failing transaction by a sw_aud_id
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<SwitchingErrors> getSwitchingErrorsBySwAudit(Integer swAudit);
	
	/**
	 * Return totals from a sending branch
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<BranchCountsBean> getTotalsBySendingBranch();
	
	/**
	 * Return totals going to a branch
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public List<BranchCountsBean> getTotalsByReceivingBranch();
	
	/**
	 * Get details about a sw_aud_id
	 * <p>
	 * 
	 * @param 
	 * @return List<SwitchingErrors>
	 */
	public SwAudit getSwAudit(Integer swAudId);
	
}
