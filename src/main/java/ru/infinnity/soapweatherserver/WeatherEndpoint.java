package ru.infinnity.soapweatherserver;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.infinnity.myweather.wsdl.GetWeatherRequest;
import ru.infinnity.myweather.wsdl.GetWeatherResponse;
import ru.infinnity.myweather.wsdl.Weather;

@Endpoint
@RequiredArgsConstructor
public class WeatherEndpoint {
    private static final String NAMESPACE_URI = "https://www.infinnity.ru/springsoap/gen";

    private final WeatherService weatherService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getWeatherRequest")
    @ResponsePayload
    public GetWeatherResponse getWeather(@RequestPayload GetWeatherRequest request) {
        GetWeatherResponse response = new GetWeatherResponse();
        Weather weather = new Weather();
        weather.setName(request.getName());
        weather.setTemperature(weatherService.get(request.getName()));
        response.setWeather(weather);
        return response;
    }
}
