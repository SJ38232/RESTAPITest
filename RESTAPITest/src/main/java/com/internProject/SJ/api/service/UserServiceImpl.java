package com.internProject.SJ.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.internProject.SJ.api.entity.Response;
import com.internProject.SJ.api.entity.User;
import com.internProject.SJ.api.jwt.JwtToken;
import com.internProject.SJ.api.jwt.JwtTokenProvider;
import com.internProject.SJ.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserRepository userRepository;
	private final Response response;
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<?> register(User userParam) {
		if(userRepository.existsByEmail(userParam.getEmail())) {
			return response.fail("이미 회원가입 된 이메일입니다.", HttpStatus.BAD_REQUEST);
		}
		User user = User.builder()
				.name(userParam.getName())
				.password(passwordEncoder.encode(userParam.getPassword()))
				.email(userParam.getEmail())
				.authority("USER")
				.build();
		userRepository.save(user);
		
		return response.success("회원가입에 성공했습니다.");
	}

	@Override
	public ResponseEntity<?> login(User userParam) {
		if(!userRepository.existsByEmail(userParam.getEmail())) {
			return response.fail("가입되지 않은 이메일입니다", HttpStatus.BAD_REQUEST);
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userParam.getUsername(),userParam.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		log.info(authentication.getAuthorities().toString());
		JwtToken jwtToken =jwtTokenProvider.generateToken(authentication);
		log.info(jwtToken.getAccessToken());
		return response.success(jwtToken,"로그인에 성공하였습니다", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> logout(User userParam) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
