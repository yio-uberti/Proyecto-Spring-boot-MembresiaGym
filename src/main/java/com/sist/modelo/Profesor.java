package com.sist.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "profesor", catalog = "reservas", schema = "")
@Data
@Builder
public class Profesor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_profesor;
	@Column
	private String nombre_profesor;
	@Column
	private String apellido_profesor;
	@Column
	private Integer DNI_profesor;
	
	
}
