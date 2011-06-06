package com.argility.centralpages.data;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SwAudit implements Serializable {
	
	private Integer swAudId;
	private Date swAudDte;
	private Integer audId;
	private String grpCde;
	private Integer actTyp;
	private Integer audVer;
	private String usrId;
	private String audDeviceId;
	private Date audTs;
	private String audXml;
	private String audReplication;
	private String fppCde;
	private String audDocNo;
	private Date audDocTs;
	private String brCde;
	private String oboBrCde;
	private Integer oboAudId;

	public SwAudit() {}

	public Integer getSwAudId() {
		return this.swAudId;
	}

	public void setSwAudId(Integer swAudId) {
		this.swAudId = swAudId;
	}

	public Date getSwAudDte() {
		return this.swAudDte;
	}

	public void setSwAudDte(Date swAudDte) {
		this.swAudDte = swAudDte;
	}

	public Integer getAudId() {
		return this.audId;
	}

	public void setAudId(Integer audId) {
		this.audId = audId;
	}

	public String getGrpCde() {
		return this.grpCde;
	}

	public void setGrpCde(String grpCde) {
		this.grpCde = grpCde;
	}

	public Integer getActTyp() {
		return this.actTyp;
	}

	public void setActTyp(Integer actTyp) {
		this.actTyp = actTyp;
	}

	public Integer getAudVer() {
		return this.audVer;
	}

	public void setAudVer(Integer audVer) {
		this.audVer = audVer;
	}

	public String getUsrId() {
		return this.usrId;
	}

	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	public String getAudDeviceId() {
		return this.audDeviceId;
	}

	public void setAudDeviceId(String audDeviceId) {
		this.audDeviceId = audDeviceId;
	}

	public Date getAudTs() {
		return this.audTs;
	}

	public void setAudTs(Date audTs) {
		this.audTs = audTs;
	}

	public String getAudXml() {
		return this.audXml;
	}

	public void setAudXml(String audXml) {
		this.audXml = audXml;
	}

	public String getAudReplication() {
		return this.audReplication;
	}

	public void setAudReplication(String audReplication) {
		this.audReplication = audReplication;
	}

	public String getFppCde() {
		return this.fppCde;
	}

	public void setFppCde(String fppCde) {
		this.fppCde = fppCde;
	}

	public String getAudDocNo() {
		return this.audDocNo;
	}

	public void setAudDocNo(String audDocNo) {
		this.audDocNo = audDocNo;
	}

	public Date getAudDocTs() {
		return this.audDocTs;
	}

	public void setAudDocTs(Date audDocTs) {
		this.audDocTs = audDocTs;
	}

	public String getBrCde() {
		return this.brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
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

}
