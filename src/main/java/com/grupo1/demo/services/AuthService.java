    package com.grupo1.demo.services;

    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;

    import com.grupo1.demo.security.JwtTokenProvider;
    import com.grupo1.demo.dto.AuthResponse;
    import com.grupo1.demo.dto.LoginRequest;
    import com.grupo1.demo.repositories.UserRepository;

    import lombok.RequiredArgsConstructor;

    @Service
    @RequiredArgsConstructor
    public class AuthService {

        private final UserRepository usuarioRepository;
        private final JwtTokenProvider jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse login(LoginRequest request) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            
            
            UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
            
            String token = jwtService.getToken(user);

            String role = user.getAuthorities().stream()
                            .map(authority -> authority.getAuthority())
                            .findFirst()
                            .orElse("user");

           
            return AuthResponse.builder()
                            .token(token)
                            .role(role)
                            .build();
        }
    }
