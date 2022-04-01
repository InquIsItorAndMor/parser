package com.sokrat.sokratparsersj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="com.sokrat.sokratparsersj")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
      /*  @Override
        public void addViewControllers(ViewControllerRegistry registry) {

            registry.addViewController("/v2/api-docs")
                    .setViewName("/documentation/v2/api-docs?group=restful-api");
            
            registry.addViewController("/swagger-resources/configuration/ui")
                    .setViewName("/documentation/swagger-resources/configuration/ui");
            
            registry.addViewController("/swagger-resources/configuration/security")
                    .setViewName("/documentation/swagger-resources/configuration/security");
            
            registry.addViewController("/swagger-resources")
                    .setViewName("/documentation/swagger-resources");
        }*/
        
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	
}
