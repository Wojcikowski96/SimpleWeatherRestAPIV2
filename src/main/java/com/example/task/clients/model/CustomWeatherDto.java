package com.example.task.clients.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@Builder
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customWeatherDto", namespace = "http://task.example.com", propOrder = {
        "locationID",
        "description",
        "temperature",
        "location",
        "iconUrl",
        "correlationID"
})
public class CustomWeatherDto {

    @XmlElement(required = true)
    private Long locationID;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private Double temperature;
    @XmlElement(required = true)
    private String location;
    @XmlElement(required = true)
    private String iconUrl;
    @XmlElement(required = true, name = "RequestFlowData")
    private RequestFlowData correlationID;


}
