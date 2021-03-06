package com.swg.sms;

import com.swg.sms.service.ServiceLifecycleManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/coconuts-config-*.xml");
        applicationContext.getBean(ServiceLifecycleManager.class).startService();
    }
}
