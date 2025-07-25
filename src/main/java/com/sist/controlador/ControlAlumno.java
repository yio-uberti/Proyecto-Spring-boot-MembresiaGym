package com.sist.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Alumno;
import com.sist.repositorio.RepositorioActividad;
import com.sist.repositorio.RepositorioAlumno;
import com.sist.repositorio.RepositorioAsistencia;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/Control/Alumno")
public class ControlAlumno {

	@Autowired
	private RepositorioAlumno repoAlumno;

	@Autowired
	private RepositorioAsistencia repoAsis;


	@GetMapping("/Listar")
	public List<Alumno> listar() {
		List<Alumno> listaAlumnos = (List<Alumno>) repoAlumno.findAll();
		System.out.println(listaAlumnos);
		return listaAlumnos;
	}

	@GetMapping("/Buscador/{id}")
	public ResponseEntity<Alumno> buscarAlumno(@PathVariable("id") Integer id) {
		
		Optional<Alumno> alumno = repoAlumno.findById(id);
		if (alumno.isPresent()) {
			return ResponseEntity.ok(alumno.get());
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	@PostMapping("/Registrar")
	public ResponseEntity<?> registrar(@RequestBody Alumno alum) {

		Optional<Alumno> alumnoExiste = repoAlumno.findById(alum.getId_alumnos());
		if (alumnoExiste.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.CONFLICT)
					.body("El alumno ya esta inscripto al gimnasio");
		}

		repoAlumno.save(alum);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body("Alumnos inscripto");
	}

	@PutMapping("/Actualizar/{id}")
	public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Integer id, 
			@RequestBody Alumno datosAlumno) {
		Optional<Alumno> alumnoExistente = repoAlumno.findById(id);

		if (alumnoExistente.isPresent()) {
			Alumno alumnoOriginal = alumnoExistente.get();

			// Actualizar campos básicos
			alumnoOriginal.setNombre_alumno(datosAlumno.getNombre_alumno());
			alumnoOriginal.setApellido_alumno(datosAlumno.getApellido_alumno());
			alumnoOriginal.setDNI_alumno(datosAlumno.getDNI_alumno());

			// Validar que el tipo_asistencia exista en la tabla asistencia
			if (datosAlumno.getTipo_asistencia() > 0) {
				boolean asistenciaValida = repoAsis.existsById(datosAlumno
						.getTipo_asistencia());
				
				if (asistenciaValida) {
					alumnoOriginal.setTipo_asistencia(datosAlumno
							.getTipo_asistencia());
				} else {
					return ResponseEntity.badRequest().build(); 
				}
			}

			Alumno actualizado = repoAlumno.save(alumnoOriginal);
			return ResponseEntity.ok(actualizado);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/Borrar/{id}")
	public void borrar(@PathVariable Integer id) {
		repoAlumno.deleteById(id);
	}

}
