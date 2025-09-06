package com.okBranding.back.repository;

import com.okBranding.back.models.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Integer> {

    /**
     * Metodo para verificar que no se repitan las credenciales de nombre de usuario
     * @param nombreUsuario
     * @return true o false
     */
    boolean existsByNombreUsuario(String nombreUsuario);

    /**
     * Metodo para buscar un registro de credenciales por nombre de usuario
     * @param nombreUsuario
     * @return
     */
    Optional<Credencial> findByNombreUsuario(String nombreUsuario);
}
