package com.cloud.kpi.client.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class JwtTest {

	public static void main(String[] args) {
		
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		System.out.println(encoder.encodePassword("123456", "433735"));
	}
}
