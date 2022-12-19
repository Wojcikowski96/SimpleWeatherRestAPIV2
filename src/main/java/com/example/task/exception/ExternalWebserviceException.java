package com.example.task.exception;

import com.example.task.clients.model.RequestFlowData;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Setter
@Getter
public class ExternalWebserviceException extends RuntimeException {
    private String correlationID;
    private Integer messageLevel;
    ExternalWebserviceException(){

    }

    public ExternalWebserviceException(String correlationID, Integer messageLevel) {
        super("Wystąpił problem z zewnętrznym webserwisem");
        this.correlationID = correlationID;
        this.messageLevel = messageLevel;
    }

}
