package ltweb.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ltweb.entity.RoleEntity;
import ltweb.entity.UserEntity;
import ltweb.model.LoginDto;
import ltweb.model.SignUpDto;
import ltweb.repository.RoleRepository;
import ltweb.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Đăng nhập
     * POST /api/auth/signin
     */
    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), 
                loginDto.getPassword()
            )
        );
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed in successfully!", HttpStatus.OK);
    }

    /**
     * Đăng ký
     * POST /api/auth/signup
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        // Kiểm tra username đã tồn tại
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Kiểm tra email đã tồn tại
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Tạo user mới
        UserEntity user = new UserEntity();
        user.setName(signUpDto.getUsername());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        // Gán role USER mặc định
        RoleEntity userRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Error: Role USER not found"));
        user.setRoles(Collections.singleton(userRole));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}