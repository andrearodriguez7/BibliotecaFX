package org.example.bibliotecafx.Gestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.bibliotecafx.DAO.AutorDAOImpl;
import org.example.bibliotecafx.DAO.SocioDAOImpl;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autor;
import org.example.bibliotecafx.entities.Libro;
import org.example.bibliotecafx.entities.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class Gestion {

    @FXML
    private VBox LibroInterfaz;

    @FXML
    private VBox AutorInterfaz;

    @FXML
    private VBox InicioInterfaz;

    @FXML
    private VBox PrestamoInterfaz;

    @FXML
    private VBox SocioInterfaz;

    // Objetos Autor

    @FXML
    private TextField NombreAutor;

    @FXML
    private TextField NacionalidadAutor;

    @FXML
    private TextField IdAutor;

    @FXML
    TextArea AreaAutores;

    private final AutorDAOImpl AutorDAO = new AutorDAOImpl();

    // Objetos Socio

    @FXML
    private TextField NombreSocio;

    @FXML
    private TextField DireccionSocio;

    @FXML
    private TextField TelefonoSocio;

    @FXML
    private TextField IdSocio;

    @FXML
    TextArea AreaSocios;

    private final SocioDAOImpl socioDAO = new SocioDAOImpl();

    public void BotonAutor(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/AutorInterfaz.fxml");
    }

    public void BotonLibro(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/LibroInterfaz.fxml");
    }

    public void BotonPrestamo(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/PrestamoInterfaz.fxml");
    }

    public void BotonSocio(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(InicioInterfaz, "/org/example/bibliotecafx/SocioInterfaz.fxml");
    }


    public void BotonSalir(ActionEvent actionEvent) {
        System.exit(0);
    }

    // Botones Autor
    public void AgregarAutor(ActionEvent actionEvent) {
        String nombre = NombreAutor.getText();
        String nacionalidad = NacionalidadAutor.getText();

        if (nombre.isEmpty() || nacionalidad.isEmpty()) {
            AreaAutores.setText("Por favor, complete todos los campos.");
            return;
        }

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);

        AutorDAO.agregarAutor(autor);
        AreaAutores.setText("Autor añadido correctamente.");
    }

    public void EliminarAutor(ActionEvent actionEvent) {
        String idTexto = IdAutor.getText();
        if (idTexto.isEmpty()) {
            AreaAutores.setText("Ingrese un ID para eliminar.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            AutorDAO.eliminarAutor(id);
            AreaAutores.setText("Autor eliminado correctamente.");
        } catch (NumberFormatException e) {
            AreaAutores.setText("El ID debe ser un número válido.");
        }
    }

    public void BuscarAutor(ActionEvent actionEvent) {
        String nombre = NombreAutor.getText();
        if (nombre.isEmpty()) {
            AreaAutores.setText("Ingrese un Nombre para buscar.");
            return;
        }

        Autor autor = AutorDAO.obtenerAutor(nombre);
        if (autor != null) {
            NombreAutor.setText(autor.getNombre());
            NacionalidadAutor.setText(autor.getNacionalidad());
            AreaAutores.setText("Autor encontrado: \n" + autor.getNombre() + " - " + autor.getNacionalidad());
        } else {
            AreaAutores.setText("No se encontró ningún autor con ese nombre.");
        }
    }

    public void ListarAutores(ActionEvent actionEvent) {
        List<Autor> autores = AutorDAO.listaAutores();
        if (autores.isEmpty()) {
            AreaAutores.setText("No hay autores registrados.");
        } else {
            StringBuilder resultado = new StringBuilder("Lista de Autores:\n");
            for (Autor autor : autores) {
                resultado.append("ID: ").append(autor.getId())
                        .append(" - Nombre: ").append(autor.getNombre())
                        .append(" - Nacionalidad: ").append(autor.getNacionalidad())
                        .append("\n");
            }
            AreaAutores.setText(resultado.toString());
        }
    }

    public void ModificarAutor(ActionEvent actionEvent) {
        String idTexto = IdAutor.getText();
        String nombre = NombreAutor.getText();
        String nacionalidad = NacionalidadAutor.getText();

        if (idTexto.isEmpty() || nombre.isEmpty() || nacionalidad.isEmpty()) {
            AreaAutores.setText("Complete todos los campos para modificar un autor.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Autor autor = AutorDAO.obtenerAutorPorId(id);
            if (autor != null) {
                autor.setNombre(nombre);
                autor.setNacionalidad(nacionalidad);
                AutorDAO.actualizarAutor(autor);
                AreaAutores.setText("Autor modificado correctamente.");
            } else {
                AreaAutores.setText("No se encontró ningún autor con ese ID.");
            }
        } catch (NumberFormatException e) {
            AreaAutores.setText("El ID debe ser un número válido.");
        }

    }

    public void VolverAutor(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(AutorInterfaz, "/org/example/bibliotecafx/InicioInterfaz.fxml");
    }

    public void LimpiarAutor() {
        NombreAutor.clear();
        NacionalidadAutor.clear();
        IdAutor.clear();
        AreaAutores.clear();
    }

    // Botones Socio
    public void AgregarSocio(ActionEvent actionEvent) {
        String nombre = NombreSocio.getText();
        String direccion = DireccionSocio.getText();
        String telefono = TelefonoSocio.getText();

        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            AreaSocios.setText("Todos los campos son obligatorios.");
            return;
        }

        Socio socio = new Socio(nombre, direccion, telefono);
        socioDAO.agregarSocio(socio);
        AreaSocios.setText("Socio agregado exitosamente.");
    }

    public void LimpiarSocio() {
        NombreSocio.clear();
        DireccionSocio.clear();
        TelefonoSocio.clear();
        IdSocio.clear();
        AreaSocios.clear();

    }

    public void VolverSocio(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(SocioInterfaz, "/org/example/bibliotecafx/InicioInterfaz.fxml");
    }

    public void ModificarSocio(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(IdSocio.getText());

            // Buscar el socio en la base de datos
            Socio socio = socioDAO.obtenerSocio(id);
            if (socio == null) {
                AreaSocios.setText("No se encontró un socio con el ID ingresado.");
                return;
            }

            // Actualizar los datos del socio existente
            socio.setNombre(NombreSocio.getText());
            socio.setDireccion(DireccionSocio.getText());
            socio.setTelefono(TelefonoSocio.getText());

            if (socio.getNombre().isEmpty() || socio.getDireccion().isEmpty() || socio.getTelefono().isEmpty()) {
                AreaSocios.setText("Todos los campos son obligatorios.");
                return;
            }

            // Guardar los cambios en la base de datos
            socioDAO.actualizarSocio(socio);
            AreaSocios.setText("Socio actualizado correctamente.");

        } catch (NumberFormatException e) {
            AreaSocios.setText("Ingrese un ID válido.");
        }
    }

    public void ListarSocios(ActionEvent actionEvent) {
        List<Socio> socios = socioDAO.listarTodosLosSocios();
        if (socios.isEmpty()) {
            AreaSocios.setText("No hay socios registrados.");
        } else {
            StringBuilder lista = new StringBuilder("Lista de Socios:\n");
            for (Socio s : socios) {
                lista.append("ID: ").append(s.getId())
                        .append(", Nombre: ").append(s.getNombre())
                        .append(", Dirección: ").append(s.getDireccion())
                        .append(", Teléfono: ").append(s.getTelefono()).append("\n");
            }
            AreaSocios.setText(lista.toString());
        }
    }

    public void BuscarSocio(ActionEvent actionEvent) {
        String nombre = NombreSocio.getText().trim();
        String telefono = TelefonoSocio.getText().trim();
        int filledCount = 0;
        if (!nombre.isEmpty()) filledCount++;
        if (!telefono.isEmpty()) filledCount++;

        if (filledCount > 1) {
            showAlert(Alert.AlertType.ERROR, "Error", "Solo se puede buscar por un parámetro: Nombre o Teléfono.");
            return;
        }

        String parametro = !nombre.isEmpty() ? nombre : telefono;

        if (parametro.isEmpty()) {
            AreaSocios.setText("Ingrese un nombre o teléfono para buscar.");
            return;
        }

        List<Socio> sociosEncontrados = socioDAO.buscarPorParametro(parametro);
        AreaSocios.clear();

        if (sociosEncontrados.isEmpty()) {
            AreaSocios.setText("No se encontraron socios con ese criterio.");
            showAlert(Alert.AlertType.WARNING, "No encontrado", "No se encontró ningún socio con ese dato.");
        } else if (sociosEncontrados.size() == 1) {
            Socio socio = sociosEncontrados.get(0);
            NombreSocio.setText(socio.getNombreSocio());
            DireccionSocio.setText(socio.getDireccion());
            TelefonoSocio.setText(socio.getTelefono());
            IdSocio.setText(String.valueOf(socio.getIdSocio()));
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Socio encontrado. Datos cargados en los campos.");
        } else {
            StringBuilder resultado = new StringBuilder();
            for (Socio socio : sociosEncontrados) {
                resultado.append(socio.toString()).append("\n");
            }
            AreaSocios.setText(resultado.toString());
            showAlert(Alert.AlertType.INFORMATION, "Éxito", "Múltiples socios encontrados.");
        }
    }


    public void EliminarSocio(ActionEvent actionEvent) {
        try {
            int id = Integer.parseInt(IdSocio.getText());
            socioDAO.eliminarSocio(id);
            AreaSocios.setText("Socio eliminado correctamente.");

        } catch (NumberFormatException e) {
            AreaSocios.setText("Ingrese un ID válido.");
        }
    }


    // Botones Libro


    // Botones Préstamo
}




