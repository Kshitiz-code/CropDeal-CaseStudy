package com.AgricultureApp.Payment_MicroserviceAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMicroserviceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentMicroserviceApiApplication.class, args);
	}

}
