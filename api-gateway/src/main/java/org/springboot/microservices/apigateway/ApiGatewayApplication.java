package org.springboot.microservices.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // Enable Eureka Client
public class ApiGatewayApplication {

        public static void main(String[] args) {
             SpringApplication.run(ApiGatewayApplication.class, args);
        }
}

