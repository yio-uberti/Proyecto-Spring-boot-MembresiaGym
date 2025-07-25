package com.sist.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Actividad;

@Repository
public interface RepositorioActividad extends CrudRepository<Actividad, Integer>{

}
