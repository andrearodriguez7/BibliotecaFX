package org.example.bibliotecafx.Gestion;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

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


}
