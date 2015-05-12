package kc87.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"kc87.web"})
public class MvcConfiguration extends WebMvcConfigurerAdapter
{

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry)
   {
      registry.addResourceHandler("css/**").addResourceLocations("css/");
      registry.addResourceHandler("js/**").addResourceLocations("js/");
   }

   @Bean
   public ServletContextTemplateResolver thymeleafTemplateResolver()
   {
      ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
      resolver.setPrefix("/WEB-INF/thymeleaf/");
      resolver.setTemplateMode("HTML5");
      resolver.setCacheable(false);
      resolver.setCharacterEncoding("UTF-8");
      return resolver;
   }

   @Bean
   public SpringTemplateEngine thymeleafTemplateEngine()
   {
      SpringTemplateEngine engine = new SpringTemplateEngine();
      engine.setTemplateResolver(thymeleafTemplateResolver());
      return engine;
   }

   @Bean
   public ThymeleafViewResolver thymeleafViewResolver()
   {
      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
      resolver.setTemplateEngine(thymeleafTemplateEngine());
      return resolver;
   }

}
