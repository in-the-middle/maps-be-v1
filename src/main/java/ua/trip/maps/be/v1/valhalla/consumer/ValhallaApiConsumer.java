package ua.trip.maps.be.v1.valhalla.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.trip.maps.be.v1.valhalla.model.RouteInputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.RouteOutputValhallaModel;

@FeignClient(name = "valhalla", url = "http://localhost:8002/")
public interface ValhallaApiConsumer {
    @RequestMapping(method = RequestMethod.POST, value = "/route", produces = "application/json")
    ResponseEntity<RouteOutputValhallaModel> getRoute(@RequestBody RouteInputValhallaModel routeInputValhallaModel);
}
