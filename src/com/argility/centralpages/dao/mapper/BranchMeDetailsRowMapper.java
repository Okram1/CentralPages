package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.BranchMeDetails;

public class BranchMeDetailsRowMapper<T> implements RowMapper<BranchMeDetails> {

	public static final String SELECT_COL = "SELECT br_cde, " +
			"branch_prod_details.central, " +
			"me_instore_status, " +
			"me_instore_last_update, " +
			"uucp_status.message as uucp_message, " +
			"last_uucp_comm, " +
			"br_batch, " +
			"repl_locked, " +
			"xout_queued, " +
			"repl_process, " +
			"me_copy_queued_to_batch, " +
			"me_copied_to_batch, " +
			"dump_waiting_on_batch, " +
			"me_replicate_started_date, " +
			"imported_on_batch " +
			"	FROM branch_me_status " +
			"	JOIN branch_prod_details USING (br_cde) " +
			"	JOIN uucp_status USING (br_cde) " +
			"	JOIN branch_me_instore_details USING (br_cde)";
	
	public BranchMeDetails mapRow(ResultSet rs, int arg1) throws SQLException {
		
		BranchMeDetails data = new BranchMeDetails();
		
		data.setBrCde(rs.getString("br_cde"));
		data.setCentral(rs.getString("central"));
		data.setBrBatch(rs.getString("br_batch"));
		data.setMeInstoreStatus(rs.getString("me_instore_status"));
		data.setUucpMessage(rs.getString("uucp_message"));
		data.setLastUucpComm(rs.getTimestamp("last_uucp_comm"));
		data.setReplLocked(rs.getBoolean("repl_locked"));
		data.setXoutQueued(rs.getBoolean("xout_queued"));
		data.setReplProcess(rs.getString("repl_process"));
		data.setMeCopyQueued(rs.getBoolean("me_copy_queued_to_batch"));
		data.setMeDumpWaitingOnBatch(rs.getBoolean("dump_waiting_on_batch"));
		data.setMeDumpCopiedToBatch(rs.getBoolean("me_copied_to_batch"));
		data.setMeReplicateStarted(rs.getTimestamp("me_replicate_started_date"));
		data.setImportedOnBatch(rs.getBoolean("imported_on_batch"));
		
		return data;
	}

}
