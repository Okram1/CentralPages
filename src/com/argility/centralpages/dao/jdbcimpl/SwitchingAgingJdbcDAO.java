package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.SwitchingAgingStatsDAO;
import com.argility.centralpages.dao.mapper.SwitchingAgingRowMapper;
import com.argility.centralpages.data.SwitchingAgingCount;

public class SwitchingAgingJdbcDAO extends AbstractDAO implements SwitchingAgingStatsDAO {

	public List<SwitchingAgingCount> getAllSwitchingAgingByBranch() {
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_BRANCH;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(true, false, false));
	}

	public List<SwitchingAgingCount> getAllSwitchingAgingByBranchAndType() {
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_BR_AND_TYPE;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, false, true));

	}

	public List<SwitchingAgingCount> getAllSwitchingAgingByType() {
		String sql  = SwitchingAgingRowMapper.SELECT_SQL_BY_TYPE;
		
		return getJdbcTemplate().query(sql, 
				new SwitchingAgingRowMapper<SwitchingAgingCount>(false, true, false));
	}

	public List<SwitchingAgingCount> getSwitchingAgingByType(Integer actTyp) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SwitchingAgingCount> getSwitchingAgingByBranch(String brCde) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<SwitchingAgingCount> getSwitchingAgingByBranchAndType(
			String brCde, Integer actTyp) {
		// TODO Auto-generated method stub
		return null;
	}

}
