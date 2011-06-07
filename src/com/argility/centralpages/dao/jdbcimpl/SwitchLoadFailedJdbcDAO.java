package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.SwitchLoadFailedDAO;
import com.argility.centralpages.dao.mapper.SwitchLoadFailedRowMapper;
import com.argility.centralpages.data.SwitchLoadFailed;

public class SwitchLoadFailedJdbcDAO extends AbstractDAO implements SwitchLoadFailedDAO{

	public List<SwitchLoadFailed> getAllSwitchLoadFailed() {
		log.info("getAllSwitchLoadFailed()");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + 
		"WHERE date_time > now() - '31 days'::interval ORDER BY date_time desc";
		
		return getJdbcTemplate().query(sql, new SwitchLoadFailedRowMapper<SwitchLoadFailed>());

	}

	public List<SwitchLoadFailed> getSwitchLoadFailedFromBranch(String brCde) {
		log.info("getSwitchLoadFailedFromBranch()");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {brCde}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

	public List<SwitchLoadFailed> getSwitchLoadFailedBranchAudit(String brCde,
			Integer audId) {
		log.info("getSwitchLoadFailedBranchAudit()");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ? AND aud_id = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {brCde, audId}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

}
