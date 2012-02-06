package com.swg.core.entity;

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
public class VoteObserverBean implements VoteObserver {
	private static final long serialVersionUID = -918974117761313992L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@NotBlank
	@Column(name="full_name")
	private String fullName;
	
	@NotBlank
	@Pattern(regexp="^((\\+62)|0)\\d{7,11}+")
	@Column(name="cellular_number", unique=true, length=15)
	private String cellularNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registered_date")
	private Date registeredDate;
	
	@Column(name="verified")
	private boolean verified = false;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="verified_date")
	private Date verifiedDate;
	
	@Column(name="deleted")
	private boolean deleted = false;
	
	@Version
	@Column(name="version")
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

	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Date getVerifiedDate() {
		return verifiedDate;
	}
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
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
