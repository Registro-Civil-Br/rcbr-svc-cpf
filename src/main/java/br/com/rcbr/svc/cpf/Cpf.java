package br.com.rcbr.svc.cpf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Cpf {

    public static void main(String[] args) {
        SpringApplication.run(Cpf.class, args);
    }

}
