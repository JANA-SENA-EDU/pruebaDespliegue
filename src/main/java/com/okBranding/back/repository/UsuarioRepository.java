package com.okBranding.back.repository;

import com.okBranding.back.models.Credencial;
import com.okBranding.back.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

   /**
    * Metodo para traer un nombre de usuario por NombreUsuario
    * @param nombreUsuario
    * @return
    */
   Optional<Usuario> findByCredencialNombreUsuario(String nombreUsuario);

   /**
    * Metodo para verificar si ya existe un correo registrado en la base de datos
    * @param correo
    * @return
    */
   boolean existsByCorreo(String correo);

   /**
    * Metodo para verificar si ya existe un correo registrado en la base de datos
    * @param telefono
    * @return
    */
   boolean existsBytelefono(String telefono);

   /**
    * Metodo para verificar si ya existe un correo registrado en la base de datos
    * @param nombre
    * @return
    */
   boolean existsByNombre(String nombre);

   /**
    * Metodo para buscar un Usuario por sus credenciales
    * @param credencial
    * @return
    */
   Optional<Usuario> findByCredencial(Credencial credencial);

   /**
    * Metodo para validar un usuario por correo
    * @param correo
    * @param idUsuario
    * @return
    */
   boolean existsByCorreoAndIdUsuarioNot(String correo, Integer idUsuario);

   /**
    * Metodo para validar un usuario por telefono
    * @param telefono
    * @param idUsuario
    * @return
    */
   boolean existsByTelefonoAndIdUsuarioNot(String telefono, Integer idUsuario);

   /**
    * Metodo par validar un usaruio por nombre
    * @param nombre
    * @param idUsuario
    * @return
    */
   boolean existsByNombreAndIdUsuarioNot(String nombre, Integer idUsuario);
}
