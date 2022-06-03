package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.*;
import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.exception.ForbiddenException;
import com.amazonaws.services.sns.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserDetailsCustomService userDetailsCustomService;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtTokenUtil;

    @Autowired
    public AuthController(UserDetailsCustomService userDetailsCustomService, AuthenticationManager authenticationManager, JwtUtils jwtTokenUtil) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        UserResponseDto userResponseDto = userDetailsCustomService.register(userRequestDto);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) {

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new ForbiddenException("Incorrect username or password");
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> usersDTOs = this.userDetailsCustomService.getAllUsers();
        return ResponseEntity.ok().body(usersDTOs);
    }

    @PatchMapping("user/{id}")
    public UserResponseDto updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        UserResponseDto userResponseDto = userDetailsCustomService.updateUser(userId, userUpdateDto);
        return userResponseDto;
    }

}
