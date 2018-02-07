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
@Table(name = "customer_pricing_totals")
public class CustomerPricingEntity implements Serializable{

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
	
	@Column(name = "category")
	private String category ;
	
	@Column(name = "total_gallons")
	private BigDecimal total_gallons;
	
	@Column(name = "ll_lw_gallons")
	private BigDecimal ll_lw_gallons;
	
	@Column(name = "sd_ly_gallons")
	private BigDecimal sd_ly_gallons;
	
	@Column(name = "mtd_ly_gallons")
	private BigDecimal mtd_ly_gallons;
	
	@Column(name = "yest")
	private BigDecimal yest ;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public BigDecimal getTotal_gallons() {
		return total_gallons;
	}

	public void setTotal_gallons(BigDecimal total_gallons) {
		this.total_gallons = total_gallons;
	}

	public BigDecimal getLl_lw_gallons() {
		return ll_lw_gallons;
	}

	public void setLl_lw_gallons(BigDecimal ll_lw_gallons) {
		this.ll_lw_gallons = ll_lw_gallons;
	}

	public BigDecimal getSd_ly_gallons() {
		return sd_ly_gallons;
	}

	public void setSd_ly_gallons(BigDecimal sd_ly_gallons) {
		this.sd_ly_gallons = sd_ly_gallons;
	}

	public BigDecimal getMtd_ly_gallons() {
		return mtd_ly_gallons;
	}

	public void setMtd_ly_gallons(BigDecimal mtd_ly_gallons) {
		this.mtd_ly_gallons = mtd_ly_gallons;
	}

	public BigDecimal getYest() {
		return yest;
	}

	public void setYest(BigDecimal yest) {
		this.yest = yest;
	}

	@Override
	public String toString() {
		return "CustomerPricingEntity [id=" + id + ", fuel_type=" + fuel_type + ", category=" + category
				+ ", total_gallons=" + total_gallons + ", ll_lw_gallons=" + ll_lw_gallons + ", sd_ly_gallons="
				+ sd_ly_gallons + ", mtd_ly_gallons=" + mtd_ly_gallons + ", yest=" + yest + "]";
	}
}
