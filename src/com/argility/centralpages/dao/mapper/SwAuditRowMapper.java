package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.SwAudit;

public class SwAuditRowMapper<T> implements RowMapper<SwAudit> {

	public static String SELECT_COLUMNS_SQL = "SELECT aud_ts, " +
			"aud_doc_ts, " +
			"br_cde, " +
			"obo_br_cde, " +
			"fpp_cde, " +
			"sw_aud_id, " +
			"aud_xml, " +
			"act_typ, " +
			"sw_aud_dte, " +
			"usr_id, " +
			"aud_ver, " +
			"aud_id, " +
			"aud_replication, " +
			"grp_cde, " +
			"aud_doc_no, " +
			"aud_device_id, " +
			"obo_aud_id " +
			"	FROM sw_audit ";
	
	public SwAudit mapRow(ResultSet rs, int arg1) throws SQLException {
		SwAudit data = new SwAudit();
		
		data.setSwAudId(rs.getInt("sw_aud_id"));
		data.setSwAudDte(rs.getTimestamp("sw_aud_dte"));
		data.setAudId(rs.getInt("aud_id"));
		data.setGrpCde(rs.getString("grp_cde"));
		data.setActTyp(rs.getInt("act_typ"));
		data.setAudVer(rs.getInt("aud_ver"));
		data.setUsrId(rs.getString("usr_id"));
		data.setAudDeviceId(rs.getString("aud_device_id"));
		data.setAudTs(rs.getTimestamp("aud_ts"));
		data.setAudXml(rs.getString("aud_xml"));
		data.setAudReplication(rs.getString("aud_replication"));
		data.setFppCde(rs.getString("fpp_cde"));
		data.setAudDocNo(rs.getString("aud_doc_no"));
		data.setAudDocTs(rs.getTimestamp("aud_doc_ts"));
		data.setBrCde(rs.getString("br_cde"));
		data.setOboBrCde(rs.getString("obo_br_cde"));
		data.setOboAudId(rs.getInt("obo_aud_id"));
		
		return data;
	}

}
