package com.cloud.cqc.client.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.cqc.client.configure.security.JwtAuthenticationRequest;
import com.cloud.cqc.client.configure.security.JwtAuthenticationResponse;
import com.cloud.cqc.client.configure.security.JwtTokenUtil;
import com.cloud.cqc.framework.core.utils.RandomUtils;
import com.cloud.cqc.framework.mvc.BaseRestController;
import com.cloud.cqc.framework.mvc.http.RestEntity;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseRestController {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	/**
	 *
	 * 获取token
	 *
	 * @param authenticationRequest
	 *            请求参数
	 * @return
	 */
	@RequestMapping(value = "/access_token", method = RequestMethod.POST)
	public RestEntity<?> createAuthenticationToken(@Valid @RequestBody JwtAuthenticationRequest authenticationRequest)
			throws AuthenticationException {

		UserDetails details = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword());
		authRequest.setDetails(details);
		final Authentication authentication = authenticationManager.authenticate(authRequest);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		final String key = RandomUtils.randomString(6);

		final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername(), key);

		return resultOk(new JwtAuthenticationResponse(token, key));

	}

}
