package com.pilot.main.herokurepo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pilot_fuel_industry_stats")
public class PilotFuelIndustryStatsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "id")
	private Long id;
	
	@Column(name = "industry")
	private String industry ;
	
	
	@Column(name = "daily")
	private BigDecimal daily;
	
	@Column(name = "monthly_avg")
	private BigDecimal monthly_avg;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public BigDecimal getDaily() {
		return daily;
	}

	public void setDaily(BigDecimal daily) {
		this.daily = daily;
	}

	public BigDecimal getMonthly_avg() {
		return monthly_avg;
	}

	public void setMonthly_avg(BigDecimal monthly_avg) {
		this.monthly_avg = monthly_avg;
	}

	@Override
	public String toString() {
		return "PilotFuelIndustryStatsEntity [id=" + id + ", industry=" + industry + ", daily=" + daily
				+ ", monthly_avg=" + monthly_avg + "]";
	}

	
	
	
	
}
