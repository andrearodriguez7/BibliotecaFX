package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Socio;

import java.util.List;

public interface ISocioDAO {
    void agregarSocio(Socio socio);
    Socio obtenerSocio(Integer id);
    void actualizarSocio(Socio socio);
    void eliminarSocio(Integer id);
    List<Socio> listarTodosLosSocios();

    List<Socio> buscarPorParametro(String parametro);
}
