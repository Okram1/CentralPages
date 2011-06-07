package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.SwitchingTran;

public class SwitchingTranMapper<T> implements RowMapper<SwitchingTran> {

	public static final String SELECT_COL_SQL = "SELECT sw_audit_Ctrl.sw_aud_id, " +
			"sw_audit_ctrl.br_Cde, " +
			"sw_audit_ctrl.aud_id, " +
			"sw_audit_ctrl.obo_br_Cde, " +
			"aud_ts, " +
			"act_typ, " +
			"act_desc, " +
			"sw_aud_dte " +
			"	FROM sw_audit_Ctrl " +
			"	JOIN sw_audit USING (sw_aud_id) " +
			"	JOIN action_typ USING (act_Typ)";
	
	public SwitchingTran mapRow(ResultSet rs, int arg1) throws SQLException {
		SwitchingTran tran = new SwitchingTran();
		
		tran.setSwAudId(rs.getInt("sw_aud_id"));
		tran.setBrCde(rs.getString("br_cde"));
		tran.setAudId(rs.getInt("aud_id"));
		tran.setAudTs(rs.getTimestamp("aud_ts"));
		tran.setOboBrCde(rs.getString("obo_br_cde"));
		tran.setActTyp(rs.getInt("act_typ"));
		tran.setActDesc(rs.getString("act_desc"));
		tran.setSwAudDte(rs.getTimestamp("sw_aud_dte"));
		
		return tran;
	}

}
