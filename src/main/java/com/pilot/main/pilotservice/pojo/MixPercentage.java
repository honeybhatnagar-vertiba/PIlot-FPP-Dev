package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class MixPercentage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal mixActual;
	
	private BigDecimal mixTarget;
	
	private BigDecimal mixVsLy;
	
	private Boolean mixVsLyPositive;

	public BigDecimal getMixActual() {
		return mixActual;
	}

	public void setMixActual(BigDecimal mixActual) {
		this.mixActual = mixActual;
	}

	public BigDecimal getMixTarget() {
		return mixTarget;
	}

	public void setMixTarget(BigDecimal mixTarget) {
		this.mixTarget = mixTarget;
	}

	public BigDecimal getMixVsLy() {
		return mixVsLy;
	}

	public void setMixVsLy(BigDecimal mixVsLy) {
		this.mixVsLy = mixVsLy;
	}

	public Boolean getMixVsLyPositive() {
		return mixVsLyPositive;
	}

	public void setMixVsLyPositive(Boolean mixVsLyPositive) {
		this.mixVsLyPositive = mixVsLyPositive;
	}

	@Override
	public String toString() {
		return "MixPercentage [mixActual=" + mixActual + ", mixTarget=" + mixTarget + ", mixVsLy=" + mixVsLy
				+ ", mixVsLyPositive=" + mixVsLyPositive + "]";
	}
}