package com.sist.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rol", catalog = "reservas", schema = "")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
	@Column(name = "rol_nombre")
    private String rolNombre;
    
}
