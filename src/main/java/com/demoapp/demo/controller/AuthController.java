package com.demoapp.demo.controller;

import com.demoapp.demo.model.User;
import com.demoapp.demo.model.UserType;
import com.demoapp.demo.model.UserTypes;
import com.demoapp.demo.payload.request.LoginRequest;
import com.demoapp.demo.payload.request.SignupRequest;
import com.demoapp.demo.payload.respomse.JwtResponse;
import com.demoapp.demo.payload.respomse.MessageResponse;
import com.demoapp.demo.repository.UserRepository;
import com.demoapp.demo.repository.UserTypeRepository;
import com.demoapp.demo.security.jwt.JwtUtils;
import com.demoapp.demo.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = new ArrayList<>();
        String userType = userDetails.getAuthorities().toString();
        roles.add(userType);
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    //@PostMapping("/signup")
    @RequestMapping(value="/signup", method = RequestMethod.POST,
                    consumes= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        UserType userType = signUpRequest.getUserType();

        //UserType strUserType = signUpRequest.getUserType();
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                userType);




//        if (strRoles == null) {
//            UserType userType = userTypeRepository.findByUserType(UserTypes.DOCTOR)
//                    .orElseThrow(() -> new RuntimeException("Error: UserType is not found."));
//            roles.add(userType);
//        } else {
//            strRoles.forEach(role -> {
//                switch (role) {
//                    case "admin":
//                        UserType adminRole = userTypeRepository.findByUserType(UserTypes.ROLE_ADMIN)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(adminRole);
//
//                        break;
//                    case "Doctor":
//                        UserType docRole = userTypeRepository.findByUserType(UserTypes.DOCTOR)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(docRole);
//
//                        break;
//                    default:
//                        UserType userRole = userTypeRepository.findByUserType(UserTypes.PATIENT)
//                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                        roles.add(userRole);
//                }
//            });
//        }

//Set<String> strRoles = signUpRequest.getRole();
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
