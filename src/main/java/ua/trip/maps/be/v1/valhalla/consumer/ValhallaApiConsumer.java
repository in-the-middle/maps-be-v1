package ua.trip.maps.be.v1.valhalla.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.trip.maps.be.v1.valhalla.model.MatrixInputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.MatrixOutputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.RouteInputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.RouteOutputValhallaModel;

import java.util.concurrent.Future;

@FeignClient(name = "valhalla", url = "${VALHALLA_SERVICE_URL:http://host.docker.internal:8002/}")
public interface ValhallaApiConsumer {
    @RequestMapping(method = RequestMethod.POST, value = "/route", produces = "application/json")
    ResponseEntity<RouteOutputValhallaModel> getRoute(@RequestBody RouteInputValhallaModel routeInputValhallaModel);

    @RequestMapping(method = RequestMethod.POST, value = "/sources_to_targets", produces = "application/json")
    ResponseEntity<MatrixOutputValhallaModel> getMatrix(@RequestBody MatrixInputValhallaModel matrixInputValhallaModel);
}
