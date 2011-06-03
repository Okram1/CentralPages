package com.argility.centralpages.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.data.ProductionStats;

public class ProductionStatsRowMapper<T> implements RowMapper<ProductionStats>{

	public static String SELECT_COLUMNS_SQL = "SELECT aud_id, " +
			"br_cde, " +
			"date_time, " +
			"stack_trace, " +
			"error_message, " +
			"process, " +
			"message FROM production_stats ";
	
	public ProductionStats mapRow(ResultSet rs, int i) throws SQLException {
		ProductionStats ps = new ProductionStats();
		
		ps.setBrCde(rs.getString("br_cde"));
		ps.setAudId(rs.getInt("aud_id"));
		ps.setDateTime(rs.getTimestamp("date_time"));
		ps.setStackTrace(rs.getString("stack_trace"));
		ps.setErrorMessage(rs.getString("error_message"));
		ps.setProcess(rs.getString("process"));
		ps.setMessage(rs.getString("message"));
		
		return ps;
	}

}
