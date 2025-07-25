package com.sist.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Clases;
import com.sist.repositorio.RepositorioClases;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/Control/Clases")
public class ControlClases {

	@Autowired
	private RepositorioClases repoClases;
	
	@GetMapping("/Listar")
	public List<Clases> listar() {
		List<Clases> listaClases = (List<Clases>) repoClases.findAll();
		return listaClases;
	}
	
	@PostMapping("/Registrar")
	public ResponseEntity<?> registrar(@RequestBody Clases datosClase) {
		
		Optional<Clases> claseExistente = repoClases
				.findById(datosClase.getId_clase());
		
		if (claseExistente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("La Clase ya fue aignada");
		}
		
		repoClases.save(datosClase);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Clase registrada");
	}
	
	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Clases> actualizar(@PathVariable Integer id, 
			 @RequestBody Clases datosClases) {
		
		Optional<Clases> claseExistente = repoClases.findById(id);
		
		if (claseExistente.isPresent()) {
			Clases original = claseExistente.get();
			
			original.setId_alumnos(datosClases.getId_alumnos());
			original.setId_actividad(datosClases.getId_actividad());
			original.setDia(datosClases.getDia());
			original.setHorario(datosClases.getHorario());
		
			Clases actualizado = repoClases.save(original);
			return ResponseEntity.ok(actualizado);
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/Borrar/{id}")
	public void borra(@PathVariable Integer id) {
		repoClases.deleteById(id);
	}
	
}
