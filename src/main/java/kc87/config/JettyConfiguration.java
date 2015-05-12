package kc87.config;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;


@Configuration
public class JettyConfiguration
{

   @Autowired
   private ApplicationContext appContext;

   private int httpPort = 8080;


   @Bean
   public ServletHolder dispatcherServlet()
   {
      AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
      ctx.register(MvcConfiguration.class);
      DispatcherServlet servlet = new DispatcherServlet(ctx);
      ServletHolder holder = new ServletHolder("dispatcher-servlet", servlet);
      holder.setInitOrder(1);
      return holder;
   }

   @Bean
   public ServletContextHandler servletContext() throws IOException
   {
      ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);

      AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
      webApplicationContext.setParent(appContext);
      webApplicationContext.refresh();

      handler.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE,
              webApplicationContext);

      handler.setContextPath("/");
      handler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
      //handler.setWelcomeFiles(new String[]{"index.html"});
      handler.addServlet(dispatcherServlet(), "/*");

      return handler;
   }


   @Bean(initMethod = "start", destroyMethod = "stop")
   public Server jettyServer() throws Exception
   {
      Server server = new Server();

      ServerConnector httpConnector = new ServerConnector(server);

      httpConnector.setPort(httpPort);
      server.addConnector(httpConnector);
      server.setHandler(servletContext());

      return server;
   }
}
