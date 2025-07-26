
package com.banco.solicitudes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Esta anotación combina @Configuration, @EnableAutoConfiguration y @ComponentScan
public class GestorSolicitudesApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(GestorSolicitudesApplication.class, args);
    }

}