package com.mygaienko.common.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by enda1n on 11.06.2017.
 */
public class VelocityTest {

    @Test
    public void test() throws Exception {
        Properties velocityProperties = new Properties();
//        velocityProperties.setProperty("resource.loader", "classpath");
//        velocityProperties.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        velocityProperties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH,
//                "C:\\dev\\workspaces\\java\\practice\\practice-common\\src\\test\\resource");

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init(velocityProperties);

        Template template = velocityEngine.getTemplate("testvelocity.vm");

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("name", "Dmytro");

        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        System.out.println(stringWriter.toString());
    }
}
