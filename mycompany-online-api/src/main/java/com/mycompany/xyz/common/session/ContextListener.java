/*
 \* Copyright  ©  Mycompany Ltd. All Rights Reserved.
 */
package com.mycompany.xyz.common.session;

import java.io.File;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * ContextListener class - Is invoked when the application starts or stops
 */
@Component
public final class ContextListener implements ServletContextListener, HttpSessionListener {

    private String envName;
    
    public ContextListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        ServletContext context = event.getServletContext();
        File envDir = (File) context.getAttribute("javax.servlet.context.tempdir");

        //to avoid netbeans picking up com.sun.xml.internal.bind.api.JAXBRIContex .TODO look for a better solution.
        System.setProperty("javax.xml.bind.JAXBContext",
                "com.sun.xml.internal.bind.v2.ContextFactory");

        envName = envDir.getName();

        if (envName.equals("")) {
            System.out.println("***ERROR***Context listener unable to establish application context");
        } else {

            context.setAttribute("StartDate", new Date());
            context.setAttribute("EnvName", envName);

            //set cookie policy system wide
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (envName == null || envName.equals("")) {
            System.out.println("***ERROR***Context listener unable to establish application context");
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }

}
