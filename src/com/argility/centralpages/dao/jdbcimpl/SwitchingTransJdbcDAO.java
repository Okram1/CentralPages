package com.argility.centralpages.dao.jdbcimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.argility.centralpages.dao.SwitchingTransDAO;
import com.argility.centralpages.dao.mapper.SwAuditRowMapper;
import com.argility.centralpages.dao.mapper.SwitchingTranMapper;
import com.argility.centralpages.data.ActionTypeCountBean;
import com.argility.centralpages.data.BranchCountsBean;
import com.argility.centralpages.data.SwAudit;
import com.argility.centralpages.data.SwitchingTran;

public class SwitchingTransJdbcDAO extends AbstractDAO implements SwitchingTransDAO {

	public List<BranchCountsBean> getTotalsBySendingBranch() {
		log.info("getTotalsBySendingBranch()");
		String sql = "SELECT sw_audit_ctrl.br_cde, "
				+ "count(1) FROM sw_audit_ctrl "
				+ " WHERE sw_audit_ctrl.obo_aud_id is null "
				+ "group by br_cde order by count desc";

		List<BranchCountsBean> list = new ArrayList<BranchCountsBean>();

		list = getJdbcTemplate().query(sql, new RowMapper<BranchCountsBean>() {

			public BranchCountsBean mapRow(ResultSet rs, int i)
					throws SQLException {
				BranchCountsBean bean = new BranchCountsBean();

				bean.setBrCde(rs.getString("br_cde"));
				bean.setCount(rs.getInt("count"));

				return bean;
			}

		});

		log.info("Size is " + list.size());

		return list;
	}

	public List<BranchCountsBean> getTotalsByOboBranch() {
		log.info("getTotalsByOboBranch()");
		
		String sql = "SELECT sw_audit_ctrl.obo_br_cde, "
				+ "count(1) FROM sw_audit_ctrl "
				+ " WHERE sw_audit_ctrl.obo_aud_id is null "
				+ "group by obo_br_cde order by count desc";

		List<BranchCountsBean> list = new ArrayList<BranchCountsBean>();

		list = getJdbcTemplate().query(sql, new RowMapper<BranchCountsBean>() {

			public BranchCountsBean mapRow(ResultSet rs, int i)
					throws SQLException {
				BranchCountsBean bean = new BranchCountsBean();

				bean.setBrCde(rs.getString("obo_br_cde"));
				bean.setCount(rs.getInt("count"));

				return bean;
			}

		});

		return list;
	}

	public List<ActionTypeCountBean> getTotalsByActionType() {
		log.info("getTotalsByActionType()");
		/*String sql = "SELECT act_typ, "
				+ "	act_desc, "
				+ "	count(1) from sw_audit_ctrl join sw_audit using (sw_aud_id) "
				+ "	join action_typ using (act_typ) "
				+ " WHERE sw_audit_ctrl.obo_aud_id is null "
				+ "		group by act_typ, act_desc order by count desc";*/

		String sql = "SELECT * FROM sw_by_action_type";
		
		List<ActionTypeCountBean> list = new ArrayList<ActionTypeCountBean>();

		list = getJdbcTemplate().query(sql,
				new RowMapper<ActionTypeCountBean>() {

			public ActionTypeCountBean mapRow(ResultSet rs, int i) throws SQLException {
				ActionTypeCountBean bean = new ActionTypeCountBean();
				
				bean.setActTyp(rs.getInt("act_typ"));
				bean.setActionDesc(rs.getString("act_desc"));
				bean.setCount(rs.getInt("count"));
				
				return bean;
			}
			
		});
		return list;
	}

	public List<ActionTypeCountBean> getAllByActionTypeForBranch(String brCde) {
		log.info("getAllByActionTypeForBranch("+brCde+")");
		String sql = "SELECT act_typ, " +
				"act_desc, " +
				"count(1) " +
				"	from sw_audit_ctrl join sw_audit " +
				"	using (sw_aud_id) join action_typ " +
				"	using (act_typ) " +
				"		where sw_audit_ctrl.obo_br_cde = ? " +
				" 		AND sw_audit_ctrl.obo_aud_id is null " +
				"		group by act_typ, act_desc order by count desc";
		
		List<ActionTypeCountBean> list = new ArrayList<ActionTypeCountBean>();
		
		list = getJdbcTemplate().query(sql,
				new Object[] {brCde},
				new RowMapper<ActionTypeCountBean>() {

			public ActionTypeCountBean mapRow(ResultSet rs, int i) throws SQLException {
				ActionTypeCountBean bean = new ActionTypeCountBean();
				
				bean.setActTyp(rs.getInt("act_typ"));
				bean.setActionDesc(rs.getString("act_desc"));
				bean.setCount(rs.getInt("count"));
				
				return bean;
			}
			
		});
		return list;
	}

