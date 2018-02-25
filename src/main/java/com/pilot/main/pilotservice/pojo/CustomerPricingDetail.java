package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;

public class CustomerPricingDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String temporalPeriod;

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
		return "CustomerPricingDetails [temporalPeriod=" + temporalPeriod + ", pFJTotal=" + pFJTotal + ", betterOf="
				+ betterOf + ", totalRetail=" + totalRetail + ", retailMinus=" + retailMinus + ", funded=" + funded
				+ ", ccc=" + ccc + "]";
	}
}