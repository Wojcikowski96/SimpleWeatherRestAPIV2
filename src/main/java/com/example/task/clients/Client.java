package com.example.task.clients;

import com.example.task.clients.model.*;
import com.example.task.dtoweather.OpenWeatherDtoWeather;
import com.example.task.exception.CityNotFoundException;
//import com.example.task.filter.SoapHandler;
import com.example.task.forecastbuilder.ForecastBuilder;
import com.example.task.repository.AppRepository;
import com.example.task.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
@Component
@Endpoint
public class Client {
    private final RestTemplateService openWeatherService;
    private final ForecastBuilder builder;

    public Client(RestTemplateService openWeatherService, ForecastBuilder builder) {
        this.openWeatherService = openWeatherService;
        this.builder = builder;
    }
    @PayloadRoot(namespace = "http://task.example.com", localPart = "getForecast")
    @ResponsePayload
    public GetForecastResponse getForecast(@RequestPayload GetForecast getForecast){
        GetForecastResponse resp = new GetForecastResponse();
        CustomWeatherDto customResponse = builder.buildForecast(getForecast, openWeatherService);
        resp.setCustomWeatherDto(customResponse);
        return resp;}
}


