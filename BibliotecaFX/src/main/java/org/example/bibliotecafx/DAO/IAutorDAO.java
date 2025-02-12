package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autor;

import java.util.List;

public interface IAutorDAO {
    void agregarAutor(Autor autor); // No tiene return tan solo ejecuta una acción
    Autor obtenerAutor(String nombre); // Tiene return y por ende devuelve un valor, objeto, lista...
    void actualizarAutor(Autor autor);
    void eliminarAutor(Integer id);

    List<Autor> listaAutores();

    Autor obtenerAutorPorId(Integer id);
}
