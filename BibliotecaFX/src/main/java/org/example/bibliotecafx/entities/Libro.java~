package org.example.bibliotecafx.entities;

import jakarta.persistence.*;

@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String isbn;
    private String editorial;
    private int anyoPublicacion;

    // Relación muchos a uno (Un libro tiene un autor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false) // Clave foránea con restricción de no nulo
    private Autor autor;

    public Libro() {}

    public Libro(String titulo, String isbn, String editorial, int anyoPublicacion, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.editorial = editorial;
        this.anyoPublicacion = anyoPublicacion;
        this.autor = autor;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    public int getAnyoPublicacion() { return anyoPublicacion; }
    public void setAnyoPublicacion(int anyoPublicacion) { this.anyoPublicacion = anyoPublicacion; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anyoPublicacion=" + anyoPublicacion +
                ", autor=" + (autor != null ? autor.getNombre() : "Desconocido") +
                '}';
    }
}



