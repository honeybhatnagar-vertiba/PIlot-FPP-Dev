package com.pilot.main.awsrepo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuel_comparison_info")
public class FuelComparisonEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)  
	@Column(name = "id")
	private Long id;
	
	@Column(name = "fuel_type")
	private String fuel_type ;
	
	
	
	@Column(name = "loves")
	private BigDecimal loves;
	
	@Column(name = "ta")
	private BigDecimal ta;
	
	@Column(name = "petro")
	private BigDecimal petro;
	
	@Column(name = "independent")
	private BigDecimal independent;
	
	@Column(name = "quicktrip")
	private BigDecimal quicktrip ;
	
	@Column(name = "safeway")
	private BigDecimal safeway ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuel_type() {
		return fuel_type;
	}

	public void setFuel_type(String fuel_type) {
		this.fuel_type = fuel_type;
	}

	public BigDecimal getLoves() {
		return loves;
	}

	public void setLoves(BigDecimal loves) {
		this.loves = loves;
	}

	public BigDecimal getTa() {
		return ta;
	}

	public void setTa(BigDecimal ta) {
		this.ta = ta;
	}

	public BigDecimal getPetro() {
		return petro;
	}

	public void setPetro(BigDecimal petro) {
		this.petro = petro;
	}

	public BigDecimal getIndependent() {
		return independent;
	}

	public void setIndependent(BigDecimal independent) {
		this.independent = independent;
	}

	public BigDecimal getQuicktrip() {
		return quicktrip;
	}

	public void setQuicktrip(BigDecimal quicktrip) {
		this.quicktrip = quicktrip;
	}

	public BigDecimal getSafeway() {
		return safeway;
	}

	public void setSafeway(BigDecimal safeway) {
		this.safeway = safeway;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FuelComparisonEntity [id=" + id + ", fuel_type=" + fuel_type + ", loves=" + loves + ", ta=" + ta
				+ ", petro=" + petro + ", independent=" + independent + ", quicktrip=" + quicktrip + ", safeway="
				+ safeway + "]";
	}
	
	
	

}
