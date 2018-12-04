package gr.hua.dit.ds.springdemo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("gr.hua.dit.ds.springdemo")
public class DemoAppConfig implements WebMvcConfigurer {

}
