package com.example.microservicios_usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {
	    "com.example.microservicios_usuarios",
	    "com.example.commons_microservicios"
	})
@EntityScan({
	"com.example.commons_alumnos.alumnos.models.entity"
})
public class MicroserviciosUsuariosApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosUsuariosApplication.class, args);
	}

}