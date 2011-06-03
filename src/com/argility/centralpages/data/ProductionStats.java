package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ProductionStats implements Serializable {

	public static String SELECT_COLUMNS_SQL = "SELECT aud_id, br_cde, date_time, stack_trace, error_message, process, message FROM production_stats ";

	private String brCde;
	private Date dateTime;
	private String process;
	private Integer audId;
	private String errorMessage;
	private String stackTrace;
	private String message;

	public ProductionStats() {}

	public String getBrCde() {
		return this.brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public Integer getAudId() {
		return this.audId;
	}

	public void setAudId(Integer audId) {
		this.audId = audId;
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

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
