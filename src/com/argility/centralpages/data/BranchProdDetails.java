package com.argility.centralpages.data;

import java.util.Date;
import java.io.Serializable;

@SuppressWarnings("serial")
public class BranchProdDetails implements Serializable {

	public static String SELECT_COLUMNS_SQL = "SELECT repl_audit_up_to, repl_up_to, br_cde, sw_crash_aud_id, br_batch, br_repl_lock_date, sw_aud_up_to, xout_queued, fpp_cde, triad_live, central, xout_received, br_repl_unlock_date, repl_diff, repl_locked, sw_diff, last_sw_load, me_replicate_started_date, repl_process, me_rolled_date, sw_crashed, triad_locked FROM branch_prod_details ";

	private String brCde;
	private String central;
	private String fppCde;
	private String brBatch;
	private Integer replAuditUpTo;
	private Integer replUpTo;
	private Integer replDiff;
	private Boolean replLocked;
	private Date brReplLockDate;
	private Date brReplUnlockDate;
	private Date xoutReceived;
	private String replProcess;
	private Boolean xoutQueued;
	private Boolean triadLive;
	private Boolean triadLocked;
	private Date lastSwLoad;
	private Integer swAudUpTo;
	private Integer swDiff;
	private Boolean swCrashed;
	private Integer swCrashAudId;
	private Date meReplicateStartedDate;
	private Date meRolledDate;

	public BranchProdDetails() {}

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

	public String getFppCde() {
		return this.fppCde;
	}

	public void setFppCde(String fppCde) {
		this.fppCde = fppCde;
	}

	public String getBrBatch() {
		return this.brBatch;
	}

	public void setBrBatch(String brBatch) {
		this.brBatch = brBatch;
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

	public Boolean getReplLocked() {
		return this.replLocked;
	}

	public void setReplLocked(Boolean replLocked) {
		this.replLocked = replLocked;
	}

	public Date getBrReplLockDate() {
		return this.brReplLockDate;
	}

	public void setBrReplLockDate(Date brReplLockDate) {
		this.brReplLockDate = brReplLockDate;
	}

	public Date getBrReplUnlockDate() {
		return this.brReplUnlockDate;
	}

	public void setBrReplUnlockDate(Date brReplUnlockDate) {
		this.brReplUnlockDate = brReplUnlockDate;
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

	public Boolean getXoutQueued() {
		return this.xoutQueued;
	}

	public void setXoutQueued(Boolean xoutQueued) {
		this.xoutQueued = xoutQueued;
	}

	public Boolean getTriadLive() {
		return this.triadLive;
	}

	public void setTriadLive(Boolean triadLive) {
		this.triadLive = triadLive;
	}

	public Boolean getTriadLocked() {
		return this.triadLocked;
	}

	public void setTriadLocked(Boolean triadLocked) {
		this.triadLocked = triadLocked;
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

	public Date getMeReplicateStartedDate() {
		return this.meReplicateStartedDate;
	}

	public void setMeReplicateStartedDate(Date meReplicateStartedDate) {
		this.meReplicateStartedDate = meReplicateStartedDate;
	}

	public Date getMeRolledDate() {
		return this.meRolledDate;
	}

	public void setMeRolledDate(Date meRolledDate) {
		this.meRolledDate = meRolledDate;
	}

}
