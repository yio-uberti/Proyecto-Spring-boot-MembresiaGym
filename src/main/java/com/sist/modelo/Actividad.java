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
@Table(name = "actividad", catalog = "reservas", schema = "")
@Data
@Builder
public class Actividad {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id_act;
	@Column
	private String descripcion_act;
	@Column
	private Integer id_profesor_act;
	
}
