package com.swg.core.entity;

import java.io.Serializable;
import java.util.Date;

public interface VoteObserver extends Serializable {
	Integer getId();
	
	String getFullName();

	String getCellularNumber();
	void setCellularNumber(String cellularNumber);

	Date getRegisteredDate();
	void setRegisteredDate(Date registeredDate);

	boolean isVerified();
	void setVerified(boolean verified);

	Date getVerifiedDate();

	boolean isDeleted();
	void setDeleted(boolean deleted);
	
	Date getVersion();
}
