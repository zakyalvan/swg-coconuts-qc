package com.swg.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Orang yang menjadi pemantau atau saksi dalam proses pemungutan suara
 * sekaligus melaporkan hasil perhitungan suara pada tempat pemungutan suara
 * yang dipantaunya.
 * 
 * @author zakyalvan
 */
@Entity
@Table(name="vote_observer")
public class VoteObserver implements Serializable {
	private static final long serialVersionUID = -918974117761313992L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Column
	private String fullName;
	
	@NotBlank
	@Pattern(regexp="^((\\+62)|0)\\d{7,11}+")
	@Column(unique=true, length=15)
	private String cellularNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date registeredDate;
	
	@Column(name="is_deleted")
	private boolean deleted = false;
	
	@Version
	private Timestamp version;

	public Integer getId() {
		return id;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCellularNumber() {
		return cellularNumber;
	}
	public void setCellularNumber(String cellularNumber) {
		this.cellularNumber = cellularNumber;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public Date getVersion() {
		return version;
	}
}
