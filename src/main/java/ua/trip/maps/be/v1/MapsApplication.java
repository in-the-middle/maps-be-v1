package ua.trip.maps.be.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
//import ua.trip.auth.lib.v1.configuration.AuthConfiguration;

@SpringBootApplication
@EnableAsync
@EnableFeignClients(basePackages = {"ua.trip.maps.be.v1.valhalla.consumer"})
//@Import(AuthConfiguration.class)
public class MapsApplication {
    public static void main(String[] args){
        SpringApplication.run(MapsApplication.class, args);
    }
}
