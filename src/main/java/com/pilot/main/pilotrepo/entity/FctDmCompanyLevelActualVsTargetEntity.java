package com.pilot.main.pilotrepo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FCT_DM_COMPANY_LEVEL_ACTUAL_VS_TARGET", schema = "fpp_dev")
public class FctDmCompanyLevelActualVsTargetEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	FctDmCompanyLevelActualVsTargetId fctDmCompanyLevelActualVsTargetId;

	@Column(name = "DIM_CURRENCY_ID")
	private Long dimCurrencyId;

	@Column(name = "DIM_QUANTITY_CONVERSION_ID")
	private Long dimQuantityConversionId;

	@Column(name = "ACTUAL_GROSS_PROFIT")
	private BigDecimal actualGrossProfit;

	@Column(name = "ACTUAL_GROSS_PROFIT_LY")
	private BigDecimal actualGrossProfitLy;

	@Column(name = "TARGET_GROSS_PROFIT")
	private BigDecimal targetGrossProfit;

	@Column(name = "ACTUAL_VOLUME")
	private BigDecimal actualVolume;

	@Column(name = "ACTUAL_VOLUME_LY")
	private BigDecimal actualVolumeLy;

	@Column(name = "TARGET_VOLUME")
	private BigDecimal targetVolume;

	@Column(name = "ACTUAL_BUYING_PERFORMANCE")
	private BigDecimal actualBuyingPerformance;

	@Column(name = "TARGET_BUYING_PERFORMANCE")
	private BigDecimal targetBuyingPerformance;

	@Column(name = "ACTUAL_EFFECTIVE_PUMP_FEE")
	private BigDecimal actualEffectivePumpFee;

	@Column(name = "TARGET_EFFECTIVE_PUMP_FEE")
	private BigDecimal targetEffectivePumpFee;

	@Column(name = "ACTUAL_NET_PL_MARGIN")
	private BigDecimal actualNetPlMargin;

	@Column(name = "ACTUAL_NET_PL_MARGIN_LY")
	private BigDecimal actualNetPlMarginLy;

	@Column(name = "TARGET_NET_PL_MARGIN")
	private BigDecimal targetNetPlMargin;

	public FctDmCompanyLevelActualVsTargetId getFctDmCompanyLevelActualVsTargetId() {
		return fctDmCompanyLevelActualVsTargetId;
	}

	public void setFctDmCompanyLevelActualVsTargetId(FctDmCompanyLevelActualVsTargetId fctDmCompanyLevelActualVsTargetId) {
		this.fctDmCompanyLevelActualVsTargetId = fctDmCompanyLevelActualVsTargetId;
	}

	public Long getDimCurrencyId() {
		return dimCurrencyId;
	}

	public void setDimCurrencyId(Long dimCurrencyId) {
		this.dimCurrencyId = dimCurrencyId;
	}

	public Long getDimQuantityConversionId() {
		return dimQuantityConversionId;
	}

	public void setDimQuantityConversionId(Long dimQuantityConversionId) {
		this.dimQuantityConversionId = dimQuantityConversionId;
	}

	public BigDecimal getActualGrossProfit() {
		return actualGrossProfit;
	}

	public void setActualGrossProfit(BigDecimal actualGrossProfit) {
		this.actualGrossProfit = actualGrossProfit;
	}

	public BigDecimal getActualGrossProfitLy() {
		return actualGrossProfitLy;
	}

	public void setActualGrossProfitLy(BigDecimal actualGrossProfitLy) {
		this.actualGrossProfitLy = actualGrossProfitLy;
	}

	public BigDecimal getTargetGrossProfit() {
		return targetGrossProfit;
	}

	public void setTargetGrossProfit(BigDecimal targetGrossProfit) {
		this.targetGrossProfit = targetGrossProfit;
	}

	public BigDecimal getActualVolume() {
		return actualVolume;
	}

	public void setActualVolume(BigDecimal actualVolume) {
		this.actualVolume = actualVolume;
	}

	public BigDecimal getActualVolumeLy() {
		return actualVolumeLy;
	}

	public void setActualVolumeLy(BigDecimal actualVolumeLy) {
		this.actualVolumeLy = actualVolumeLy;
	}

	public BigDecimal getTargetVolume() {
		return targetVolume;
	}

	public void setTargetVolume(BigDecimal targetVolume) {
		this.targetVolume = targetVolume;
	}

	public BigDecimal getActualBuyingPerformance() {
		return actualBuyingPerformance;
	}

	public void setActualBuyingPerformance(BigDecimal actualBuyingPerformance) {
		this.actualBuyingPerformance = actualBuyingPerformance;
	}

	public BigDecimal getTargetBuyingPerformance() {
		return targetBuyingPerformance;
	}

	public void setTargetBuyingPerformance(BigDecimal targetBuyingPerformance) {
		this.targetBuyingPerformance = targetBuyingPerformance;
	}

	public BigDecimal getActualEffectivePumpFee() {
		return actualEffectivePumpFee;
	}

	public void setActualEffectivePumpFee(BigDecimal actualEffectivePumpFee) {
		this.actualEffectivePumpFee = actualEffectivePumpFee;
	}

	public BigDecimal getTargetEffectivePumpFee() {
		return targetEffectivePumpFee;
	}

	public void setTargetEffectivePumpFee(BigDecimal targetEffectivePumpFee) {
		this.targetEffectivePumpFee = targetEffectivePumpFee;
	}

	public BigDecimal getActualNetPlMargin() {
		return actualNetPlMargin;
	}

	public void setActualNetPlMargin(BigDecimal actualNetPlMargin) {
		this.actualNetPlMargin = actualNetPlMargin;
	}

	public BigDecimal getActualNetPlMarginLy() {
		return actualNetPlMarginLy;
	}

	public void setActualNetPlMarginLy(BigDecimal actualNetPlMarginLy) {
		this.actualNetPlMarginLy = actualNetPlMarginLy;
	}

	public BigDecimal getTargetNetPlMargin() {
		return targetNetPlMargin;
	}

	public void setTargetNetPlMargin(BigDecimal targetNetPlMargin) {
		this.targetNetPlMargin = targetNetPlMargin;
	}

	@Override
	public String toString() {
		return "FctDmCompanyLevelActualVsTargetEntity [fctDmCompanyLevelActualVsTargetId="
				+ fctDmCompanyLevelActualVsTargetId + ", dimCurrencyId=" + dimCurrencyId + ", dimQuantityConversionId="
				+ dimQuantityConversionId + ", actualGrossProfit=" + actualGrossProfit + ", actualGrossProfitLy="
				+ actualGrossProfitLy + ", targetGrossProfit=" + targetGrossProfit + ", actualVolume=" + actualVolume
				+ ", actualVolumeLy=" + actualVolumeLy + ", targetVolume=" + targetVolume + ", actualBuyingPerformance="
				+ actualBuyingPerformance + ", targetBuyingPerformance=" + targetBuyingPerformance
				+ ", actualEffectivePumpFee=" + actualEffectivePumpFee + ", targetEffectivePumpFee="
				+ targetEffectivePumpFee + ", actualNetPlMargin=" + actualNetPlMargin + ", actualNetPlMarginLy="
				+ actualNetPlMarginLy + ", targetNetPlMargin=" + targetNetPlMargin + "]";
	}
}