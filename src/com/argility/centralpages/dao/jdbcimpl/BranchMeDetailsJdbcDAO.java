package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.BranchMeDetailsDAO;
import com.argility.centralpages.dao.mapper.BranchMeDetailsRowMapper;
import com.argility.centralpages.data.BranchMeDetails;

public class BranchMeDetailsJdbcDAO extends AbstractDAO implements BranchMeDetailsDAO {

	public List<BranchMeDetails> getOutstandingOnCentralList() {
		log.info("getOutstandingOnCentralList()");
		String sql = BranchMeDetailsRowMapper.SELECT_COL + " WHERE got_me_on_central is false " +
				"OR got_me_on_central is null";
		
		return getJdbcTemplate().query(sql, new BranchMeDetailsRowMapper<BranchMeDetails>());
	}

	public List<BranchMeDetails> getOnCentralAndOutstandingOnBatchList() {
		log.info("getOnCentralAndOutstandingOnBatchList()");
		String sql = BranchMeDetailsRowMapper.SELECT_COL + " WHERE imported_on_batch is false " +
				"OR imported_on_batch is null";
		
		return getJdbcTemplate().query(sql, new BranchMeDetailsRowMapper<BranchMeDetails>());
	}

	public List<BranchMeDetails> getOnCentralNotRolledList() {
		log.info("getOnCentralNotRolledList()");
		String sql = BranchMeDetailsRowMapper.SELECT_COL + " WHERE me_rolled_date is null";
		
		return getJdbcTemplate().query(sql, new BranchMeDetailsRowMapper<BranchMeDetails>());
	}

}
