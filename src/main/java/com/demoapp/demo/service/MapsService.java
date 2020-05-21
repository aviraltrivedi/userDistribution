package com.demoapp.demo.service;

import com.demoapp.demo.ApplicationConstants;
import com.demoapp.demo.model.MapsLocation;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;


@Service
public class MapsService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Value("${google.api.key}")
    private String Google_Api_Key;

    @Autowired
    private RestTemplate rTemplate;

    @Autowired
    private MapsLocation mapsLocation;

    public void getLatAndLng() throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String s = rTemplate.postForObject(ApplicationConstants.API_GEOLOCATION+"&key="+Google_Api_Key, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jsonParser = factory.createParser(s);
        JsonNode node = (JsonNode) mapper.readTree(jsonParser).get("location");
        String location = String.valueOf(node.get("lat").doubleValue())+","+String.valueOf(node.get("lng").doubleValue());
        this.mapsLocation.setLocData(location);
    }

    public String listOfPlaces(String typeofplace) throws IOException
    {
        this.getLatAndLng();
        String current_Loc = ApplicationConstants.CONST_LOCATION+this.mapsLocation.getLocData();
        String key = "&key="+Google_Api_Key;
        this.mapsLocation.setLocQuery(ApplicationConstants.API_PLACES_SEARCH+
                current_Loc+
                ApplicationConstants.CONST_RADIUS+
                typeofplace+
                key+
                ApplicationConstants.CONST_SENSOR);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String s = rTemplate.postForObject(this.mapsLocation.getLocQuery(), entity, String.class);
        return s;
    }

}
