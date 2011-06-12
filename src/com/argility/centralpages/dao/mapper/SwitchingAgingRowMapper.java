package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.SwitchingAgingCount;

public class SwitchingAgingRowMapper<T> implements RowMapper<SwitchingAgingCount> {

	public static String SELECT_SQL_BY_BR_AND_TYPE = "SELECT act_typ, " +
			"act_desc, " +
			"total_5days, " +
			"total_10days, " +
			"total_12months_plus, " +
			"br_cde, " +
			"total_6months, " +
			"total_count, " +
			"total_2days, " +
			"total_12months " +
			"	FROM daily_switching_mail_by_branch_by_type " +
			"	JOIN action_typ USING (act_typ) ";
	
	public static String SELECT_SQL_BY_BRANCH = "SELECT br_cde," +
			"total_5days, " +
			"total_10days, " +
			"total_12months_plus, " +
			"total_6months, " +
			"total_count, " +
			"total_2days, " +
			"total_12months " +
			"	FROM daily_switching_mail_by_branch ";
	
	public static String SELECT_SQL_BY_TYPE = "SELECT act_typ," +
			"act_desc, " +
			"total_5days, " +
			"total_10days, " +
			"total_12months_plus, " +
			"total_6months, " +
			"total_count, " +
			"total_2days, " +
			"total_12months " +
			"	FROM daily_switching_mail_by_type " +
			"	JOIN action_typ USING (act_typ) ";
	
	boolean isBranch, isType, isBoth;
	
	public SwitchingAgingRowMapper(boolean isBranch, boolean isType, boolean isBoth) {
		this.isBranch = isBranch;
		this.isType = isType;
		this.isBoth = isBoth;
	}
	
	public SwitchingAgingCount mapRow(ResultSet rs, int arg1) throws SQLException {
		SwitchingAgingCount data = new SwitchingAgingCount();
		
		if (isBranch || isBoth) data.setBrCde(rs.getString("br_cde"));
		if (isType || isBoth) data.setActTyp(rs.getInt("act_typ"));
		if (isType || isBoth) data.setActDesc(rs.getString("act_desc"));
		data.setTotalCount(rs.getInt("total_count"));
		data.setTotal2days(rs.getInt("total_2days"));
		data.setTotal5days(rs.getInt("total_5days"));
		data.setTotal10days(rs.getInt("total_10days"));
		data.setTotal6months(rs.getInt("total_6months"));
		data.setTotal12months(rs.getInt("total_12months"));
		data.setTotal12monthsPlus(rs.getInt("total_12months_plus"));

		return data;
	}

}
