package com.swg.base.backend.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("village")
public class Village extends Region {
    public static final Integer TYPE_KELURAHAN = 3;
    public static final Integer TYPE_DESA = 4;
    private static final long serialVersionUID = 7706869318681425849L;
    @Column(name = "type")
    private Integer type = TYPE_KELURAHAN;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
