package kc87.service;

import org.springframework.stereotype.Service;


@Service
public class EchoService
{
   private static EchoService instance = null;

   public EchoService()
   {
      /**
       * This is here to detect if we get instantiated 2x.  While starting
       * with SpringMVC with Jetty embedded I found that I could get into
       * a case where the web application would recreate all the beans
       * already created outside of the web app context.  The component
       * scan filters should prevent this.
       */
      if (instance != null) {
         throw new RuntimeException("DummyServer has already been instantiated.");
      }
      instance = this;
   }

   public String echo(String msg)
   {
      return msg;
   }

}
