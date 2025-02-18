package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Libro;

import java.util.List;

public interface ILibroDAO {
    void agregarLibro(Libro libro);
    Libro obtenerLibro(String isbn);
    void actualizarLibro(Libro libro);
    void eliminarLibro(String isbn);
    List<Libro> listarLibrosDisponibles();

    List<Libro> obtenerLibrosPorAutor(String nombreAutor);

    List<Libro> obtenerLibrosPorTitulo(String tituloLibro);
}
