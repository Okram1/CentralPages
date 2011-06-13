package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.dao.mapper.BranchInfoRowMapper;
import com.argility.centralpages.dao.mapper.UucpStatusRowMapper;
import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.UucpStatus;

public class SystemStatsJdbcDAO extends AbstractDAO implements SystemStatsDAO {

	public List<UucpStatus> getAllUucpStatus() {
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL;

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}

	public List<UucpStatus> getUucpStatusProblems() {
		log.info("getUucpStatusProblems()");
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL + 
			" WHERE message != 'Conversation complete' ORDER BY central";

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}

	public BranchInfo getBrInfo(String brCde) {
		log.info("getBrInfo(" + brCde + ")");
		String sql = BranchInfoRowMapper.SELECT_COL_SQL + " WHERE br_cde = ?";
		
		BranchInfo brInfo = null;
		
		try {
			brInfo = getJdbcTemplate().queryForObject(sql,
					new Object[] {brCde},
					new BranchInfoRowMapper<BranchInfo>());
		} catch (EmptyResultDataAccessException e) {}
		
		log.info("BR INFO " + brInfo);
		
		return brInfo;
	}

}
