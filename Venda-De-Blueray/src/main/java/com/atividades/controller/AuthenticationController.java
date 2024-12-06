package com.atividades.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atividades.DTOs.AuthenticationDTO;
import com.atividades.DTOs.LoginResponseDTO;
import com.atividades.DTOs.RegisterDTO;
import com.atividades.models.User;
import com.atividades.repository.UserRepository;
import com.atividades.security.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.http.MediaType;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> register(
	        @RequestPart("image") MultipartFile imageFile,
	        @RequestPart("data") String data) throws IOException {
	    ObjectMapper objectMapper = new ObjectMapper();
	    RegisterDTO registerData = objectMapper.readValue(data, RegisterDTO.class);

	    if (this.userRepository.findByUsername(registerData.username()) == null) {
	        String imagePath = saveImage(imageFile);
	        String encryptedPassword = new BCryptPasswordEncoder().encode(registerData.password());
	        User newUser = new User(registerData.username(), registerData.email(), encryptedPassword, registerData.role(), imagePath);
	        this.userRepository.save(newUser);

	        
	        ResponseEntity<?> logar = loginInterno(new AuthenticationDTO(registerData.username(), registerData.password()));
	        return logar;
	    } else {
	        return ResponseEntity.badRequest().build();
	    }
	}

	@Value("${app.upload.dir}")
	private String uploadsDir;

	private String saveImage(MultipartFile imageFile) throws IOException {
	    File uploadDir = new File(uploadsDir);

	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();
	    }

	    String filePath = uploadsDir + UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
	    File destinationFile = new File(filePath);
	    imageFile.transferTo(destinationFile);

	    return filePath;
	}
	
	public ResponseEntity<?> loginInterno(AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = tokenService.generateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
}
