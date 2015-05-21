package com.swg.base.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Entity yang menunjukan data hasil perhitungan pada
 * masing-masing tempat pemungutan suara (tps). Data inilah yang
 * diupdate oleh pemantau atau saksi dari masing-masing tps.
 *
 * @author zakyalvan
 */
@Entity
@Table(name = "vote_result")
public class VoteResult implements Serializable {
    private static final long serialVersionUID = 1137871712781924646L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
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
