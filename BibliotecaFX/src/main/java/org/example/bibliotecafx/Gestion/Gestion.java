package org.example.bibliotecafx.Gestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.bibliotecafx.DAO.AutorDAOImpl;

import java.io.IOException;

public class Gestion {

    // Interfaces
    @FXML
    private VBox InicioInterfaz;
    @FXML
    private VBox SocioInterfaz;
    @FXML
    private VBox AutorInterfaz;
    @FXML
    private VBox LibroInterfaz;
    @FXML
    private VBox PrestamoInterfaz;

    @FXML
    private TextField nombreAutorField;
    @FXML
    private TextField nacionalidadAutorField;
    @FXML
    private Label resultadoBusquedaLabel;

    private final AutorDAOImpl autorDAO = new AutorDAOImpl();

    // Botones
    @FXML
    private void BotonAutor() throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/AutorInterfaz.fxml");
    }

    @FXML
    private void BotonLibro() throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/LibroInterfaz.fxml");
    }

    @FXML
    private void BotonPrestamo() throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/PrestamoInterfaz.fxml");
    }

    @FXML
    private void BotonSocio() throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/SocioInterfaz.fxml");
    }

    @FXML
    private void BotonSalir() {
        System.exit(0);
    }


    public void listarAutores(ActionEvent actionEvent) {
    }

    public void buscarAutor(ActionEvent actionEvent) {
    }

    public void eliminarAutor(ActionEvent actionEvent) {
    }

    public void modificarAutor(ActionEvent actionEvent) {
    }

    public void anadirAutor(ActionEvent actionEvent) {


    }

    @FXML
    private void VolverInicioAutor() throws IOException {
        new CargarInterfaz(AutorInterfaz, "/org/example/bibliotecafx/InicioInterfaz.fxml");
    }
}
