package com.example.task.exception;

import com.example.task.clients.model.RequestFlowData;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyResponseErrorHandler implements ResponseErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(MyResponseErrorHandler.class);

    private RequestFlowData data;

    private final List acceptableStatus;

    public  MyResponseErrorHandler(@Value("${config.good-status}") String goodStatus, RequestFlowData data) {
        this.data = data;
        System.out.println(goodStatus);

        acceptableStatus = Arrays.stream(goodStatus.split(","))
                .map(HttpStatus::valueOf)
                .collect(Collectors.toList()) ;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("Response error : {} {}", response.getStatusCode(), response.getStatusText());
        throw new ExternalWebserviceException(data.getCorrelationId(), data.getRetraceCount());

    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Status webserwisu: "+response.getStatusCode());
        return !acceptableStatus.contains(response.getStatusCode());

    }
}