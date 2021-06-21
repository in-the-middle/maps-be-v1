package ua.trip.maps.be.v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableFeignClients(basePackages = {"ua.trip.maps.be.v1.valhalla.consumer"})
public class MapsApplication {
    public static void main(String[] args){
        SpringApplication.run(MapsApplication.class, args);
    }
}
