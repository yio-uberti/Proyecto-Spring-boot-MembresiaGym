package com.sist.modelo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "clases", catalog = "reservas", schema = "")
@Data
@Builder
public class Clases {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_clase;
	@Column 
	private Integer id_alumnos;
	@Column
	private Integer id_actividad;
	@Column
	private String dia;
	@Column
	private LocalDate horario;
}
