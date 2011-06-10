package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.BranchInfo;

public class BranchInfoRowMapper<T> implements RowMapper<BranchInfo> {

	public static final String SELECT_COL_SQL = "SELECT br_cde, " +
			"br_desc, " +
			"br_active, " +
			"br_closed, " +
			"grp_cde, " +
			"ctry_cde, " +
			"br_phone, " +
			"br_is_trading " +
			"	FROM branch";
	
	public BranchInfo mapRow(ResultSet rs, int arg1) throws SQLException {
		BranchInfo info = new BranchInfo();
		
		info.setBrCde(rs.getString("br_cde"));
		info.setBrDesc(rs.getString("br_desc"));
		info.setBrActive(rs.getBoolean("br_active"));
		info.setBrClosedDate(rs.getTimestamp("br_closed"));
		info.setGrpCde(rs.getString("grp_cde"));
		info.setCtryCde(rs.getString("ctry_cde"));
		info.setBrPhone(rs.getString("br_phone"));
		info.setBrTrading(rs.getBoolean("br_is_trading"));
		
		return info;
	}

}
