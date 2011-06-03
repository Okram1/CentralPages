package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.SwitchLoadFailed;

public class SwitchLoadFailedRowMapper<T> implements RowMapper<SwitchLoadFailed>{

	public static String SELECT_COLUMNS_SQL = "SELECT act_typ, " +
			"br_cde, " +
			"date_time, " +
			"obo_br_cde, " +
			"error_message, " +
			"process, " +
			"aud_id, " +
			"stack_trace, " +
			"obo_aud_id," +
			"act_desc " +
			"	FROM switch_load_failed " +
			"	LEFT OUTER JOIN action_typ USING (act_typ)";
	
	public SwitchLoadFailed mapRow(ResultSet rs, int i)
			throws SQLException {
		SwitchLoadFailed slf = new SwitchLoadFailed();
		
		slf.setBrCde(rs.getString("br_cde"));
		slf.setAudId(rs.getInt("aud_id"));
		slf.setOboBrCde(rs.getString("obo_br_cde"));
		slf.setOboAudId(rs.getInt("obo_aud_id"));
		slf.setActTyp(rs.getInt("act_typ"));
		slf.setActDesc(rs.getString("act_desc"));
		slf.setDateTime(rs.getTimestamp("date_time"));
		slf.setStackTrace(rs.getString("stack_trace"));
		//slf.setErrorMessage(rs.getString("error_message"));
		//slf.setProcess(rs.getString("process"));
		
		return slf;
	}

}
