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
@Table(name = "fuel_margin_info")
public class FuelMarginEntity implements Serializable {
	
	
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
	
	
	@Column(name = "net")
	private BigDecimal net;
	
	@Column(name = "gross")
	private BigDecimal gross;
	
	@Column(name = "def")
	private BigDecimal def;
	
	@Column(name = "so_ly1")
	private BigDecimal so_ly1;
	
	@Column(name = "so_ly2")
	private BigDecimal so_ly2 ;
	
	@Column(name = "so_ly3")
	private BigDecimal so_ly3 ;

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

	public BigDecimal getNet() {
		return net;
	}

	public void setNet(BigDecimal net) {
		this.net = net;
	}

	public BigDecimal getGross() {
		return gross;
	}

	public void setGross(BigDecimal gross) {
		this.gross = gross;
	}

	public BigDecimal getDef() {
		return def;
	}

	public void setDef(BigDecimal def) {
		this.def = def;
	}

	public BigDecimal getSo_ly1() {
		return so_ly1;
	}

	public void setSo_ly1(BigDecimal so_ly1) {
		this.so_ly1 = so_ly1;
	}

	public BigDecimal getSo_ly2() {
		return so_ly2;
	}

	public void setSo_ly2(BigDecimal so_ly2) {
		this.so_ly2 = so_ly2;
	}

	public BigDecimal getSo_ly3() {
		return so_ly3;
	}

	public void setSo_ly3(BigDecimal so_ly3) {
		this.so_ly3 = so_ly3;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FuelMarginEntity [id=" + id + ", fuel_type=" + fuel_type + ", net=" + net + ", gross=" + gross
				+ ", def=" + def + ", so_ly1=" + so_ly1 + ", so_ly2=" + so_ly2 + ", so_ly3=" + so_ly3 + "]";
	}
	


}
