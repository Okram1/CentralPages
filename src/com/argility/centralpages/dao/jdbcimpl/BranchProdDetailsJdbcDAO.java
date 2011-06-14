package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.BranchProdDetailsDAO;
import com.argility.centralpages.dao.mapper.BranchProdDetailsRowMapper;
import com.argility.centralpages.data.BranchProdDetails;

public class BranchProdDetailsJdbcDAO extends AbstractDAO implements BranchProdDetailsDAO{

	public List<BranchProdDetails> getAllBranchProdDetails() {
		log.info("getAllBranchProdDetails()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL;
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getSwImportFailedList() {
		log.info("getSwImportFailedList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + " WHERE sw_crashed";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getCentralProcCrashedList() {
		log.info("getCentralProcCrashedList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE repl_process ilike '%crashed%' ";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getSwImportBehindList() {
		log.info("getSwManyToImportList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + " WHERE sw_diff > 1000";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getReplicateAndNotImportedList() {
		log.info("getReplicateAndNotImportedList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE (br_repl_lock_date-last_sw_load) > '24:00:00' ORDER by last_sw_load";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getSwNotImportedForDaysList() {
		log.info("getSwNotImportedForDaysList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE last_sw_load < now() - '3 days'::interval ORDER by last_sw_load";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getXoutReceivedNotProcessedList() {
		log.info("getXoutReceivedNotProcessedList()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE (xout_received-br_repl_lock_date) > '12:00:00' ORDER by br_repl_lock_date";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public List<BranchProdDetails> getNotReplicatedForDays() {
		log.info("getNotReplicatedForDays()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL +
		" WHERE br_repl_lock_date < now() - '5 days'::interval ORDER by br_repl_lock_date";
		
		return getJdbcTemplate().query(sql, new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

	public BranchProdDetails getProdDetailsForBranch(String brCde) {
		log.info("getProdDetailsForBranch()");
		String sql = BranchProdDetailsRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ?";
		
		return getJdbcTemplate().queryForObject(sql,
				new Object[] {brCde},
				new BranchProdDetailsRowMapper<BranchProdDetails>());
	}

}
