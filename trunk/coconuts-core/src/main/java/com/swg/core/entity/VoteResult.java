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
import javax.validation.constraints.NotNull;

/**
 * Entity yang menunjukan data hasil perhitungan pada 
 * masing-masing tempat pemungutan suara (tps). Data inilah yang
 * diupdate oleh pemantau atau saksi dari masing-masing tps.
 * 
 * @author zakyalvan
 */
@Entity
@Table(name="vote_result")
public class VoteResult implements Serializable {
	private static final long serialVersionUID = 1137871712781924646L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	
	@Version
	private Timestamp version;

	public Integer getId() {
		return id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Date getVersion() {
		return version;
	}
}
