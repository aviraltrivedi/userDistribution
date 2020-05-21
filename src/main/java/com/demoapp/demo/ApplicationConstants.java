package com.demoapp.demo;

import org.springframework.beans.factory.annotation.Value;

public interface ApplicationConstants {


    String GET_ALL_USERS = "/";
    String GET_USER_BY_ID = "/getUser/{id}";
    String ADD_ONE_USER = "/addUser";
    String UPDATE_ONE_USER = "/updateUser/{id}";
    String DELETE_ONE_USER = "/deleteUser/{id}";

    String[] DEV_UTILS_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/h2-console/**",
            "/api/doctor/getMedicalShops",
            "/api/doctor/getPathologyLabs",
            "/api/patient/**"
            // other public endpoints of your API may be appended to this array
    };

    String API_AUTH_URLS = "/api/auth/**";
    String API_TEST_URLS = "/api/test/**";

    String API_AUTH_ROOT = "/api/auth";
    String API_SIGNIN = "/signin";
    String API_SIGNUP = "/signup";

    String API_DOCTOR_ROOT = "/api/doctor";
    String API_GET_SHOPS = "/getMedicalShops";
    String API_GET_PATHOLOGY_LAB = "/getPathologyLabs";

    //Google Maps API's
    String API_GEOLOCATION = "https://www.googleapis.com/geolocation/v1/geolocate?";
    String API_PLACES_SEARCH = "https://maps.googleapis.com/maps/api/place/search/json?";
    String CONST_LOCATION = "location=";
    String CONST_RADIUS = "&radius=5000";
    String CONST_SENSOR = "&sensor=true";
    String CONST_PHARMACY = "&types=pharmacy";
    String CONST_PATHOLOGY = "&keyword=pathology+lab";

    String API_PATIENT_ROOT = "/api/patient";
    String API_GET_DOCTORS = "/getDoctorss";
}
