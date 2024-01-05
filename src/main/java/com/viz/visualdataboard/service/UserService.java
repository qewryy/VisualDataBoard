package com.viz.visualdataboard.service;

import com.viz.visualdataboard.domain.Role;
import com.viz.visualdataboard.domain.User;
import com.viz.visualdataboard.dto.request.SignUpRequest;
import com.viz.visualdataboard.dto.response.UserResponse;
import com.viz.visualdataboard.repository.RoleRepository;
import com.viz.visualdataboard.repository.UserRepository;
import com.viz.visualdataboard.security.JwtProvider;
import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public void join(SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())){
            throw new RuntimeException("사용자 아이디가 이미 존재합니다.");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setFilename(signUpRequest.getFilename());

        Role defaultRole = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("권한을 찾지 못했습니다."));
        user.setRole(defaultRole);

        userRepository.save(user);
    }

    public Cookie loginUser(String username, String password) {
        // 아이디로 사용자 정보 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));

        // 비밀번호 비교
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 같지 않습니다.");
        }

        // JWT 생성
        return jwtProvider.createUserToken(username, user.getRole());
    }

    public UserResponse getMe(UserDetailsServiceImpl userDetails) {
        User user = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with UserID: " + userDetails.getId()));

        return UserResponse.of(user);
    }
}
