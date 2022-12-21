package com.example.task.service;

import com.example.task.clients.Client;
import com.example.task.clients.model.*;
import org.springframework.stereotype.Service;


@Service
//@Endpoint
public class WeatherService {
//    private RestTemplateService openWeatherService;
//    @Value("${config.url}")
//    private String baseUrl;
//    @Value("${config.appid}")
//    private String appId;
//
//    @Value("${config.iconUrl}")
//    private String iconbaseUrl;
//    private final AppRepository repository;
      private Client client;
      WeatherService(Client client){
          this.client = client;
      }
      public CustomWeatherDto getWeather(GetForecast getForecast){
          return client.getForecast(getForecast).getCustomWeatherDto();
    }



//    public  List<CustomWeatherDto> getMultipleForecast(List<Long> ids){
//        List<CustomWeatherDto> resultRequest = new ArrayList<>();
//
//        for(Long singleID : ids){
//                  resultRequest.add(getForecast(singleID));
//        }
//
//        return resultRequest;
//    }

//    @PayloadRoot(namespace = "http://task.example.com", localPart = "getForecast")
//    @ResponsePayload
//    public GetForecastResponse getForecast(@RequestPayload GetForecast getForecast){
//        String correlationId = RequestFlowData.getId();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(RequestFlowData.CORRELATION_ID, correlationId);
//
//        LOGGER.info("start REST request to {} with correlationId {}"+ correlationId);
//        LOGGER.info("nagłówek"+ httpHeaders.get("X-Correlation-ID"));
//
//        GetForecastResponse resp = new GetForecastResponse();
//        Long locationID = getForecast.getCityID();
//        repository.findById(getForecast.getCityID())
//                .orElseThrow(() -> new CityNotFoundException(locationID));
//
//        double longitude = repository.findById(locationID).map(City::getLongitude).orElse(null);
//        double latitude = repository.findById(locationID).map(City::getLatitude).orElse(null);
//
//
//        final String url = baseUrl+"?lat="+latitude +
//                "&lon="+longitude+
//                "&exclude=daily,minutely&units=metric&appid="+appId+"&cnt=1&lang=pl";
//        System.out.println(url);
//
//        openWeatherService.setHttpHeaders(httpHeaders);
//
//        openWeatherService.setResponseFromOpenWeather(url);
//
//        OpenWeatherDtoWeather openWeatherServiceResponse = openWeatherService.getResponse();
//
//        CustomWeatherDto formatted = CustomWeatherDto.builder().temperature(openWeatherServiceResponse.getMain()
//                        .getTemp()).description(openWeatherServiceResponse.getWeather().get(0).getDescription())
//                .location(openWeatherServiceResponse.getName()).locationID(locationID)
//                .iconUrl(iconbaseUrl+openWeatherServiceResponse.getWeather().get(0).getIcon()+".png").correlationID(correlationId).build();
//        resp.setCustomWeatherDto(formatted);
//        return resp;

    }