	public List<ActionTypeCountBean> getAllByActionTypeFromBranch(String brCde) {
		log.info("getAllByActionTypeFromBranch("+brCde+")");
		String sql = "SELECT act_typ, " +
				"act_desc, " +
				"count(1) " +
				"	from sw_audit_ctrl join sw_audit " +
				"	using (sw_aud_id) join action_typ " +
				"	using (act_typ) " +
				"		where sw_audit_ctrl.br_cde = ? " +
				" 		AND sw_audit_ctrl.obo_aud_id is null " +
				"		group by act_typ, act_desc order by count desc";
		
		List<ActionTypeCountBean> list = new ArrayList<ActionTypeCountBean>();
		
		list = getJdbcTemplate().query(sql,
				new Object[] {brCde},
				new RowMapper<ActionTypeCountBean>() {

			public ActionTypeCountBean mapRow(ResultSet rs, int i) throws SQLException {
				ActionTypeCountBean bean = new ActionTypeCountBean();
				
				bean.setActTyp(rs.getInt("act_typ"));
				bean.setActionDesc(rs.getString("act_desc"));
				bean.setCount(rs.getInt("count"));
				
				return bean;
			}
			
		});
		return list;
	}

	public List<SwitchingTran> getSwitchTransToBranch(String oboBrCde) {
		log.info("getSwitchTransToBranch("+oboBrCde+")");
		String sql = SwitchingTranMapper.SELECT_COL_SQL + 
			" WHERE sw_audit_ctrl.obo_aud_id is null AND sw_audit_ctrl.obo_br_cde = ?";
			
		return getJdbcTemplate().query(sql,
				new Object[]{oboBrCde},
				new SwitchingTranMapper<SwitchingTran>());
	}

	public List<SwitchingTran> getSwitchTransFromBranch(String brCde) {
		log.info("getSwitchTransFromBranch("+brCde+")");
		String sql = SwitchingTranMapper.SELECT_COL_SQL + 
		" WHERE sw_audit_ctrl.obo_aud_id is null AND sw_audit_ctrl.br_cde = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[]{brCde},
				new SwitchingTranMapper<SwitchingTran>());
	}

	public List<SwitchingTran> getSwitchTransByActionType(Integer actTyp) {
		log.info("getSwitchTransByActionType("+actTyp+")");
		String sql = SwitchingTranMapper.SELECT_COL_SQL + 
		" WHERE sw_audit_ctrl.obo_aud_id is null AND act_typ = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[]{actTyp},
				new SwitchingTranMapper<SwitchingTran>());
	}

	public List<SwitchingTran> getSwitchTransByAuditId(Integer audId) {
		log.info("getSwitchTransByAuditId("+audId+")");
		String sql = SwitchingTranMapper.SELECT_COL_SQL + " WHERE sw_audit_ctrl.aud_id = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[]{audId},
				new SwitchingTranMapper<SwitchingTran>());
	}

	public List<SwitchingTran> getSwitchTransBySwAuditId(Integer swAudId) {
		log.info("getSwitchTransBySwAuditId("+swAudId+")");
		String sql = SwitchingTranMapper.SELECT_COL_SQL + 
		" WHERE sw_audit_ctrl.obo_aud_id is null AND sw_audit_ctrl.sw_aud_id = ?";
		
		return getJdbcTemplate().query(sql,
				new Object[]{swAudId},
				new SwitchingTranMapper<SwitchingTran>());
	}
	
	public SwAudit getSwAudit(Integer swAudId) {
		log.info("getSwAudit("+swAudId+")");
		String sql = SwAuditRowMapper.SELECT_COLUMNS_SQL + " WHERE sw_aud_id = ?";
		
		return getJdbcTemplate().queryForObject(sql,
				new Object[] {swAudId},
				new SwAuditRowMapper<SwAudit>());
	}

}
