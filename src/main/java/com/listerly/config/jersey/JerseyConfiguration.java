package com.listerly.config.jersey;

import static java.util.logging.Logger.getLogger;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Injector;

public class JerseyConfiguration extends ResourceConfig {
    private final Logger log = getLogger(getClass().getName());

    @Inject
    public JerseyConfiguration(ServiceLocator serviceLocator, ServletContext servletContext) {
    	log.info("Creating JerseyConfiguration");
        packages("com.listerly.resources");
        property("jersey.config.server.mvc.factory.freemarker", TemplateObjectFactory.class);
        register(FreemarkerMvcFeature.class);
//        register(new AbstractBinder() {
//            @Override
//            protected void configure() {
//                //bindFactory(AdminProvider.class).to(Admin.class);
//            }
//        });

        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector((Injector) servletContext.getAttribute(Injector.class.getName()));
    }

}
