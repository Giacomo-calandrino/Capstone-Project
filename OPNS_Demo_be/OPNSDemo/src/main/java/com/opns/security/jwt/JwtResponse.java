package com.opns.security.jwt;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private int volume;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username,int volume, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.volume = volume;
		this.roles = roles;
	}
}
