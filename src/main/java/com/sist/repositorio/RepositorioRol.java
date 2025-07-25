package com.sist.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Rol;
@Repository
public interface RepositorioRol extends CrudRepository<Rol, Integer>{
	Optional<Rol> findByRolNombre(String nombre);
}
