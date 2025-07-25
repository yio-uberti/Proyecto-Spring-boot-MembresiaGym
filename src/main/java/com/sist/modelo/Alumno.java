package com.sist.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alumnos", catalog = "reservas", schema = "")
@Getter
@Setter
@RequiredArgsConstructor
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_alumnos;
	@Column
	private String nombre_alumno;
	@Column
	private String apellido_alumno;
	@Column
	private Integer DNI_alumno;
	@Column
	private Integer tipo_asistencia;
	
}
