package com.opns.schede;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="schede")
@Builder
public class Scheda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="volume", nullable=false)
	private int volume;
	
	@Column(name="titolo", nullable=false)
	private String titolo;	
	
	@Column(name="testo", nullable=false)
	private String testo;
	
	@Column(name="imgUrl", nullable=false)
	private String imgUrl;
	
}
