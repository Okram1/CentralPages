package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SwitchLoadFailed implements Serializable {

	public static String SELECT_COLUMNS_SQL = "SELECT act_typ, br_cde, date_time, obo_br_cde, error_message, process, aud_id, stack_trace, obo_aud_id FROM switch_load_failed ";

	private String brCde;
	private Integer audId;
	private String oboBrCde;
	private Integer oboAudId;
	private Integer actTyp;
	private String actDesc;
	private Date dateTime;
	private String errorMessage;
	private String stackTrace;
	private String process;

	public SwitchLoadFailed() {}

	public String getBrCde() {
		return this.brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public Integer getAudId() {
		return this.audId;
	}

	public void setAudId(Integer audId) {
		this.audId = audId;
	}

	public String getOboBrCde() {
		return this.oboBrCde;
	}

	public void setOboBrCde(String oboBrCde) {
		this.oboBrCde = oboBrCde;
	}

	public Integer getOboAudId() {
		return this.oboAudId;
	}

	public void setOboAudId(Integer oboAudId) {
		this.oboAudId = oboAudId;
	}

	public Integer getActTyp() {
		return this.actTyp;
	}

	public void setActTyp(Integer actTyp) {
		this.actTyp = actTyp;
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStackTrace() {
		return this.stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

}
