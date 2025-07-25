package com.sist.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Profesor;
import com.sist.repositorio.RepositorioProfesor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/Control/Profesor")
public class ControlProfesor {

	@Autowired
	private RepositorioProfesor repoProfe;

	@GetMapping("/Listar")
	public List<Profesor> listar() {
		List<Profesor> listaProfesores = (List<Profesor>) repoProfe.findAll();
		System.out.println(listaProfesores);
		return listaProfesores;
	}

	@GetMapping("/Buscador/{id}")
	public ResponseEntity<Profesor> buscar(@PathVariable("id") Integer id) {

		Optional<Profesor> profesorExiste = repoProfe.findById(id);
		if (profesorExiste.isPresent()) {

			return ResponseEntity.ok(profesorExiste.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}

	@PostMapping("/Registrar")
	public ResponseEntity<?> registrar(@RequestBody Profesor datosProfe) {

		Optional<Profesor> profesorExiste = repoProfe.findById(datosProfe.getId_profesor());
		if (profesorExiste.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("El profesor ya esta registrado");
		}

		repoProfe.save(datosProfe);
		return ResponseEntity.status(HttpStatus.CREATED).body("Profesor Inscripto");
	}

	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Profesor> actualizarProfesor(@PathVariable("id") Integer id,
			@RequestBody Profesor datosProfe) {

		Optional<Profesor> profesorExiste = repoProfe.findById(id);
		if (profesorExiste.isPresent()) {
			Profesor profeOriginal = profesorExiste.get();

			profeOriginal.setNombre_profesor(datosProfe.getNombre_profesor());
			profeOriginal.setApellido_profesor(datosProfe.getApellido_profesor());
			profeOriginal.setDNI_profesor(datosProfe.getDNI_profesor());

			Profesor actualizado = repoProfe.save(profeOriginal);
			return ResponseEntity.ok(actualizado);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@DeleteMapping("/Borrar/{id}")
	public void borrar(@PathVariable Integer Id) {
		repoProfe.deleteById(Id);
	}

}
