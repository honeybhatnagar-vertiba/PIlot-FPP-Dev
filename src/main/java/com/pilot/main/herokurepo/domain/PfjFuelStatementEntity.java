package com.pilot.main.herokurepo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pfj_fuel_statement ")
public class PfjFuelStatementEntity implements Serializable{

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
	
	
	@Column(name = "percent1")
	private String percent1 ;
	
	@Column(name = "amount1")
	private String amount1 ;
	
	@Column(name = "percent2")
	private String percent2 ;
	
	@Column(name = "amount2")
	private String amount2;
	
	@Column(name = "percent_gallons")
	private String percent_gallons;
	
	@Column(name = "amount_gallons")
	private String amount_gallons;
	
	@Column(name = "percent_gallons1")
	private String percent_gallons1;
	
	@Column(name = "amount_gallons1")
	private String amount_gallons1;
	
	@Column(name = "actual")
	private String actual;
	
	@Column(name = "target")
	private String target;
	
	@Column(name = "vs_ly")
	private String vs_ly;

	@Override
	public String toString() {
		return "PfjFuelStatementEntity [id=" + id + ", industry=" + industry + ", percent1=" + percent1 + ", amount1="
				+ amount1 + ", percent2=" + percent2 + ", amount2=" + amount2 + ", percent_gallons=" + percent_gallons
				+ ", amount_gallons=" + amount_gallons + ", percent_gallons1=" + percent_gallons1 + ", amount_gallons1="
				+ amount_gallons1 + ", actual=" + actual + ", target=" + target + ", vs_ly=" + vs_ly + "]";
	}

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

	public String getPercent1() {
		return percent1;
	}

	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}

	public String getAmount1() {
		return amount1;
	}

	public void setAmount1(String amount1) {
		this.amount1 = amount1;
	}

	public String getPercent2() {
		return percent2;
	}

	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}

	public String getAmount2() {
		return amount2;
	}

	public void setAmount2(String amount2) {
		this.amount2 = amount2;
	}

	public String getPercent_gallons() {
		return percent_gallons;
	}

	public void setPercent_gallons(String percent_gallons) {
		this.percent_gallons = percent_gallons;
	}

	public String getAmount_gallons() {
		return amount_gallons;
	}

	public void setAmount_gallons(String amount_gallons) {
		this.amount_gallons = amount_gallons;
	}

	public String getPercent_gallons1() {
		return percent_gallons1;
	}

	public void setPercent_gallons1(String percent_gallons1) {
		this.percent_gallons1 = percent_gallons1;
	}

	public String getAmount_gallons1() {
		return amount_gallons1;
	}

	public void setAmount_gallons1(String amount_gallons1) {
		this.amount_gallons1 = amount_gallons1;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getVs_ly() {
		return vs_ly;
	}

	public void setVs_ly(String vs_ly) {
		this.vs_ly = vs_ly;
	}
	
	
	
	
	
}
