package kc87.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({kc87.config.JettyConfiguration.class})
@ComponentScan(basePackages = {"kc87.service, kc87.repository"})
public class RootConfiguration
{
}
