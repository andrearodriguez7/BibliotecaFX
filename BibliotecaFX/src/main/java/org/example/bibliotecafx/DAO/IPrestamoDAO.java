package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Prestamo;

import java.util.List;

public interface IPrestamoDAO {
    void registrarPrestamo(Prestamo prestamo);
    List<Prestamo> listarPrestamosActivos();
    List<Prestamo> obtenerHistorialPrestamosPorSocio(Integer idSocio);
}
