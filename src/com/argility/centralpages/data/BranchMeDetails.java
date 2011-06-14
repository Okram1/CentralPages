package com.argility.centralpages.data;

import java.util.Date;

public class BranchMeDetails {

	// SELECT br_cde, branch_prod_details.central, me_instore_status, me_instore_last_update, 
	// uucp_status.message as uucp_message, last_uucp_comm, br_batch, repl_locked, xout_queued, 
	// repl_process, me_copy_queued_to_batch, me_copied_to_batch, dump_waiting_on_batch, 
	// me_replicate_started_date, imported_on_batch FROM branch_me_status JOIN branch_prod_details USING (br_cde) JOIN uucp_status USING (br_cde) JOIN branch_me_instore_details USING (br_cde);
	
	private String brCde;
	private String central;
	private String meInstoreStatus;
	private Date meInstoreLastUpdate;
	private String uucpMessage;
	private Date lastUucpComm;
	private String brBatch;
	private Boolean replLocked;
	private Boolean xoutQueued;
	private String replProcess;
	private Boolean meCopyQueued;
	private Boolean meDumpCopiedToBatch;
	private Boolean meDumpWaitingOnBatch;
	private Date meReplicateStarted;
	private Boolean importedOnBatch;
	
	public BranchMeDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getBrCde() {
		return brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public String getCentral() {
		return central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getMeInstoreStatus() {
		return meInstoreStatus;
	}

	public void setMeInstoreStatus(String meInstoreStatus) {
		this.meInstoreStatus = meInstoreStatus;
	}

	public Date getMeInstoreLastUpdate() {
		return meInstoreLastUpdate;
	}

	public void setMeInstoreLastUpdate(Date meInstoreLastUpdate) {
		this.meInstoreLastUpdate = meInstoreLastUpdate;
	}

	public String getUucpMessage() {
		return uucpMessage;
	}

	public void setUucpMessage(String uucpMessage) {
		this.uucpMessage = uucpMessage;
	}

	public Date getLastUucpComm() {
		return lastUucpComm;
	}

	public void setLastUucpComm(Date lastUucpComm) {
		this.lastUucpComm = lastUucpComm;
	}

	public String getBrBatch() {
		return brBatch;
	}

	public void setBrBatch(String brBatch) {
		this.brBatch = brBatch;
	}

	public Boolean getReplLocked() {
		return replLocked;
	}

	public void setReplLocked(Boolean replLocked) {
		this.replLocked = replLocked;
	}

	public Boolean getXoutQueued() {
		return xoutQueued;
	}

	public void setXoutQueued(Boolean xoutQueued) {
		this.xoutQueued = xoutQueued;
	}

	public String getReplProcess() {
		return replProcess;
	}

	public void setReplProcess(String replProcess) {
		this.replProcess = replProcess;
	}

	public Boolean getMeCopyQueued() {
		return meCopyQueued;
	}

	public void setMeCopyQueued(Boolean meCopyQueued) {
		this.meCopyQueued = meCopyQueued;
	}

	public Boolean getMeDumpCopiedToBatch() {
		return meDumpCopiedToBatch;
	}

	public void setMeDumpCopiedToBatch(Boolean meDumpCopiedToBatch) {
		this.meDumpCopiedToBatch = meDumpCopiedToBatch;
	}

	public Boolean getMeDumpWaitingOnBatch() {
		return meDumpWaitingOnBatch;
	}

	public void setMeDumpWaitingOnBatch(Boolean meDumpWaitingOnBatch) {
		this.meDumpWaitingOnBatch = meDumpWaitingOnBatch;
	}

	public Date getMeReplicateStarted() {
		return meReplicateStarted;
	}

	public void setMeReplicateStarted(Date meReplicateStarted) {
		this.meReplicateStarted = meReplicateStarted;
	}

	public Boolean getImportedOnBatch() {
		return importedOnBatch;
	}

	public void setImportedOnBatch(Boolean importedOnBatch) {
		this.importedOnBatch = importedOnBatch;
	}

}
