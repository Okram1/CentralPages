package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.UucpStatus;

public class UucpStatusRowMapper<T> implements RowMapper<UucpStatus> {

	public static String SELECT_COLUMNS_SQL = "SELECT last_uucp_comm, br_cde, central, message FROM uucp_status ";
	
	public UucpStatus mapRow(ResultSet rs, int arg1) throws SQLException {
		UucpStatus data = new UucpStatus();
		
		data.setBrCde(rs.getString("br_cde"));
		data.setCentral(rs.getString("central"));
		data.setLastUucpComm(rs.getTimestamp("last_uucp_comm"));
		data.setMessage(rs.getString("message"));

		return data;
	}

}
