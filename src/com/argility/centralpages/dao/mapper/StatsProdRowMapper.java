package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.StatsProd;

public class StatsProdRowMapper<T> implements RowMapper<StatsProd> {

	public static String SELECT_COLUMNS_SQL = "SELECT repl_diff, " +
			"repl_audit_up_to, " +
			"repl_up_to, " +
			"sw_diff, " +
			"last_sw_load, " +
			"br_cde, " +
			"last_replicated, " +
			"repl_process, " +
			"sw_crash_aud_id, " +
			"triad_process, " +
			"sw_aud_up_to, " +
			"sw_crashed, " +
			"xout_received, " +
			"central " +
			"FROM stats_prod ";
	
	public StatsProd mapRow(ResultSet rs, int i) throws SQLException {
		StatsProd sp = new StatsProd();
		
		sp.setBrCde(rs.getString("br_cde"));
		sp.setCentral(rs.getString("central"));
		sp.setLastReplicated(rs.getTimestamp("last_replicated"));
		sp.setXoutReceived(rs.getTimestamp("xout_received"));
		sp.setReplProcess(rs.getString("repl_process"));
		sp.setTriadProcess(rs.getString("triad_process"));
		sp.setReplAuditUpTo(rs.getInt("repl_audit_up_to"));
		sp.setReplUpTo(rs.getInt("repl_up_to"));
		sp.setReplDiff(rs.getInt("repl_diff"));
		sp.setLastSwLoad(rs.getTimestamp("last_sw_load"));
		sp.setSwAudUpTo(rs.getInt("sw_aud_up_to"));
		sp.setSwDiff(rs.getInt("sw_diff"));
		sp.setSwCrashed(rs.getBoolean("sw_crashed"));
		sp.setSwCrashAudId(rs.getInt("sw_crash_aud_id"));
		
		return sp;
	}

}
