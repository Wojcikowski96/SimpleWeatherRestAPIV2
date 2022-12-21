package com.example.task.forecastbuilder;

import com.example.task.clients.model.*;
import com.example.task.openWeatherDto.OpenWeatherDtoWeather;
import com.example.task.exception.CityNotFoundException;
import com.example.task.exception.CorrelationIdEmptyException;
import com.example.task.repository.CitiesRepository;
import com.example.task.service.RestTemplateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class ForecastBuilder {
    @Value("${config.url}")
    private String baseUrl;
    @Value("${config.appid}")
    private String appId;

    @Value("${config.iconUrl}")
    private String iconbaseUrl;
    @Value("${config.correlationID}")
    private String correlationID;
    private CitiesRepository repository;
    private RequestFlowData data;
    ForecastBuilder(CitiesRepository repository, RequestFlowData data){
        this.repository = repository;
        this.data = data;
    }
    public CustomWeatherDto buildForecast(GetForecast getForecast, RestTemplateService openWeatherService){

        String correlationId = data.getCorrelationId();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(RequestFlowData.correlationidkey, correlationId);


        Long locationID = getForecast.getCityID();
        repository.findById(getForecast.getCityID())
                .orElseThrow(() -> new CityNotFoundException(locationID, data.getCorrelationId(), data.getRetraceCount()));

        double longitude = repository.findById(locationID).map(City::getLongitude).orElse(null);
        double latitude = repository.findById(locationID).map(City::getLatitude).orElse(null);


        final String url = baseUrl+"?lat="+latitude +
                "&lon="+longitude+
                "&exclude=daily,minutely&units=metric&appid="+appId+"&cnt=1&lang=pl";
        System.out.println(url);

        openWeatherService.setHttpHeaders(httpHeaders);
        openWeatherService.setResponseFromOpenWeather(url);

        OpenWeatherDtoWeather openWeatherServiceResponse = openWeatherService.getResponse();
        openWeatherServiceResponse.setCorrelationID(correlationId);
        System.out.println(openWeatherServiceResponse);

        if(data.getCorrelationId() == null || data.getCorrelationId().isEmpty()){
            throw new CorrelationIdEmptyException();
        }

        CustomWeatherDto formatted = CustomWeatherDto.builder().temperature(openWeatherServiceResponse.getMain()
                        .getTemp()).description(openWeatherServiceResponse.getWeather().get(0).getDescription())
                .location(openWeatherServiceResponse.getName()).locationID(locationID)
                .iconUrl(iconbaseUrl+openWeatherServiceResponse.getWeather().get(0).getIcon()+".png").correlationID(data).build();
        return formatted;
    }
}
