package com.sist.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Actividad;
import com.sist.repositorio.RepositorioActividad;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/Control/Actividad")
public class ControlActividad {

	@Autowired
	private RepositorioActividad repoAct;
	
	@GetMapping("/Listar")
	public List<Actividad> listar() {
		
		List<Actividad> listaActividad = (List<Actividad>) repoAct.findAll();
		return listaActividad;
	}
	
	@PostMapping("/Registrar")
	public ResponseEntity<?> registrar(@RequestBody Actividad datosAct) {
		
		Optional<Actividad> actividadExistente = repoAct
				.findById(datosAct.getId_act());

		if (actividadExistente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("La actividad ya esta registrada");
		}
		
		repoAct.save(datosAct);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Actividad registrada");
	}
	
	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Actividad> actualizar(@PathVariable Integer id, 
			@RequestBody Actividad datosAct) {
		
		Optional<Actividad> actExistente = repoAct.findById(id);
		
		if (actExistente.isPresent()) {
			Actividad original = actExistente.get();
			
			original.setDescripcion_act(datosAct.getDescripcion_act());
			original.setId_profesor_act(datosAct.getId_profesor_act());
			
			Actividad actualizado = repoAct.save(original);
			return ResponseEntity.ok(actualizado);
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/Borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		repoAct.deleteById(id);
	}
	
}
