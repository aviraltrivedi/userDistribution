package com.demoapp.demo.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapsLocation {

    private String LocData;
    private String LocQuery;

    public String getLocQuery() {
        return LocQuery;
    }

    public void setLocQuery(String locQuery) {
        LocQuery = locQuery;
    }

    public MapsLocation(String locData) {
        LocData = locData;
    }

    public MapsLocation()
    {

    }

    public String getLocData() {
        return LocData;
    }

    public void setLocData(String locData) {
        LocData = locData;
    }

}
