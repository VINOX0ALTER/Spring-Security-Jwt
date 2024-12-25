package com.serin.hairLeap.hairLeap;

import com.serin.hairLeap.hairLeap.sec.entities.AppRole;
import com.serin.hairLeap.hairLeap.sec.entities.AppUser;
import com.serin.hairLeap.hairLeap.sec.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class HairLeapApplication {

	public static void main(String[] args) {
		SpringApplication.run(HairLeapApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@Bean
	CommandLineRunner start(AccountService accountService){
		return args -> {
			accountService.addNewRole(new AppRole(0,"USER"));
			accountService.addNewRole(new AppRole(1,"ADMIN"));
			accountService.addNewRole(new AppRole(2,"CUSTOMER_MANAGER"));
			accountService.addNewRole(new AppRole(3,"PRODUCT_MANAGER"));
			accountService.addNewRole(new AppRole(4,"BILLS_MANAGER"));

			accountService.addNewUser(new AppUser(1,"admin","123",new ArrayList<>()));
			accountService.addNewUser(new AppUser(2,"user1","123",new ArrayList<>()));
			accountService.addNewUser(new AppUser(3,"user2","123",new ArrayList<>()));
			accountService.addNewUser(new AppUser(4,"user3","123",new ArrayList<>()));
			accountService.addNewUser(new AppUser(5,"user4","123",new ArrayList<>()));

			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("admin","ADMIN");
			accountService.addRoleToUser("admin","USER");
			accountService.addRoleToUser("user2","USER");
			accountService.addRoleToUser("user2","CUSTOMER_MANAGER");
			accountService.addRoleToUser("user3","USER");
			accountService.addRoleToUser("user3","PRODUCT_MANAGER");
			accountService.addRoleToUser("user4","USER");
			accountService.addRoleToUser("user4","BILLS_MANAGER");



		};
	}

}
