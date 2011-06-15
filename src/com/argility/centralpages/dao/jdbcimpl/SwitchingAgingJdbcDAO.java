package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.SwitchingAgingStatsDAO;
import com.argility.centralpages.dao.mapper.SwitchingAgingRowMapper;
import com.argility.centralpages.data.SwitchingAgingCount;

public class SwitchingAgingJdbcDAO extends AbstractDAO implements SwitchingAgingStatsDAO {

	public List<SwitchingAgingCount> getAllSwitchingAgingByOboBranch() {
		log.info("getAllSwitchingAgingByOboBranch()");
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_OBO_BRANCH;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(true, false, false));
	}

	public List<SwitchingAgingCount> getAllSwitchingAgingByBranchAndType() {
		log.info("getAllSwitchingAgingByBranchAndType()");
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_BR_AND_TYPE;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, false, true));

	}

	public List<SwitchingAgingCount> getAllSwitchingAgingByType() {
		log.info("getAllSwitchingAgingByType()");
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_TYPE;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, true, false));
	}

	public List<SwitchingAgingCount> getSwitchingAgingByType(Integer actTyp) {
		log.info("getSwitchingAgingByType()");
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_BR_AND_TYPE + " WHERE act_typ = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {actTyp},
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, false, true));
	}

	public List<SwitchingAgingCount> getSwitchingAgingByOboBranch(String brCde) {
		log.info("getSwitchingAgingByBranch()");
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_BR_AND_TYPE + " WHERE br_cde = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {brCde},
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, false, true));
	}

	public List<SwitchingAgingCount> getSwitchingAgingByBranchAndType(
			String brCde, Integer actTyp) {
		log.info("()");
		// TODO Auto-generated method stub
		return null;
	}

}
