package com.copious.training.api.v1;

import com.copious.training.config.JwtTokenUtil;
import com.copious.training.config.JwtUserDetailsService;
import com.copious.training.domain.GenericResponse;
import com.copious.training.domain.JwtRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/authenticate")
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @ApiOperation(value = "Ping for Health Check")
    @GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Object> ping() {
        return ResponseEntity.ok("Services are up and running...");
    }

    @PostMapping
    public ResponseEntity<GenericResponse<String>> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("jwt-token", token);
        return new ResponseEntity<>(
                new GenericResponse<>(true,
                        HttpStatus.OK.name(),
                        "User logged in successfully!!"
                ),
                httpHeaders,
                HttpStatus.OK
        );
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }
}


