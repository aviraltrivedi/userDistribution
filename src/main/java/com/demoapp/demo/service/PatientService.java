package com.demoapp.demo.service;

import com.demoapp.demo.model.User;
import com.demoapp.demo.model.UserTypes;
import com.demoapp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getListDoctors(){
        List<User> doctors = userRepository.findAll();
        for(User doctor : doctors){
            if(doctor.getUserType().getUserTypes().equals(UserTypes.PATIENT)){
                doctors.remove(doctor);
            }
        }


        return doctors;
    }

}
