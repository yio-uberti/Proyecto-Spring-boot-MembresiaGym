package com.sist;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sist.modelo.Rol;
import com.sist.repositorio.RepositorioRol;
import com.sist.servicio.UsuarioServicio;

@SpringBootApplication
public class MembresiaSistemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MembresiaSistemaApplication.class, args);

	}
	
}
