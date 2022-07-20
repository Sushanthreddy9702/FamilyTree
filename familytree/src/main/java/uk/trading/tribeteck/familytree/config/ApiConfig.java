/**
 * 
 */
package uk.trading.tribeteck.familytree.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * @author
 *
 */

@Configuration
public class ApiConfig<Docket> {
	
	@Bean
	public OpenAPI customOpenAPI() {
			Contact contact = new Contact();
			contact.setEmail("user@gmail.com");
			contact.setName("User");
			contact.setUrl("User Profile Link");
			return new OpenAPI().info(
					new Info().title("Family Tree API for UK Trading Tribe Tech Test")
					.version("1.0.0")
					.description("Create an application to model a very basic family tree (feel free to use a framework if you wish). The application must fulfil the following core requirements:\r\n"
							+ "\r\n"
							+ "    Initialise the family tree with 2 members, 1 male, 1 female\r\n"
							+ "    Add a child, each child must have 1 father and 1 mother. Parents must be a current family member\r\n"
							+ "    List both parents for any given family member\r\n"
							+ "    List children for any given family member\r\n"
							+ "    List all descendants for any given family member\r\n"
							+ "    List all ancestors for any given family member\r\n"
							+ "    These operations should be exposed through a REST API\r\n"
							+ "\r\n"
							+ "Please keep it simple. If you require any additional information, please ask. We would rather you asked obvious questions and got it right rather than not ask questions and get it wrong. (Asking good questions is seen as a positive!)"
							+ "Review Criteria:"
							+ "All tech test submissions (for any of our tech tests) are anonymised and reviewed using a standard template.\r\n"
							+ "\r\n"
							+ "At a high level we will be looking for:\r\n"
							+ "\r\n"
							+ "    Readability: clear naming conventions, clear documentation where necessary\r\n"
							+ "    Good understanding of the programming language and its features\r\n"
							+ "    Automated tests\r\n"
							+ "    Simplicity\r\n"
							+ "    Clear instructions on how to build, test & run the application using the build framework of your choice\r\n"
							+ "    Logging\r\n"
							+ "    Error handling\r\n"
							+ "    Clearly described regular commits visible through VCS\r\n"
							+ "")
					.termsOfService("http://swagger.io/terms/")
					.contact(contact)
					.license(new License().name("Apache 2.0").url("http://springdoc.org")))
					.externalDocs(new ExternalDocumentation());
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:application-messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
		
	}
	
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean =  new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}