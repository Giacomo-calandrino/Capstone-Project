package com.opns.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class LoginResponse {
	private String token;
	
	private Long id;
	private String username;
	private String email;	
	private String nome;
	private String cognome;
	//private String password;
	
	private int volume;


}

