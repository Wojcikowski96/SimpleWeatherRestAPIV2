package com.example.task.controller;

import com.example.task.clients.model.City;
import com.example.task.clients.model.CustomWeatherDto;
import com.example.task.clients.model.GetForecast;
import com.example.task.repository.CitiesRepository;
import com.example.task.service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin(origins = "http://localhost:4200/")
@Api(value="", tags={"Mój serwis"})
public class RestController {
    Logger log;
    private final WeatherService service;
    private final CitiesRepository repository;


    public RestController(WeatherService weatherService, CitiesRepository repository) {
        this.service = weatherService;
        this.repository = repository;
    }
    @GetMapping("/user")
    public Principal user(Principal user) {
        //log.info(details);
        return user;
    }
    @GetMapping(path = "/cities")
    @ApiOperation("Gets all 10 predefined locations")

    public List<City> getLocations(@AuthenticationPrincipal UserDetails user) throws ParserConfigurationException, IOException, SAXException {
        log.info(user);
        return (List<City>) repository.findAll();
    }

//    @ApiOperation("Gets multiple forecasts for multiple locations identities")
//    @RequestMapping(value = "/forecasts", method = RequestMethod.GET)
//    public List<CustomWeatherDto> getMultipleForecast(@RequestParam(name="cityID") List<Long> idS) {
//
//
//        return service.getMultipleForecast(idS);
//    }

    @ApiOperation("Gets forecast for single locationID")
    @RequestMapping(value = "/forecast", method = RequestMethod.GET)
    public CustomWeatherDto getForecast(@RequestParam(value = "cityID") long identity,
                                        @RequestParam(value = "correlationId") String correlationId,
                                        GetForecast getForecast) {
        System.out.println("Id");
        System.out.println(correlationId);
        getForecast.setCityID(identity);

        return service.getWeather(getForecast);
    }
//    @CrossOrigin(origins = "http://localhost:4200")
//    @RequestMapping("/user")
//    public Principal user(Principal user) {
//        return user;
//    }

}
