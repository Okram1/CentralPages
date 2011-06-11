package com.argility.centralpages.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SwitchingAgingCount implements Serializable {

	private String brCde;
	private Integer actTyp;
	private Integer totalCount;
	private Integer total2days;
	private Integer total5days;
	private Integer total10days;
	private Integer total6months;
	private Integer total12months;
	private Integer total12monthsPlus;

	public SwitchingAgingCount() {}

	public String getBrCde() {
		return this.brCde;
	}

	public void setBrCde(String brCde) {
		this.brCde = brCde;
	}

	public Integer getActTyp() {
		return this.actTyp;
	}

	public void setActTyp(Integer actTyp) {
		this.actTyp = actTyp;
	}

	public Integer getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotal2days() {
		return this.total2days;
	}

	public void setTotal2days(Integer total2days) {
		this.total2days = total2days;
	}

	public Integer getTotal5days() {
		return this.total5days;
	}

	public void setTotal5days(Integer total5days) {
		this.total5days = total5days;
	}

	public Integer getTotal10days() {
		return this.total10days;
	}

	public void setTotal10days(Integer total10days) {
		this.total10days = total10days;
	}

	public Integer getTotal6months() {
		return this.total6months;
	}

	public void setTotal6months(Integer total6months) {
		this.total6months = total6months;
	}

	public Integer getTotal12months() {
		return this.total12months;
	}

	public void setTotal12months(Integer total12months) {
		this.total12months = total12months;
	}

	public Integer getTotal12monthsPlus() {
		return this.total12monthsPlus;
	}

	public void setTotal12monthsPlus(Integer total12monthsPlus) {
		this.total12monthsPlus = total12monthsPlus;
	}

}
