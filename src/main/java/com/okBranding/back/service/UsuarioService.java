package com.okBranding.back.service;

import com.okBranding.back.dto.LoginRequestDTO;
import com.okBranding.back.dto.UsuarioRequestDTO;
import com.okBranding.back.dto.UsuarioResponseDTO;

import java.util.List;
import java.util.Map;

public interface UsuarioService {
    /**
     * Metodo apara registrar un Usuario
     * @param usuarioRequestDTO
     * @return la respuesta si es exitosa o no
     */
    Map<String,Object> registrarUsuario(UsuarioRequestDTO usuarioRequestDTO);

    /**
     * Metodo para loguear un usuario
     * @param loginRequestDTO
     * @return la respuesta si es exitosa o no
     */
    public Map<String, Object> login(LoginRequestDTO loginRequestDTO);

    /**
     * Metodo para listar todos los usuarios
     * @return lista de usuarios
     */
    List<UsuarioResponseDTO> listarUsuarios();

    /**
     * Metodo para actualizar un usuario
     * @param idUsuario identificador del usuario
     * @param usuarioRequestDTO datos actualizados
     * @return respuesta con estado de la actualización
     */
    Map<String, Object> actualizarUsuario(Integer idUsuario, UsuarioRequestDTO usuarioRequestDTO);

    /**
     * Metodo para eliminar (desactivar) un usuario
     * @param idUsuario identificador del usuario
     * @return respuesta con estado de la eliminación
     */
    Map<String, Object> eliminarUsuario(Integer idUsuario);
}
