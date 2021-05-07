package com.bankapp.BankApp;

import com.bankapp.BankApp.repository.UserRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);
	}

}
