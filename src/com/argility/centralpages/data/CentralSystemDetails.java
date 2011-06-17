package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CentralSystemDetails implements Serializable {

	private String central;
	private Date updateDate;
	private Boolean comms;
	private Integer jmsQueueSize;
	private Integer replFilesToProcess;
	private Boolean dbRunning;
	private Boolean jbossRunning;
	private Boolean cronRunning;
	private String disksFull;

	public CentralSystemDetails() {}

	public String getCentral() {
		return this.central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Boolean getComms() {
		return this.comms;
	}

	public void setComms(Boolean comms) {
		this.comms = comms;
	}

	public Integer getJmsQueueSize() {
		return this.jmsQueueSize;
	}

	public void setJmsQueueSize(Integer jmsQueueSize) {
		this.jmsQueueSize = jmsQueueSize;
	}

	public Integer getReplFilesToProcess() {
		return this.replFilesToProcess;
	}

	public void setReplFilesToProcess(Integer replFilesToProcess) {
		this.replFilesToProcess = replFilesToProcess;
	}

	public Boolean getDbRunning() {
		return this.dbRunning;
	}

	public void setDbRunning(Boolean dbRunning) {
		this.dbRunning = dbRunning;
	}

	public Boolean getJbossRunning() {
		return this.jbossRunning;
	}

	public void setJbossRunning(Boolean jbossRunning) {
		this.jbossRunning = jbossRunning;
	}

	public Boolean getCronRunning() {
		return this.cronRunning;
	}

	public void setCronRunning(Boolean cronRunning) {
		this.cronRunning = cronRunning;
	}

	public String getDisksFull() {
		return this.disksFull;
	}

	public void setDisksFull(String disksFull) {
		this.disksFull = disksFull;
	}

}
