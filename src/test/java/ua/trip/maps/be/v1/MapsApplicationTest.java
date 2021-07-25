package ua.trip.maps.be.v1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.trip.maps.be.v1.service.CenterService;

public class MapsApplicationTest {
    @MockBean
    CenterService centerService;

    @Test
    void contextLoads() {
    }
}
