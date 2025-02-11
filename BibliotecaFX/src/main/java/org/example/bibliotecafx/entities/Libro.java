package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    private String isbn;

    private String titulo;
    private String editorial;
    private int anio;

    @OneToOne
    @JoinColumn(name = "idAutor", unique = true)
    private Autor autor;

    @OneToOne(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Prestamo prestamo;

    public Libro() {}

    public Libro(String isbn, String titulo, String editorial, int anio, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editorial = editorial;
        this.anio = anio;
        this.autor = autor;
    }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public Prestamo getPrestamo() { return prestamo; }
    public void setPrestamo(Prestamo prestamo) { this.prestamo = prestamo; }
}

