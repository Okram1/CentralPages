package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.CentralSystemDetails;

public class CentralSystemDetailsRowMapper<T> implements RowMapper<CentralSystemDetails> {

	public static String SELECT_COLUMNS_SQL = "SELECT db_running, " +
			"disks_full, " +
			"update_date, " +
			"repl_files_to_process, " +
			"cron_running, " +
			"jms_queue_size, " +
			"jboss_running, " +
			"comms, " +
			"central " +
			"	FROM central_system_details ";
	
	public CentralSystemDetails mapRow(ResultSet rs, int arg1)
			throws SQLException {
		CentralSystemDetails data = new CentralSystemDetails();
		
		data.setCentral(rs.getString("central"));
		data.setUpdateDate(rs.getTimestamp("update_date"));
		data.setComms(rs.getBoolean("comms"));
		data.setJmsQueueSize(rs.getInt("jms_queue_size"));
		data.setReplFilesToProcess(rs.getInt("repl_files_to_process"));
		data.setDbRunning(rs.getBoolean("db_running"));
		data.setJbossRunning(rs.getBoolean("jboss_running"));
		data.setCronRunning(rs.getBoolean("cron_running"));
		data.setDisksFull(rs.getString("disks_full"));

		return data;
	}

}
