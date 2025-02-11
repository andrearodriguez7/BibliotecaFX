package org.example.bibliotecafx.entities;

import java.util.Date;

public class Prestamo {
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private Socio socio; // Clave foránea
    private Libro libro; // Clave foránea

    public Prestamo(Date fechaPrestamo, Date fechaDevolucion, Socio socio, Libro libro) {
        if (fechaPrestamo == null) throw new IllegalArgumentException("Fecha de préstamo no puede ser nula");
        if (fechaDevolucion != null && fechaDevolucion.before(fechaPrestamo))
            throw new IllegalArgumentException("Fecha de devolución no puede ser antes de la fecha de préstamo");

        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.socio = socio;
        this.libro = libro;
    }

    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) {
        if (fechaPrestamo == null) throw new IllegalArgumentException("Fecha de préstamo no puede ser nula");
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) {
        if (fechaDevolucion != null && fechaDevolucion.before(fechaPrestamo))
            throw new IllegalArgumentException("Fecha de devolución no puede ser antes de la fecha de préstamo");
        this.fechaDevolucion = fechaDevolucion;
    }

    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }

    @Override
    public String toString() {
        return "Toma{" +
                "fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + (fechaDevolucion != null ? fechaDevolucion : "No devuelto") +
                ", socio=" + socio.getNombre() +
                ", libro=" + libro.getTitulo() +
                '}';
    }
}
