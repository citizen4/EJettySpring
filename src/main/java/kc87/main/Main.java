package kc87.main;

import kc87.config.RootConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main
{
   public static void main(String[] args) throws Exception
   {
      AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();

      appContext.registerShutdownHook();
      appContext.register(RootConfiguration.class);
      appContext.refresh();





   }

}
