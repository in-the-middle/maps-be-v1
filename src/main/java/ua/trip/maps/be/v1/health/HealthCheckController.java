package ua.trip.maps.be.v1.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actuator/maps-be-v1")
public class HealthCheckController {
    @RequestMapping(value = "/health", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> healthTest()
    {
        return ResponseEntity.ok("{\"status\":\"UP\"}");
    }
}
