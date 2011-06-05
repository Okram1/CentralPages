package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.SwitchingErrors;

public class SwitchingErrorsRowMapper<T> implements RowMapper<SwitchingErrors> {

	public static final String SELECT_COL_SQL = "select switching_errors.br_cde, " +
			"switching_errors.aud_id, " +
			"switching_errors.obo_br_cde, " +
			"error, " +
			"switching_errors.sw_aud_id, " +
			"act_typ, " +
			"act_desc " +
			"FROM switching_errors " +
			"join sw_audit using (sw_aud_id) " +
			"join action_typ using (act_typ)";
	
	public SwitchingErrors mapRow(ResultSet rs, int i) throws SQLException {
		SwitchingErrors swe = new SwitchingErrors();
		
		swe.setBrCde(rs.getString("br_cde"));
		swe.setAudId(rs.getInt("aud_id"));
		swe.setOboBrCde(rs.getString("obo_br_cde"));
		swe.setError(rs.getString("error"));
		swe.setSwAudId(rs.getInt("sw_aud_id"));
		swe.setActTyp(rs.getInt("act_typ"));
		swe.setActDesc(rs.getString("act_desc"));
		
		return swe;
	}

}
