package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import com.argility.centralpages.dao.SystemStatsDAO;
import com.argility.centralpages.dao.mapper.BranchInfoRowMapper;
import com.argility.centralpages.dao.mapper.CentralSystemDetailsRowMapper;
import com.argility.centralpages.dao.mapper.UucpStatusRowMapper;
import com.argility.centralpages.data.BranchInfo;
import com.argility.centralpages.data.CentralSystemDetails;
import com.argility.centralpages.data.UucpStatus;

public class SystemStatsJdbcDAO extends AbstractDAO implements SystemStatsDAO {

	public List<UucpStatus> getAllCentralUucpStatus() {
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL;

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}
	
	public List<UucpStatus> getAllSwitchingUucpStatus() {
		String sql = UucpStatusRowMapper.SW_SELECT_COLUMNS_SQL;

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}

	public List<UucpStatus> getUucpStatusProblems() {
		log.info("getUucpStatusProblems()");
		
		String where = " WHERE message != 'Conversation complete' ORDER BY central";
		
		String sqlCentral = UucpStatusRowMapper.SELECT_COLUMNS_SQL + where;
		String sqlSwitching = UucpStatusRowMapper.SW_SELECT_COLUMNS_SQL + where; 
		

		List<UucpStatus> uucpProblemCentral = 
			getJdbcTemplate().query(sqlCentral, new UucpStatusRowMapper<UucpStatus>());
		List<UucpStatus> uucpProblemSwitching = 
			getJdbcTemplate().query(sqlSwitching, new UucpStatusRowMapper<UucpStatus>());
		
		uucpProblemCentral.addAll(uucpProblemSwitching);
		
		return uucpProblemCentral;
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

	public List<CentralSystemDetails> getCentralSystemDetails() {
		log.info("getCentralSystemDetails()");
		
		String sql = CentralSystemDetailsRowMapper.SELECT_COLUMNS_SQL;
		
		return getJdbcTemplate().query(sql, new CentralSystemDetailsRowMapper<CentralSystemDetails>());
	}

	public List<UucpStatus> getUucpStatusForCentral(String central) {
		log.info("getUucpStatusForCentral("+central+")");
		
		String where = "WHERE central = ? ORDER BY central";
		
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL;
		
		if (central.equals("9900")) {
			sql = UucpStatusRowMapper.SW_SELECT_COLUMNS_SQL;
		}

		sql += where;
		
		return getJdbcTemplate().query(sql,
				new Object[] {central},
				new UucpStatusRowMapper<UucpStatus>());
	}

	
}
