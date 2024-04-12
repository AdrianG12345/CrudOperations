package com.example.demo.auth;


import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final String REGEXNAME = "^[a-zA-Z]+$";
    private static final String[] domainNames = {".com", ".net", ".ro", ".org"};
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        Pattern pattern = Pattern.compile(REGEXNAME);
        String email = request.getEmail();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();

        if (!validateEmail(email))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        boolean bool = pattern.matcher(firstName).matches() && pattern.matcher(lastName).matches();
//        if (! (firstName.matches(REGEXNAME) && lastName.matches(REGEXNAME)) ) {
        if (!bool){
            System.out.println("name must only be characters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


    private static boolean validateEmail(String email){
        // Check if email contains '@' character
        if (!email.contains("@")) {
            System.out.println("EMAIL DOES NOT CONTAIN '@'");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            return false;
        }
        int ok = 0;
        for (String v : domainNames){
            if (email.endsWith(v))
                ok = 1;
        }
        if (ok == 0){
            System.out.println("EMAIL DOES NOT END IN DOMAIN NAME!");
            return false;
        }
        return true;
    }

}
