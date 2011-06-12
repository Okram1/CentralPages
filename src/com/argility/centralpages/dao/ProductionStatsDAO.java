package com.argility.centralpages.dao;

import java.util.Date;
import java.util.List;

import com.argility.centralpages.data.ProductionStats;

//TODO - add javadoc to this project
public interface ProductionStatsDAO {

	/**
	 * Returns all logged production stats we have
	 * <p>
	 * 
	 * @param 
	 * @return List<ProductionStats> object holding the log entries
	 */
	public List<ProductionStats> getAllProductionStats();
	
	/**
	 * Returns all logged production stats for a single branch
	 * <p>
	 * 
	 * @param String brCde
	 * @return List<ProductionStats> object holding the log entries
	 */
	public List<ProductionStats> getAllProductionStatsForBranch(String brCde);
	
	/**
	 * Returns all logged production stats for a single date
	 * <p>
	 * 
	 * @param 
	 * @return List<ProductionStats> object holding the log entries
	 */
	public List<ProductionStats> getAllProductionStatsForDate(Date date);
	
	/**
	 * Returns all logged production stats for a single audit
	 * <p>
	 * 
	 * @param 
	 * @return List<ProductionStats> object holding the log entries
	 */
	public List<ProductionStats> getAllProductionStatsForAudit(Integer audId);
}
