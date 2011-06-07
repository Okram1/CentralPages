package com.argility.centralpages.dao;

import java.util.List;

import com.argility.centralpages.dao.mapper.UucpStatusRowMapper;
import com.argility.centralpages.data.UucpStatus;

public class SystemStatsJdbcDAO extends AbstractDAO implements SystemStatsDAO {

	public List<UucpStatus> getAllUucpStatus() {
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL;

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}

	public List<UucpStatus> getUucpStatusProblems() {
		String sql = UucpStatusRowMapper.SELECT_COLUMNS_SQL + 
			" WHERE message != 'Conversation complete' ORDER BY central";

		return getJdbcTemplate().query(sql, new UucpStatusRowMapper<UucpStatus>());
	}

}
