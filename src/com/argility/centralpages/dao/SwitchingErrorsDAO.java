package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.SwitchingErrors;


public interface SwitchingErrorsDAO {

	public List<ActionTypeCountBean> getActionTypeTotals() ;  
	
	public List<SwitchingErrors> getSwitchingErrorsByType(Integer actTyp);
	
	public List<SwitchingErrors> getSwitchingErrorsFromBranch(String brCde);
	
	public List<SwitchingErrors> getSwitchingErrorsToBranch(String brCde);
	
	public List<ActionTypeCountBean> getActionTypeTotalsWithError();
	
}