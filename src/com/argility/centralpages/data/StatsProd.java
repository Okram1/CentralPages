package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StatsProd implements Serializable {

	public static String SELECT_COLUMNS_SQL = "SELECT repl_diff, repl_audit_up_to, repl_up_to, sw_diff, last_sw_load, br_cde, last_replicated, repl_process, sw_crash_aud_id, triad_process, sw_aud_up_to, sw_crashed, xout_received, central FROM stats_prod ";

	private String brCde;
	private String central;
	private Date lastReplicated;
	private Date xoutReceived;
	private String replProcess;
	private String triadProcess;
	private Integer replAuditUpTo;
	private Integer replUpTo;
	private Integer replDiff;
	private Date lastSwLoad;
	private Integer swAudUpTo;
	private Integer swDiff;
	private Boolean swCrashed;
	private Integer swCrashAudId;

	public StatsProd() {}

	public String getBrCde() {
		return this.brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public String getCentral() {
		return this.central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public Date getLastReplicated() {
		return this.lastReplicated;
	}

	public void setLastReplicated(Date lastReplicated) {
		this.lastReplicated = lastReplicated;
	}

	public Date getXoutReceived() {
		return this.xoutReceived;
	}

	public void setXoutReceived(Date xoutReceived) {
		this.xoutReceived = xoutReceived;
	}

	public String getReplProcess() {
		return this.replProcess;
	}

	public void setReplProcess(String replProcess) {
		this.replProcess = replProcess;
	}

	public String getTriadProcess() {
		return this.triadProcess;
	}

	public void setTriadProcess(String triadProcess) {
		this.triadProcess = triadProcess;
	}

	public Integer getReplAuditUpTo() {
		return this.replAuditUpTo;
	}

	public void setReplAuditUpTo(Integer replAuditUpTo) {
		this.replAuditUpTo = replAuditUpTo;
	}

	public Integer getReplUpTo() {
		return this.replUpTo;
	}

	public void setReplUpTo(Integer replUpTo) {
		this.replUpTo = replUpTo;
	}

	public Integer getReplDiff() {
		return this.replDiff;
	}

	public void setReplDiff(Integer replDiff) {
		this.replDiff = replDiff;
	}

	public Date getLastSwLoad() {
		return this.lastSwLoad;
	}

	public void setLastSwLoad(Date lastSwLoad) {
		this.lastSwLoad = lastSwLoad;
	}

	public Integer getSwAudUpTo() {
		return this.swAudUpTo;
	}

	public void setSwAudUpTo(Integer swAudUpTo) {
		this.swAudUpTo = swAudUpTo;
	}

	public Integer getSwDiff() {
		return this.swDiff;
	}

	public void setSwDiff(Integer swDiff) {
		this.swDiff = swDiff;
	}

	public Boolean getSwCrashed() {
		return this.swCrashed;
	}

	public void setSwCrashed(Boolean swCrashed) {
		this.swCrashed = swCrashed;
	}

	public Integer getSwCrashAudId() {
		return this.swCrashAudId;
	}

	public void setSwCrashAudId(Integer swCrashAudId) {
		this.swCrashAudId = swCrashAudId;
	}

}
