package com.bankapp.BankApp;

import com.bankapp.BankApp.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

// added enabling JPA repo

//@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
//@EnableGlobalMethodSecurity(securedEnabled = true)
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
