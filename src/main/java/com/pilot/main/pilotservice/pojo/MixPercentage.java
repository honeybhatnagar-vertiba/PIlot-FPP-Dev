package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MixPercentage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3958241561608578208L;

	private BigDecimal mixActual;
	
	private BigDecimal mixTarget;
	
	private BigDecimal mixVsLy;
	
	private Boolean mixVsLyPositive;

	public BigDecimal getMixActual() {
		return mixActual;
	}

	public void setMixActual(BigDecimal mixActual) {
		this.mixActual = mixActual.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public BigDecimal getMixTarget() {
		return mixTarget;
	}

	public void setMixTarget(BigDecimal mixTarget) {
		this.mixTarget = mixTarget.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public BigDecimal getMixVsLy() {
		return mixVsLy;
	}

	public void setMixVsLy(BigDecimal mixVsLy) {
		this.mixVsLy = mixVsLy.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public Boolean getMixVsLyPositive() {
		return mixVsLyPositive;
	}

	public void setMixVsLyPositive(Boolean mixVsLyPositive) {
		this.mixVsLyPositive = mixVsLyPositive;
		setMixVsLy(getMixVsLy().negate());
	}

	@Override
	public String toString() {
		return "MixPercentage [mixActual=" + mixActual + ", mixTarget=" + mixTarget + ", mixVsLy=" + mixVsLy
				+ ", mixVsLyPositive=" + mixVsLyPositive + "]";
	}
}