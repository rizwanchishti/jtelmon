package com.wrox.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@SuppressWarnings("unused")
public class Bootstrap implements WebApplicationInitializer
{
    @Override
    public void onStartup(ServletContext container) throws ServletException
    {
        container.getServletRegistration("default").addMapping("/resource/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfiguration.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext webContext =
                new AnnotationConfigWebApplicationContext();
        webContext.register(WebServletContextConfiguration.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springWebDispatcher", new DispatcherServlet(webContext)
        );
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(new MultipartConfigElement(
                null, 20_971_520L, 41_943_040L, 512_000
        ));
        dispatcher.addMapping("/");

        AnnotationConfigWebApplicationContext restContext =
                new AnnotationConfigWebApplicationContext();
        restContext.register(RestServletContextConfiguration.class);
        DispatcherServlet servlet = new DispatcherServlet(restContext);
        servlet.setDispatchOptionsRequest(true);
        dispatcher = container.addServlet(
                "springRestDispatcher", servlet
        );
        dispatcher.setLoadOnStartup(2);
        dispatcher.addMapping("/services/Rest/*");
    }
}

/*
    Professional Java for Web Applications
CHAPTER 12  Introducing Spring Framework - 328

Understanding Application Contexts

There are a number of interfaces that extend and classes that implement ApplicationContext:

For programmatically configuring Spring using Java instead of XML, the
AnnotationConfigApplicationContext and AnnotationConfigWebApplicationContext
classes work in standalone and Java EE web applications, respectively.


Bootstrapping Spring Framework

Programmatically Bootstrapping Spring in an Initializer

The org.springframework.web.SpringServletContainerInitializer class implements
ServletContainerInitializer, and because the JAR containing this class includes a service
provider file listing the class’s name, its onStartup method is called when your application starts up.
This class then scans your application for implementations of the org.springframework.web
.WebApplicationInitializer interface and calls the onStartup method of any matching classes
it finds. Within a WebApplicationInitializer implementation class, you can programmatically
configure listeners, Servlets, filters, and more, all without writing a single line of XML. More
important, you can bootstrap Spring from within this class.

 */

