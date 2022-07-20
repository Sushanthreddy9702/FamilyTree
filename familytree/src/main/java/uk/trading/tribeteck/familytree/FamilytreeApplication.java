package uk.trading.tribeteck.familytree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("uk.trading.tribeteck.familytree.*")
public class FamilytreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilytreeApplication.class, args);
	}

}

