package com.swg.sms.action;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.HashSet;
import java.util.Set;

/**
 * Pre process bean yang terdaftar di application context, meliputi pengecekan apakah
 * ada action dengan keyword yang sama tidak. Untuk menghindari ambigu dalam pemrosesan
 * action, maka tidak boleh ada action dengan keyword yang sama.
 *
 * @author zakyalvan
 */
public class ActionBeanPostProcessor implements BeanPostProcessor {
    private Logger logger = Logger.getLogger(getClass());
    private Set<Keyword> usedKeywords = new HashSet<Keyword>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Action.class.isAssignableFrom(bean.getClass())) {
            logger.debug("Action bean found.");
            Keyword actionKeyword = ((Action) bean).getKeyword();
            for (Keyword keyword : usedKeywords) {
                if (actionKeyword.tryMatching(keyword.getValue())) {
                    logger.error("Bean action dengan keyword yang sama ditemukan. Untuk mengindari ambigu, keyword harus uniq untuk tiap action.");
                    throw new BeanCreationException("Bean action dengan keyword yang sama ditemukan. Untuk mengindari ambigu, keyword harus uniq untuk tiap action.");
                }
            }
            usedKeywords.add(actionKeyword);
        }
        return bean;
    }
}
