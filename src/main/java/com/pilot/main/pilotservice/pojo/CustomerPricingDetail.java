package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerPricingDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5407225766790089571L;

	private String temporalPeriod;

	// private LocalDate lastClosedPeriod;

	private PFJTotal pFJTotal;

	private BetterOf betterOf;
	
	private TotalRetail totalRetail;
	
	private RetailMinus retailMinus;
	
	private Funded funded;
	
	private CCC ccc;

	public String getTemporalPeriod() {
		return temporalPeriod;
	}

	public void setTemporalPeriod(String temporalPeriod) {
		this.temporalPeriod = temporalPeriod;
	}

	/*public LocalDate getLastClosedPeriod() {
		return lastClosedPeriod;
	}

	public void setLastClosedPeriod(LocalDate lastClosedPeriod) {
		this.lastClosedPeriod = lastClosedPeriod;
	}*/

	public PFJTotal getpFJTotal() {
		return pFJTotal;
	}

	public void setpFJTotal(PFJTotal pFJTotal) {
		this.pFJTotal = pFJTotal;
	}

	public BetterOf getBetterOf() {
		return betterOf;
	}

	public void setBetterOf(BetterOf betterOf) {
		this.betterOf = betterOf;
	}

	public TotalRetail getTotalRetail() {
		return totalRetail;
	}

	public void setTotalRetail(TotalRetail totalRetail) {
		this.totalRetail = totalRetail;
	}

	public RetailMinus getRetailMinus() {
		return retailMinus;
	}

	public void setRetailMinus(RetailMinus retailMinus) {
		this.retailMinus = retailMinus;
	}

	public Funded getFunded() {
		return funded;
	}

	public void setFunded(Funded funded) {
		this.funded = funded;
	}

	public CCC getCcc() {
		return ccc;
	}

	public void setCcc(CCC ccc) {
		this.ccc = ccc;
	}

	@Override
	public String toString() {
		return "CustomerPricingDetail [temporalPeriod=" + temporalPeriod /*+ ", lastClosedPeriod=" + lastClosedPeriod*/
				+ ", pFJTotal=" + pFJTotal + ", betterOf=" + betterOf + ", totalRetail=" + totalRetail
				+ ", retailMinus=" + retailMinus + ", funded=" + funded + ", ccc=" + ccc + "]";
	}

}