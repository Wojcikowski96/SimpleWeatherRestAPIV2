package com.example.task.dtoweather;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenWeatherDtoWeather {
    private List<WeatherInfoDto> weather;
    private MainInfoDto main;
    private String name;

    private String correlationID;

    @Override
    public String toString() {
        return "OpenWeatherDtoWeather{" +
                "weather=" + weather +
                ", main=" + main +
                ", name='" + name + '\'' +
                ", correlationID='" + correlationID + '\'' +
                '}';
    }
}