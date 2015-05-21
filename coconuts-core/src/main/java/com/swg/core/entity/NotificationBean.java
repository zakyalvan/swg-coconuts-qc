package com.swg.core.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Implementasi persistable notification entity.
 *
 * @author zakyalvan
 */
@Entity
@Table(name = "notification")
public class NotificationBean implements Notification {
    private static final long serialVersionUID = -2240537609889622814L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;

    @NotNull
    @Column(name = "code")
    protected Integer code;

    @Version
    @Column(name = "version")
    protected Timestamp version;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public Date getVersion() {
        return version;
    }
}
