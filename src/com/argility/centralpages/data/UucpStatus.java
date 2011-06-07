package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UucpStatus implements Serializable {

	

	private String brCde;
	private String central;
	private Date lastUucpComm;
	private String message;

	public UucpStatus() {}

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

	public Date getLastUucpComm() {
		return this.lastUucpComm;
	}

	public void setLastUucpComm(Date lastUucpComm) {
		this.lastUucpComm = lastUucpComm;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
