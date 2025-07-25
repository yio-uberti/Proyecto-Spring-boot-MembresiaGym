package com.sist.repositorio;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sist.modelo.Usuario;

@Repository
public interface RepositorioUsuario extends CrudRepository<Usuario, Long>{
	Optional<Usuario> findByUsername(String username);
}
