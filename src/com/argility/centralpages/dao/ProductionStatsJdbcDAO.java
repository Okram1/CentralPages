package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.dao.mapper.ProductionStatsRowMapper;
import com.argility.centralpages.data.ProductionStats;
import com.argility.centralpages.data.StatsProd;

public class ProductionStatsJdbcDAO extends AbstractDAO implements
		ProductionStatsDAO {

	public List<ProductionStats> getAllProductionStats() {
		log.info("getAllProductionStats()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL;
		
		return getJdbcTemplate().query(sql, new ProductionStatsRowMapper<StatsProd>());
	}

	public List<ProductionStats> getAllProductionStatsForBranch(String brCde) {
		log.info("getAllProductionStatsForBranch()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[] {brCde},
				new ProductionStatsRowMapper<StatsProd>());
	}

}
