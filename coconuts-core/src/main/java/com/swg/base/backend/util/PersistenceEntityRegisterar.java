package com.swg.base.backend.util;

import org.apache.log4j.Logger;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * Kelas utility yang digunakan untuk meregistasi entity kelas tambahan selain
 * dari entity dalam kelas core ini. Jadi, jika dalam package lain didefinisiin
 * entity kelas baru, seluruh kelas harus diregistrasiin disini.
 *
 * @author zakyalvan
 */
public class PersistenceEntityRegisterar implements PersistenceUnitPostProcessor {
    private Logger logger = Logger.getLogger(getClass());
    private Set<String> additionalEntityClasses = new HashSet<String>();

    public void setAdditionalEntityClasses(Set<String> additionalEntityClasses) {
        this.additionalEntityClasses = additionalEntityClasses;
    }

    @Override
    public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo persistenceUnitInfo) {
        for (String additionalEntityClass : additionalEntityClasses) {
            logger.debug("Register additional entity : " + additionalEntityClass);
            persistenceUnitInfo.addManagedClassName(additionalEntityClass);
        }
    }
}
