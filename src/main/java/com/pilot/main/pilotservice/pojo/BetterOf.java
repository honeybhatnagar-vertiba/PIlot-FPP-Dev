package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class BetterOf implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GrossProfitDollars grossProfitDollars;

	private Volume volume;

	private Margin margin;

	private MixPercentage mixPercentage;

	private BigDecimal buyingPerfActual;

	private BigDecimal buyingPerfTarget;

	private BigDecimal effPumpFeeActual;

	private BigDecimal effPumpFeeTarget;

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

	public MixPercentage getMixPercentage() {
		return mixPercentage;
	}

	public void setMixPercentage(MixPercentage mixPercentage) {
		this.mixPercentage = mixPercentage;
	}

	public BigDecimal getBuyingPerfActual() {
		return buyingPerfActual;
	}

	public void setBuyingPerfActual(BigDecimal buyingPerfActual) {
		this.buyingPerfActual = buyingPerfActual;
	}

	public BigDecimal getBuyingPerfTarget() {
		return buyingPerfTarget;
	}

	public void setBuyingPerfTarget(BigDecimal buyingPerfTarget) {
		this.buyingPerfTarget = buyingPerfTarget;
	}

	public BigDecimal getEffPumpFeeActual() {
		return effPumpFeeActual;
	}

	public void setEffPumpFeeActual(BigDecimal effPumpFeeActual) {
		this.effPumpFeeActual = effPumpFeeActual;
	}

	public BigDecimal getEffPumpFeeTarget() {
		return effPumpFeeTarget;
	}

	public void setEffPumpFeeTarget(BigDecimal effPumpFeeTarget) {
		this.effPumpFeeTarget = effPumpFeeTarget;
	}

	@Override
	public String toString() {
		return "BetterOf [grossProfitDollars=" + grossProfitDollars + ", volume=" + volume + ", margin=" + margin
				+ ", mixPercentage=" + mixPercentage + ", buyingPerfActual=" + buyingPerfActual + ", buyingPerfTarget="
				+ buyingPerfTarget + ", effPumpActual=" + effPumpFeeActual + ", effPumpTarget=" + effPumpFeeTarget + "]";
	}
}