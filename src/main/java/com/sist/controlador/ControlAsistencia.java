package com.sist.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Asistencia;
import com.sist.repositorio.RepositorioAsistencia;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Control/Actividad")
public class ControlAsistencia {

	@Autowired
	private RepositorioAsistencia repoAsis;

	
	@GetMapping("/Listar")
	public List<Asistencia> listar() {
		
		List<Asistencia> listaAsistencia = (List<Asistencia>) repoAsis.findAll();
		return listaAsistencia;
	}
	
	@PostMapping("/Registrar")
	public ResponseEntity<?> registrar(@RequestBody Asistencia datosAsis){
		
		Optional<Asistencia> asistenciaExistente = repoAsis
				.findById(datosAsis.getId_asistencia());
		
		if (asistenciaExistente.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("La asistencia ya esta registrada");
		}

		repoAsis.save(datosAsis);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Asistencia registrada");
	}
	
	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Asistencia> actualizar(@PathVariable Integer id, 
			@RequestBody Asistencia datosAsis){
		
		Optional<Asistencia> AsistenciaExiste = repoAsis.findById(id);
		
		if (AsistenciaExiste.isPresent()) {
			Asistencia original = AsistenciaExiste.get();
			
			original.setDescripcion_asistencia(datosAsis.getDescripcion_asistencia());
			
			Asistencia actualizado = repoAsis.save(original);
			return ResponseEntity.ok(actualizado);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/Borrar/{id}")
	public void borra(@PathVariable Integer id) {
		repoAsis.deleteById(id);
	}
}
