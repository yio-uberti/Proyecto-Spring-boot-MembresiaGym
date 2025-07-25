package com.sist.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Alumno;

@Repository
public interface RepositorioAlumno extends CrudRepository<Alumno, Integer>{

}
