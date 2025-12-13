package com.logistique.gestion_colis.auth;

import com.logistique.gestion_colis.dto.AuthenticationRequest;
import com.logistique.gestion_colis.dto.AuthenticationResponse;
import com.logistique.gestion_colis.dto.RegisterRequest;
import com.logistique.gestion_colis.models.Admin;
import com.logistique.gestion_colis.repository.UserRepository;
import com.logistique.gestion_colis.security.AppUserDetails;
import com.logistique.gestion_colis.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        if (repository.existsByLogin(request.getLogin())) {
            throw new RuntimeException("Login already in use");
        }

        var user = new Admin(
                request.getLogin(),
                passwordEncoder.encode(request.getPassword()),
                true
        );
        repository.save(user);

        var userDetails = new AppUserDetails(user);
        var jwtToken = jwtUtils.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtUtils.generateToken(authentication);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}