package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.SwitchLoadFailedDAO;
import com.argility.centralpages.dao.mapper.SwitchLoadFailedRowMapper;
import com.argility.centralpages.data.SwitchLoadFailed;

public class SwitchLoadFailedJdbcDAO extends AbstractDAO implements SwitchLoadFailedDAO{

	public List<SwitchLoadFailed> getAllSwitchLoadFailed() {
		log.info("getAllSwitchLoadFailed()");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + 
		"WHERE date_time > now() - '61 days'::interval ORDER BY date_time desc";
		
		return getJdbcTemplate().query(sql, new SwitchLoadFailedRowMapper<SwitchLoadFailed>());

	}

	public List<SwitchLoadFailed> getSwitchLoadFailedFromBranch(String brCde) {
		log.info("getSwitchLoadFailedFromBranch("+brCde+")");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE br_cde = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {brCde}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

	public List<SwitchLoadFailed> getSwitchLoadFailedByAudit(Integer audId) {
		log.info("getSwitchLoadFailedBranchAudit("+audId+")");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE aud_id = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {audId}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

	public List<SwitchLoadFailed> getSwitchLoadFailedToBranch(String brCde) {
		log.info("getSwitchLoadFailedToBranch("+brCde+")");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE obo_br_cde = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {brCde}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

	public List<SwitchLoadFailed> getSwitchLoadFailedByActionType(Integer actTyp) {
		log.info("getSwitchLoadFailedByActionType("+actTyp+")");
		String sql = SwitchLoadFailedRowMapper.SELECT_COLUMNS_SQL + " WHERE act_typ = ?";
		
		return getJdbcTemplate().query(sql, 
				new Object[] {actTyp}, 
				new SwitchLoadFailedRowMapper<SwitchLoadFailed>());
	}

}
