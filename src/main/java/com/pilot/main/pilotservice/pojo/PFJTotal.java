package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class PFJTotal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3422873344242250017L;

	private GrossProfitDollars grossProfitDollars;
	
	private Volume volume;
	
	private Margin margin;
	
	private BigDecimal totalGAL;
	
	private BigDecimal totalTarget;

	public GrossProfitDollars getGrossProfitDollars() {
		return grossProfitDollars;
	}

	public void setGrossProfitDollars(GrossProfitDollars grossProfitDollars) {
		this.grossProfitDollars = grossProfitDollars;
	}

	public Volume getVolume() {
		return volume;
	}

	public void setVolume(Volume volume) {
		this.volume = volume;
	}

	public Margin getMargin() {
		return margin;
	}

	public void setMargin(Margin margin) {
		this.margin = margin;
	}

	public BigDecimal getTotalGAL() {
		return totalGAL;
	}

	public void setTotalGAL(BigDecimal totalGAL) {
		this.totalGAL = totalGAL;
	}

	public BigDecimal getTotalTarget() {
		return totalTarget;
	}

	public void setTotalTarget(BigDecimal totalTarget) {
		this.totalTarget = totalTarget;
	}

	@Override
	public String toString() {
		return "PFJTotal [grossProfitDollars=" + grossProfitDollars + ", volume=" + volume + ", margin=" + margin
				+ ", totalGAL=" + totalGAL + ", totalTarget=" + totalTarget + "]";
	}
}