package com.listerly.config.jersey;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import com.google.common.collect.Lists;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;

public class TemplateObjectFactory extends Configuration {

    @Inject
    private ServletContext servletContext;

    public TemplateObjectFactory() {
        // Create different loaders.
        final List<TemplateLoader> loaders = Lists.newArrayList();
        if (servletContext != null) {
            loaders.add(new WebappTemplateLoader(servletContext));
        }
        loaders.add(new ClassTemplateLoader(this.getClass(), "/"));

        setTemplateLoader(new MultiTemplateLoader(loaders.toArray(new TemplateLoader[loaders.size()])));
    }
}
