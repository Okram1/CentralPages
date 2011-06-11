package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.BranchProdDetails;

public class BranchProdDetailsRowMapper<T> implements RowMapper<BranchProdDetails> {

	public static String SELECT_COLUMNS_SQL = "SELECT repl_audit_up_to, " +
			"repl_up_to, " +
			"br_cde, " +
			"sw_crash_aud_id, " +
			"br_batch, " +
			"br_repl_lock_date, " +
			"sw_aud_up_to, " +
			"xout_queued, " +
			"fpp_cde, " +
			"triad_live, " +
			"central, " +
			"xout_received, " +
			"br_repl_unlock_date, " +
			"repl_diff, " +
			"repl_locked, " +
			"sw_diff, " +
			"last_sw_load, " +
			"me_replicate_started_date, " +
			"repl_process, " +
			"me_rolled_date, " +
			"sw_crashed, " +
			"triad_locked " +
			"	FROM branch_prod_details ";
	
	
	public BranchProdDetails mapRow(ResultSet rs, int i) throws SQLException {
		BranchProdDetails data = new BranchProdDetails();
		
		data.setBrCde(rs.getString("br_cde"));
		data.setCentral(rs.getString("central"));
		data.setFppCde(rs.getString("fpp_cde"));
		data.setBrBatch(rs.getString("br_batch"));
		data.setReplAuditUpTo(rs.getInt("repl_audit_up_to"));
		data.setReplUpTo(rs.getInt("repl_up_to"));
		data.setReplDiff(rs.getInt("repl_diff"));
		data.setReplLocked(rs.getBoolean("repl_locked"));
		data.setBrReplLockDate(rs.getTimestamp("br_repl_lock_date"));
		data.setBrReplUnlockDate(rs.getTimestamp("br_repl_unlock_date"));
		data.setXoutReceived(rs.getTimestamp("xout_received"));
		data.setReplProcess(rs.getString("repl_process"));
		data.setXoutQueued(rs.getBoolean("xout_queued"));
		data.setTriadLive(rs.getBoolean("triad_live"));
		data.setTriadLocked(rs.getBoolean("triad_locked"));
		data.setLastSwLoad(rs.getTimestamp("last_sw_load"));
		data.setSwAudUpTo(rs.getInt("sw_aud_up_to"));
		data.setSwDiff(rs.getInt("sw_diff"));
		data.setSwCrashed(rs.getBoolean("sw_crashed"));
		data.setSwCrashAudId(rs.getInt("sw_crash_aud_id"));
		data.setMeReplicateStartedDate(rs.getTimestamp("me_replicate_started_date"));
		data.setMeRolledDate(rs.getTimestamp("me_rolled_date"));
		
		return data;
	}

}
