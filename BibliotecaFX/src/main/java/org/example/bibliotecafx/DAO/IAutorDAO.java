package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Autor;

public interface IAutorDAO {
    void agregarAutor(Autor autor);
    Autor obtenerAutor(Integer id);
    void actualizarAutor(Autor autor);
    void eliminarAutor(Integer id);
}
