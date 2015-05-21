package com.swg.base.backend.entity;

import java.io.Serializable;
import java.util.Date;

public interface SiteReport extends Serializable {
    public static final Integer LOW_PRIORITY = 1;
    public static final Integer NORMAL_PRIORITY = 2;
    public static final Integer HIGH_PRIORITY = 3;

    Integer getId();

    String getContent();

    Integer getPriority();

    void setPriority(Integer priority);

    Date getReportDate();

    Date getVersion();
}
