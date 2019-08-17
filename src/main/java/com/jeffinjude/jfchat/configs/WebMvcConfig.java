package com.jeffinjude.jfchat.configs;

import java.io.IOException;
import java.net.ServerSocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The configuration class of spring.
 * @author JeffinJude
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"com.jeffinjude.jfchat"})
@ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * This method is included so that spring mvc delegates handle of static resources to 
	 * default servlet instead of dispatcher servlet. Dispatcher servlet can't handle static
	 * resources like 'localhost/hello.txt'. When dispatcher servlet fails to serve it spring
	 * delegates it to default servlet. First precedence is for dispatcher servlet, then for
	 * default servlet of servlet container. 
	 * 
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	/**
	 * Tell internal view resolver of spring mvc where the view pages are.
	 * @return InternalResourceViewResolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("viewpages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * The modelandview object of spring mvc.
	 * @return ModelAndView
	 */
	@Bean
	public ModelAndView modelAndview() {
		return new ModelAndView();
	}
	
	/**
	 * The RedirectStrategy object for redirect request functionality.
	 * @return DefaultRedirectStrategy
	 */
	@Bean
	public RedirectStrategy redirectStrategy() {
		return new DefaultRedirectStrategy();
	}
	
	/**
	 * The ServerSocket object for chat server socket.
	 * @return ServerSocket
	 */
	@Bean
	public ServerSocket serverSocket() throws IOException {
		return new ServerSocket(8818); //TODO: Move server port to property file.
	}
}
