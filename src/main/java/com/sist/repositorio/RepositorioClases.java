package com.sist.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Clases;

@Repository
public interface RepositorioClases extends CrudRepository<Clases, Integer>{

}
