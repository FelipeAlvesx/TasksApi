package br.com.todolist.api.controller;

import br.com.todolist.api.domain.User.User;
import br.com.todolist.api.domain.User.UserRepository;
import br.com.todolist.api.dto.TokenResponse;
import br.com.todolist.api.service.LoginService;
import br.com.todolist.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginDto loginDto) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
       
        var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenResponse(tokenJWT));
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistrationDto registrationDto) {
       
        this.loginService.register(registrationDto);
        var user = userRepository.findByUsername(registrationDto.username);
        
        return ResponseEntity.ok().build();
    }
    
    // DTOs internos para requests
    public record LoginDto(String username, String password) {}
    
    public record UserRegistrationDto(String username, String email, String password) {
        public UserRegistrationDto(User user){
            this(user.getUsername(), user.getEmail(), user.getPassword());
        }
    }
}
