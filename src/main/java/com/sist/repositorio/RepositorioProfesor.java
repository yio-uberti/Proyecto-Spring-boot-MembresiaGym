package com.sist.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Profesor;

@Repository
public interface RepositorioProfesor extends CrudRepository<Profesor, Integer>{

}
