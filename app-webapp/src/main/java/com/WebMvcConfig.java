package com;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class.getName());
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"/ui-build/styles/", "/ui-build/scripts/", 
		"/ui-build/assets/", "/ui-build/fonts/",
		"/META-INF/ui-build/", "/ui-build/"
		};
	
	@Autowired
	private Environment env;

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		LOGGER.info("Mapping scripts, styles, fonts and assets to resource folders");
		
		if (!registry.hasMappingForPattern("/styles/**")) {
			registry.addResourceHandler("/styles/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		 }
		else {
			LOGGER.warn("Failed to map styles");
		}
		if (!registry.hasMappingForPattern("/scripts/**")) {
			registry.addResourceHandler("/scripts/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		else {
			LOGGER.warn("Failed to map scripts");
		}
		if (!registry.hasMappingForPattern("/assets/**")) {
			registry.addResourceHandler("/assets/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		else {
			LOGGER.warn("Failed to map assets");
		}
		if (!registry.hasMappingForPattern("/fonts/**")) {
			registry.addResourceHandler("/fonts/**")
				.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
		else {
			LOGGER.warn("Failed to map fonts");
		}
		
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/");
		//resolver.setPrefix("/ui-build/");
		//resolver.setSuffix(".html");
		return resolver;
	}

    // TODO dataSource...
//    @Bean
//    public DataSource dataSource() {
//       BasicDataSource dataSource = new BasicDataSource();
//       
//       dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
//       dataSource.setUrl(env.getProperty("jdbc.url"));
//       
//       dataSource.setUsername(env.getProperty("jdbc.username"));
//
//       LOGGER.debug("Ready to decrypt dbpass");
//       dataSource.setPassword(EncryptionHelper.decryptPropertyString("jdbc.password", env.getProperty("jdbc.password")));
//
//       dataSource.setMaxWaitMillis(10000);
//       dataSource.setMaxTotal(50);
//       //dataSource.setMaxWaitMillis(env.getPropertyAsClass("jdbc.password", Long.class));
//       //dataSource.setMaxTotal(50);
//       dataSource.setValidationQuery("SELECT 1");
//       dataSource.setTestOnBorrow(true);
//       return dataSource;
//    }
    
/*    @Autowired
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
    	DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    	dataSourceTransactionManager.setDataSource(dataSource);
    	return dataSourceTransactionManager;
    }*/
 
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/error").setViewName(Constants.VIEW_GENERIC_ERROR);
        //registry.addViewController("/error").setViewName("ui-build/generic-error.html");
		//registry.addViewController("/").setViewName(Constants.VIEW_LANDING_PAGE);
		registry.addViewController("/").setViewName("ui-build/index.html");
		registry.addViewController("/error").setViewName("jsp/dynamic-error.jsp");
		//registry.addViewController("/error").setViewName("jsp/session-expired-error.jsp");
		registry.addViewController("/dynamic-error").setViewName("jsp/dynamic-error.jsp");
		registry.addViewController("/error9").setViewName("jsp/dynamic-error.jsp");
		registry.addViewController("/dynamic-error2").setViewName("jsp/dynamic-error.jsp");
		registry.addViewController("/dynamic-error4").setViewName("jsp/dynamic-error.jsp");
		registry.addViewController("/session-expired-error-page").setViewName("jsp/session-expired-error.jsp");
		registry.addViewController("/logged-out").setViewName("jsp/logged-out.jsp");
		registry.addViewController("/logged-out2").setViewName("jsp/logged-out2.jsp");
		registry.addViewController("/logged-out-fail").setViewName("jsp/logged-out-fail.jsp");
		registry.addViewController("/login-fail").setViewName("jsp/login-fail.jsp");
	}
	
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {		
	//	httpMessageConverters.add(new MappingJackson2HttpMessageConverter());        
     //   httpMessageConverters.add(new CsvMessageConverter());
        //super.configureMessageConverters(httpMessageConverters);
    }
	
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOrigins("http://localhost:3000")
//			.allowedMethods("PUT", "DELETE")
//			.allowedHeaders("header1", "header2", "header3")
//			.exposedHeaders("header1", "header2")
//			.allowedOrigins("http://localhost:9000")
//			.allowCredentials(false).maxAge(3600);
//	}
	
}