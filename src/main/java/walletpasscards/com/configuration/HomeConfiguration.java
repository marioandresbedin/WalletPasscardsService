package walletpasscards.com.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import walletpasscards.com.controller.SocioController;
import walletpasscards.com.model.Socio;
import walletpasscards.com.service.SocioService;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "walletpasscards.com")
public class HomeConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private SocioService socioService;

	@Bean
	public SocioService socioService() {
		return socioService;
	}
	
	@Bean(name = "datasource")
	public static DataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl("jdbc:mysql://localhost:3306/walletpa_walletpasses");
			ds.setUsername("walletpa_wallet");
			ds.setPassword("TiesFizzleStropLesson11");
			/* Local ENV
			//local env
			ds.setUrl("jdbc:mysql://localhost:3306/walletpasses");
			ds.setUsername("root");
			ds.setPassword("root");
			*/
		
		return ds;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addAnnotatedClasses(Socio.class);
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactory);
		return tx;
	}

	@Bean
	public SocioController socioController(SocioService socioService) {
		SocioController socioController = new SocioController();
		socioController.setPersonService(socioService);
		return socioController;
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}

	/*
	 * @SuppressWarnings("unused") private Properties hibernateProperties() {
	 * Properties properties = new Properties();
	 * properties.put("jdbc.url","jdbc:mysql://localhost:3306/walletpasscards");
	 * properties.put("jdbc.driverClassName ","hibernate.show_sql");
	 * properties.put("jdbc.username ","root");
	 * properties.put("jdbc.password","");
	 * properties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
	 * properties.put("hibernate.format_sql","true"); return properties; }
	 */

}