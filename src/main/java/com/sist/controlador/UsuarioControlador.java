package com.sist.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.modelo.Rol;
import com.sist.modelo.Usuario;
import com.sist.repositorio.RepositorioRol;
import com.sist.repositorio.RepositorioUsuario;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/Control/usuarios")
public class UsuarioControlador {

    @Autowired
    private RepositorioUsuario repo;
    @Autowired
    private RepositorioRol rolRepo;
    
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        
        Rol rolUser = rolRepo.findByRolNombre("ADMIN").orElseThrow();
        usuario.setRoles(List.of(rolUser));
        
        repo.save(usuario);
        return ResponseEntity.ok("Usuario registrado");
    }
	
    @PutMapping("/actualizar-password/{username}")
    public ResponseEntity<String> putMethodName(@PathVariable String username, @RequestBody String nuevaPassword) {
        Usuario usa = repo.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no Encontrado"));
        
        usa.setPassword(encoder.encode(nuevaPassword));
        repo.save(usa);
        return ResponseEntity.ok("Contraseña Actualizada correctamente");
    }
    
}
