package com.swg.base.backend.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "site_report")
public class SiteReportBean implements SiteReport {
    private static final long serialVersionUID = 2807982298705946713L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "priority")
    private Integer priority = NORMAL_PRIORITY;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "report_date")
    private Date reportDate;

    @Version
    @Column(name = "version")
    private Timestamp version;

    public SiteReportBean() {
    }

    public SiteReportBean(String content, Date reportDate) {
        this.content = content;
        this.reportDate = reportDate;
    }

    public SiteReportBean(String content, Integer priority, Date reportDate) {
        this.content = content;
        this.priority = priority;
        this.reportDate = reportDate;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public Date getReportDate() {
        return reportDate;
    }

    @Override
    public Date getVersion() {
        return version;
    }
}
