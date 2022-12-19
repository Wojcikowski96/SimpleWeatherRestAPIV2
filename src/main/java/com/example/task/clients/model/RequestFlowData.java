package com.example.task.clients.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "correlationidkey",
        "correlationId",
        "retraceCount"
})
@XmlRootElement(name = "RequestFlowData", namespace = "http://task.example.com")
@Component

public class RequestFlowData {

    @XmlElement(required = true, name = "CORRELATION_ID_KEY")
    public static final String correlationidkey = "CorrelationId";
    @XmlElement(required = true, nillable = true)
    private static String correlationId;
    @XmlElement(required = true, type = Integer.class, name = "retraceCount", nillable = true)
    private static Integer retraceCount = 0;


    public static void setId(String correlationId) {
        RequestFlowData.correlationId = correlationId;

    }

    @Override
    public String toString() {
        return "RequestFlowData{" +
                "correlationId='" + correlationId + '\'' +
                ", retraceCount=" + retraceCount +
                '}';
    }

    public String getCorrelationId() {
        retraceCount++;
        return correlationId;
    }
    public Integer getRetraceCount(){
        return retraceCount;
    }

    public String getCorrelationIdKey() {
        return correlationidkey;
    }
}
