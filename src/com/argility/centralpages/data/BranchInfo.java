package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class BranchInfo implements Serializable{

	private String brCde;
	private String brDesc;
	private Boolean brActive;
	private Date brClosedDate;
	private String grpCde;
	private String ctryCde;
	private String brPhone;
	private Boolean brTrading;
	
	public BranchInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getBrCde() {
		return brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public String getBrDesc() {
		return brDesc;
	}

	public void setBrDesc(String brDesc) {
		this.brDesc = brDesc;
	}
	
	public Date getBrClosedDate() {
		return brClosedDate;
	}

	public void setBrClosedDate(Date brClosedDate) {
		this.brClosedDate = brClosedDate;
	}

	public String getGrpCde() {
		return grpCde;
	}

	public void setGrpCde(String grpCde) {
		this.grpCde = grpCde;
	}

	public String getCtryCde() {
		return ctryCde;
	}

	public void setCtryCde(String ctryCde) {
		this.ctryCde = ctryCde;
	}

	public String getBrPhone() {
		return brPhone;
	}

	public void setBrPhone(String brPhone) {
		this.brPhone = brPhone;
	}

	public Boolean getBrActive() {
		return brActive;
	}

	public void setBrActive(Boolean brActive) {
		this.brActive = brActive;
	}

	public Boolean getBrTrading() {
		return brTrading;
	}

	public void setBrTrading(Boolean brTrading) {
		this.brTrading = brTrading;
	}
	
}
