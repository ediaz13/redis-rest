package ar.com.santander.mobile.backend.individual.controller;

import ar.com.santander.mobile.backend.individual.dto.AuthenticationRequest;
import ar.com.santander.mobile.backend.individual.dto.AuthenticationResponse;
import ar.com.santander.mobile.backend.individual.helper.JwtHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper jwtHelper;
    
    @Autowired
    private HttpServletRequest request;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(
                authenticationRequest.getUsername()
        );

        final String token = jwtHelper.generateToken(userDetails);
        
        HttpSession session = request.getSession();
        session.setAttribute("Nombre", authenticationRequest.getUsername());
        session.setAttribute("Pass", authenticationRequest.getPassword());
        session.setAttribute("Token", token);

        
        System.out.println("Nombre: " + session.getAttribute("Nombre"));
        System.out.println("Password: " + session.getAttribute("Pass"));
        System.out.println("Session ID: " + session.getId());
        System.out.println("Token: " + token);
        
       // session.invalidate();
        
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
