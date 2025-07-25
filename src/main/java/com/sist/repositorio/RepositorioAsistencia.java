package com.sist.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Asistencia;

@Repository
public interface RepositorioAsistencia extends CrudRepository<Asistencia, Integer>{

}
