package com.phillippko.smns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableFeignClients
@EnableCaching
public class SmnsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmnsApplication.class, args);
	}

}
