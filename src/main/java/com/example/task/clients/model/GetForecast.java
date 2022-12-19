package com.example.task.clients.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "",  propOrder = {
        "cityID",
        "correlationId"
})
@XmlRootElement(name = "getForecast", namespace = "http://task.example.com")
public class GetForecast {

    protected long cityID;
    protected String correlationId;


}
