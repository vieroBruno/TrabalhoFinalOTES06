package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    @GetMapping("/{city}")
    public Object getWeatherData(@PathVariable String city) {

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city
                + "&appid=" + apiKey
                + "&units=metric&lang=pt_br";

        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("/coordinates")
    public Object geWeatherByLatAndLon(@RequestParam double lat,@RequestParam double lon) {
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat
            + "&lon=" + lon
            + "&appid=" + apiKey
            + "&units=metric&lang=pt_br";

        return restTemplate.getForObject(url, Object.class);
    }
}