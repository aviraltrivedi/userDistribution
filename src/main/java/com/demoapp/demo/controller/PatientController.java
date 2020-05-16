package com.demoapp.demo.controller;

import com.demoapp.demo.ApplicationConstants;
import com.demoapp.demo.model.User;
import com.demoapp.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(ApplicationConstants.API_PATIENT_ROOT)
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping(ApplicationConstants.API_GET_DOCTORS)
    public List<User> listOfDoctors(){
        return patientService.getListDoctors();
    }
}
