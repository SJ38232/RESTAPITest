package com.internProject.SJ.api.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.internProject.SJ.api.entity.User;
import com.internProject.SJ.api.jwt.JwtTokenProvider;
import com.internProject.SJ.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) {
		log.info(email);
		User user = userRepository.findByEmail(email);
		log.info(user.getEmail());
		UserDetails userDetails = userRepository.findByEmail(email);
		log.info(userDetails.getUsername());
		return userDetails;
	}
}
