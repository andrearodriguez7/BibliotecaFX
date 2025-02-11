package org.example.bibliotecafx.entities;

public class Socio {
    private int id; // Clave primaria
    private String nombre;
    private String telefono;
    private String direccion;

    public Socio(int id, String nombre, String telefono, String direccion) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser mayor que 0");
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("ID debe ser mayor que 0");
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return "Socio{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
