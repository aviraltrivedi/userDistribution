package com.demoapp.demo.controller;

import com.demoapp.demo.ApplicationConstants;
import com.demoapp.demo.service.MapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(ApplicationConstants.API_DOCTOR_ROOT)
public class MapsController {

    @Autowired
    MapsService mapsService;


    @GetMapping(ApplicationConstants.API_GET_SHOPS)
    public String getMedicalShops() throws IOException {
        return mapsService.listOfPlaces(ApplicationConstants.CONST_PHARMACY);
    }

    @GetMapping(ApplicationConstants.API_GET_PATHOLOGY_LAB)
    public String getPathologyShops() throws IOException {
        return mapsService.listOfPlaces(ApplicationConstants.CONST_PATHOLOGY);
    }
}
