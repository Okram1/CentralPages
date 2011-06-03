package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.data.ProductionStats;

public interface ProductionStatsDAO {

	public List<ProductionStats> getAllProductionStats();
	
	public List<ProductionStats> getAllProductionStatsForBranch(String brCde);
}
