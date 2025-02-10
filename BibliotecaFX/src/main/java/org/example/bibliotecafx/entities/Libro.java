package org.example.bibliotecafx.entities;

public class Libro {
    private String isbn; // Clave primaria
    private String titulo;
    private String editorial;
    private int anio;
    private Autor autor; // Clave foránea

    public Libro(String isbn, String titulo, String editorial, int anio, Autor autor) {
        if (isbn == null || isbn.isEmpty()) throw new IllegalArgumentException("ISBN no puede estar vacío");
        if (anio < 0) throw new IllegalArgumentException("Año no puede ser negativo");

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
    public void setAnio(int anio) {
        if (anio < 0) throw new IllegalArgumentException("Año no puede ser negativo");
        this.anio = anio;
    }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anio=" + anio +
                ", autor=" + autor.getNombre() +
                '}';
    }
}
