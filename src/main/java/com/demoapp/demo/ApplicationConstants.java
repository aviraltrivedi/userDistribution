package com.demoapp.demo;

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
            "/api/patient/**"
            // other public endpoints of your API may be appended to this array
    };

    String API_AUTH_URLS = "/api/auth/**";
    String API_TEST_URLS = "/api/test/**";

    String API_AUTH_ROOT = "/api/auth";
    String API_SIGNIN = "/signin";
    String API_SIGNUP = "/signup";

    String API_PATIENT_ROOT = "/api/patient";
    String API_GET_DOCTORS = "/getDoctorss";


}
