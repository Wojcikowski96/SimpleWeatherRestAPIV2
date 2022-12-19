package com.example.task.dtoweather;

import lombok.Getter;

@Getter
public class WeatherInfoDto {
    private String description;
    private String icon;

    @Override
    public String toString() {
        return "WeatherInfoDto{" +
                "description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
