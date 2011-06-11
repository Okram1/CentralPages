package com.argility.centralpages.dao.jdbcimpl;

import java.util.Date;
import java.util.List;

import com.argility.centralpages.dao.ProductionStatsDAO;
import com.argility.centralpages.dao.mapper.ProductionStatsRowMapper;
import com.argility.centralpages.data.ProductionStats;
import com.argility.centralpages.data.BranchProdDetails;

public class ProductionStatsJdbcDAO extends AbstractDAO implements
		ProductionStatsDAO {

	public List<ProductionStats> getAllProductionStats() {
		log.info("getAllProductionStats()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL;
		
		return getJdbcTemplate().query(sql, new ProductionStatsRowMapper<BranchProdDetails>());
	}

	public List<ProductionStats> getAllProductionStatsForBranch(String brCde) {
		log.info("getAllProductionStatsForBranch()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[] {brCde},
				new ProductionStatsRowMapper<BranchProdDetails>());
	}

	public List<ProductionStats> getAllProductionStatsForDate(Date date) {
		log.info("getAllProductionStatsForDate()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL + " WHERE date(date_time) = date(?)";
		
		return getJdbcTemplate().query(sql,
				new Object[] {date},
				new ProductionStatsRowMapper<BranchProdDetails>());
	}

	public List<ProductionStats> getAllProductionStatsForAudit(Integer audId) {
		log.info("getAllProductionStatsForAudit()");
		String sql = ProductionStatsRowMapper.SELECT_COLUMNS_SQL + " WHERE aud_id = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[] {audId},
				new ProductionStatsRowMapper<BranchProdDetails>());
	}

}
