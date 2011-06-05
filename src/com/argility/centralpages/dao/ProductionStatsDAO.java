package com.argility.centralpages.dao;

import java.util.Date;
import java.util.List;

import com.argility.centralpages.data.ProductionStats;

//TODO - add javadoc to this project
public interface ProductionStatsDAO {

	public List<ProductionStats> getAllProductionStats();
	
	public List<ProductionStats> getAllProductionStatsForBranch(String brCde);
	
	public List<ProductionStats> getAllProductionStatsForDate(Date date);
	
	public List<ProductionStats> getAllProductionStatsForAudit(Integer audId);
}
