package com.argility.centralpages.dao.jdbcimpl;

import java.util.List;

import com.argility.centralpages.dao.StatsProdDAO;
import com.argility.centralpages.dao.mapper.StatsProdRowMapper;
import com.argility.centralpages.data.StatsProd;

public class StatsProdJdbcDAO extends AbstractDAO implements StatsProdDAO{

	public List<StatsProd> getAllStatsProd() {
		log.info("getAllStatsProd()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL;
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getSwImportFailedList() {
		log.info("getSwImportFailedList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + " WHERE sw_crashed";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getCentralProcCrashedList() {
		log.info("getCentralProcCrashedList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE repl_process ilike '%crashed%' " +
		"		OR triad_process ilike '%crashed%'";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getSwManyToImportList() {
		log.info("getSwManyToImportList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + " WHERE sw_diff > 1000";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getReplicateAndNotImportedList() {
		log.info("getReplicateAndNotImportedList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE (last_replicated-last_sw_load) > '12:00:00' ORDER by last_sw_load";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getSwNotImportedForDaysList() {
		log.info("getSwNotImportedForDaysList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE last_sw_load < now() - '5 days'::interval ORDER by last_sw_load";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getXoutReceivedNotProcessedList() {
		log.info("getXoutReceivedNotProcessedList()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL + 
		" WHERE (xout_received-last_replicated) > '12:00:00' ORDER by last_replicated";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

	public List<StatsProd> getNotReplicatedForDays() {
		log.info("getNotReplicatedForDays()");
		String sql = StatsProdRowMapper.SELECT_COLUMNS_SQL +
		" WHERE last_replicated < now() - '5 days'::interval ORDER by last_replicated";
		
		return getJdbcTemplate().query(sql, new StatsProdRowMapper<StatsProd>());
	}

}
