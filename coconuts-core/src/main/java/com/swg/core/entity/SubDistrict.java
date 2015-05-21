package com.swg.core.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Object entity yang mewakili kecamatan.
 *
 * @author zakyalvan
 */
@Entity
@DiscriminatorValue("subdistrict")
public class SubDistrict extends Region {
    private static final long serialVersionUID = 1224144372504336230L;

}
