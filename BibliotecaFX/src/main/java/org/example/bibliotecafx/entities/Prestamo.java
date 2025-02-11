package org.example.bibliotecafx.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    @OneToOne
    @JoinColumn(name = "idSocio", unique = true)
    private Socio socio;

    @OneToOne
    @JoinColumn(name = "isbn", unique = true)
    private Libro libro;

    public Prestamo() {}

    public Prestamo(LocalDate fechaPrestamo, LocalDate fechaDevolucion, Socio socio, Libro libro) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.socio = socio;
        this.libro = libro;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public LocalDate getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDate fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public Socio getSocio() { return socio; }
    public void setSocio(Socio socio) { this.socio = socio; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
}

