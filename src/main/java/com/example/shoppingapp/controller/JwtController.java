package com.example.shoppingapp.controller;

import com.example.shoppingapp.entity.JwtRequest;
import com.example.shoppingapp.entity.JwtResponse;
import com.example.shoppingapp.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;


    @PostMapping({"/authenticate"})
    public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) throws Exception {
        JwtResponse jwtResponse = jwtService.createJwtToken(jwtRequest);
        response.addHeader("Authorization", "Bearer " + jwtResponse.getJwtToken());

        return ResponseEntity.ok(jwtResponse);
    }

}
