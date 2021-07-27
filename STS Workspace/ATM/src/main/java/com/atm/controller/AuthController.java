package com.atm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
 

import java.util.function.Supplier;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@GetMapping(value= "/guest")
    public ResponseEntity<String> guest() {
        System.out.println("Showing guest page.");
        return new ResponseEntity<String>("Hello from guest page!", HttpStatus.OK);
    }

}
