package com.pilot.main.pilotservice.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Volume implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8752721638535299465L;

	private BigDecimal vsTgLeft;
	
	private Boolean vsTgLeftPositive;
	
	private BigDecimal vsTgRight;
	
	private BigDecimal vsLyLeft;
	
	private Boolean vsLyLeftPositive;
	
	private BigDecimal vsLyRight;

	public BigDecimal getVsTgLeft() {
		return vsTgLeft;
	}

	public void setVsTgLeft(BigDecimal vsTgLeft) {
		this.vsTgLeft = vsTgLeft.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public Boolean getVsTgLeftPositive() {
		return vsTgLeftPositive;
	}

	public void setVsTgLeftPositive(Boolean vsTgLeftPositive) {
		this.vsTgLeftPositive = vsTgLeftPositive;
		setVsTgLeft(getVsTgLeft().negate());
	}

	public BigDecimal getVsTgRight() {
		return vsTgRight;
	}

	public void setVsTgRight(BigDecimal vsTgRight) {
		this.vsTgRight = vsTgRight.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public BigDecimal getVsLyLeft() {
		return vsLyLeft;
	}

	public void setVsLyLeft(BigDecimal vsLyLeft) {
		this.vsLyLeft = vsLyLeft.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	public Boolean getVsLyLeftPositive() {
		return vsLyLeftPositive;
	}

	public void setVsLyLeftPositive(Boolean vsLyLeftPositive) {
		this.vsLyLeftPositive = vsLyLeftPositive;
		setVsLyLeft(getVsLyLeft().negate());
	}

	public BigDecimal getVsLyRight() {
		return vsLyRight;
	}

	public void setVsLyRight(BigDecimal vsLyRight) {
		this.vsLyRight = vsLyRight.divide(BigDecimal.valueOf(1000000), 2, RoundingMode.HALF_UP);
	}

	@Override
	public String toString() {
		return "Volume [vsTgLeft=" + vsTgLeft + ", vsTgLeftPositive=" + vsTgLeftPositive + ", vsTgRight=" + vsTgRight
				+ ", vsLyLeft=" + vsLyLeft + ", vsLyLeftPositive=" + vsLyLeftPositive + ", vsLyRight=" + vsLyRight
				+ "]";
	}
}