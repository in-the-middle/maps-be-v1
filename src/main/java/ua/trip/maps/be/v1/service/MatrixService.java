package ua.trip.maps.be.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.trip.maps.be.v1.valhalla.consumer.ValhallaApiConsumer;
import ua.trip.maps.be.v1.valhalla.model.MatrixInputValhallaModel;
import ua.trip.maps.be.v1.valhalla.model.MatrixOutputValhallaModel;

import java.util.concurrent.Future;

@Service
public class MatrixService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatrixService.class);

    @Autowired
    ValhallaApiConsumer valhallaApiConsumer;

    @Async
    public Future<MatrixOutputValhallaModel> getMatrixAsync(MatrixInputValhallaModel matrixInputValhallaModel){
        ResponseEntity<MatrixOutputValhallaModel> matrixOutputValhallaModelResponseEntity = valhallaApiConsumer.getMatrix(matrixInputValhallaModel);
        if (matrixOutputValhallaModelResponseEntity.getStatusCode().isError() || matrixOutputValhallaModelResponseEntity.getBody() == null) {
            LOGGER.error("Valhalla route service answered with exception: status code={}, response body={}", matrixOutputValhallaModelResponseEntity.getStatusCode(), matrixOutputValhallaModelResponseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Route service answered with exception");
        }
        return new AsyncResult<>(matrixOutputValhallaModelResponseEntity.getBody());
    }
}
