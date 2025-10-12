package br.com.todolist.api.service;

import org.springframework.stereotype.Service;

import br.com.todolist.api.controller.AuthController.UserRegistrationDto;
import br.com.todolist.api.domain.User.User;
import br.com.todolist.api.domain.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class LoginService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(UserRegistrationDto registrationDto){


         // Verificar se username já existe
         if (userRepository.existsByUsername(registrationDto.username())) {
            throw new RuntimeException("Usuario Ja Existe");
        }
        
        // Verificar se email já existe
        if (userRepository.existsByEmail(registrationDto.email())) {
            throw new RuntimeException("email ja existe");
        }
        
        // Criptografar a senha antes de salvar
        String encryptedPassword = passwordEncoder.encode(registrationDto.password());
        
        var user = new User(registrationDto.username(), encryptedPassword, registrationDto.password());
        userRepository.save(user);

    }
    }



