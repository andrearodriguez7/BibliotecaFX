package org.example.bibliotecafx.Gestion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.example.bibliotecafx.DAO.AutorDAOImpl;
import org.example.bibliotecafx.DAO.LibroDAOImpl;
import org.example.bibliotecafx.DAO.PrestamoDAOImpl;
import org.example.bibliotecafx.DAO.SocioDAOImpl;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autor;
import org.example.bibliotecafx.entities.Libro;
import org.example.bibliotecafx.entities.Prestamo;
import org.example.bibliotecafx.entities.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDate;
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

    // Botones Libro

    @FXML
    private TextField TituloLibro;

    @FXML
    private TextField ISBNLibro;

    @FXML
    private TextField AnyoPublicacionLibro;

    @FXML
    private TextField AutorLibro;

    @FXML
    private TextField EditorialLibro;

    @FXML
    TextArea AreaLibros;

    private final LibroDAOImpl libroDAO = new LibroDAOImpl();

    // Botones Prestamos

    @FXML
    private TextField LibroIsbn;

    @FXML
    private TextField SocioId;

    @FXML
    private DatePicker FechaPrestamo;

    @FXML
    private DatePicker FechaDevolucion;

    @FXML
    private TextField ListarLibrosPrestados;

    @FXML
    private TextField ListarHistorial;


    @FXML
    TextArea AreaPrestamos;

    private PrestamoDAOImpl prestamoDAO = new PrestamoDAOImpl();


    // Botones

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

        // Contamos cuántos campos están llenos
        if (!nombre.isEmpty()) filledCount++;
        if (!telefono.isEmpty()) filledCount++;

        // Si ambos están llenos, mostramos un mensaje de error
        if (filledCount > 1) {
            AreaSocios.setText("Solo se puede buscar por un parámetro: Nombre o Teléfono.");
            return;
        }

        String parametro = !nombre.isEmpty() ? nombre : telefono;

        // Si no se ha introducido ningún valor, mostramos un mensaje de error
        if (parametro.isEmpty()) {
            AreaSocios.setText("Ingrese un nombre o teléfono para buscar.");
            return;
        }

        // Realizamos la búsqueda de socios
        List<Socio> sociosEncontrados = socioDAO.buscarPorParametro(parametro);
        AreaSocios.clear();

        if (sociosEncontrados.isEmpty()) {
            AreaSocios.setText("No se encontraron socios con ese criterio.");
        } else if (sociosEncontrados.size() == 1) {
            // Si encontramos un solo socio, cargamos los datos en los campos
            Socio socio = sociosEncontrados.get(0);
            NombreSocio.setText(socio.getNombre());
            DireccionSocio.setText(socio.getDireccion());
            TelefonoSocio.setText(socio.getTelefono());
            IdSocio.setText(String.valueOf(socio.getId()));
            AreaSocios.setText("Socio encontrado. Datos cargados en los campos.");
        } else {
            // Si encontramos múltiples socios, mostramos la lista
            StringBuilder resultado = new StringBuilder();
            for (Socio socio : sociosEncontrados) {
                resultado.append(socio.toString()).append("\n");
            }
            AreaSocios.setText(resultado.toString());
            AreaSocios.setText("Múltiples socios encontrados.");
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

    public void AgregarLibro(ActionEvent actionEvent) {
        String titulo = TituloLibro.getText();
        String isbn = ISBNLibro.getText();
        int anyo = Integer.parseInt(AnyoPublicacionLibro.getText());
        String aux = AutorLibro.getText();

        Autor autor = AutorDAO.obtenerAutor(aux);
        if (autor == null) {
            AreaLibros.setText("Error: El autor no existe en la base de datos.");
            return;
        }

        String editorial = EditorialLibro.getText();

        Libro libro = new Libro(titulo, isbn, editorial, anyo, autor);
        libroDAO.agregarLibro(libro);
        AreaLibros.setText("Libro agregado correctamente.");
    }
    public void EliminarLibro(ActionEvent actionEvent) {
        String isbn = ISBNLibro.getText();
        libroDAO.eliminarLibro(isbn);
        AreaLibros.setText("Libro eliminado correctamente.");
    }

    public void ModificarLibro(ActionEvent actionEvent) {
        // Recuperar los valores de los campos de texto
        String titulo = TituloLibro.getText();
        String isbn = ISBNLibro.getText();
        int anyo = Integer.parseInt(AnyoPublicacionLibro.getText());
        String aux = AutorLibro.getText();

        // Obtener el autor, al igual que en el método AgregarLibro
        Autor autor = AutorDAO.obtenerAutor(aux);
        if (autor == null) {
            AreaLibros.setText("Error: El autor no existe en la base de datos.");
            return;
        }

        String editorial = EditorialLibro.getText();

        // Buscar el libro en la base de datos por el ISBN
        Libro libro = libroDAO.obtenerLibro(isbn);
        if (libro == null) {
            AreaLibros.setText("Error: No se encontró el libro con el ISBN proporcionado.");
            return;
        }

        // Modificar los campos del libro con los valores nuevos
        libro.setTitulo(titulo);
        libro.setAnyoPublicacion(anyo);
        libro.setEditorial(editorial);
        libro.setAutor(autor);

        // Actualizar el libro en la base de datos
        libroDAO.actualizarLibro(libro);

        // Mensaje de éxito
        AreaLibros.setText("Libro modificado correctamente.");
    }

    public void BuscarLibro(ActionEvent actionEvent) {
        // Obtener los valores de los campos de búsqueda
        String isbn = ISBNLibro.getText();
        String autor = AutorLibro.getText();
        String titulo = TituloLibro.getText();

        // Contar cuántos campos están llenos
        int camposLlenos = 0;
        if (!isbn.isEmpty()) camposLlenos++;
        if (!autor.isEmpty()) camposLlenos++;
        if (!titulo.isEmpty()) camposLlenos++;

        // Validación: solo uno de los campos debe estar lleno
        if (camposLlenos == 0) {
            AreaLibros.setText("Por favor, complete al menos un campo de búsqueda (ISBN, Autor o Título).");
            return;
        } else if (camposLlenos > 1) {
            AreaLibros.setText("Solo puede completar uno de los campos de búsqueda (ISBN, Autor o Título).");
            return;
        }

        // Realizamos la búsqueda según el parámetro completado
        Libro libro = null;

        if (!isbn.isEmpty()) {
            libro = libroDAO.obtenerLibro(isbn);  // Buscar por ISBN
        } else if (!autor.isEmpty()) {
            // Buscar por autor (asegurándonos que solo un parámetro es llenado)
            List<Libro> librosPorAutor = libroDAO.obtenerLibrosPorAutor(autor);
            if (!librosPorAutor.isEmpty()) {
                libro = librosPorAutor.get(0);  // Supongo que queremos el primer libro encontrado
            }
        } else if (!titulo.isEmpty()) {
            // Buscar por título
            List<Libro> librosPorTitulo = libroDAO.obtenerLibrosPorTitulo(titulo);
            if (!librosPorTitulo.isEmpty()) {
                libro = librosPorTitulo.get(0);  // Supongo que queremos el primer libro encontrado
            }
        }

        // Mostrar el resultado de la búsqueda
        if (libro != null) {
            TituloLibro.setText(libro.getTitulo());
            AutorLibro.setText(libro.getAutor().getNombre());
            AnyoPublicacionLibro.setText(String.valueOf(libro.getAnyoPublicacion()));
            EditorialLibro.setText(libro.getEditorial());
            AreaLibros.setText("Libro encontrado.");
        } else {
            AreaLibros.setText("Libro no encontrado.");
        }
    }

    public void ListarLibro(ActionEvent actionEvent) {
        List<Libro> libros = libroDAO.listarLibrosDisponibles();  // Llama al método actualizado
        StringBuilder sb = new StringBuilder("Libros disponibles:\n");

        for (Libro libro : libros) {
            sb.append(libro.toString()).append("\n");
        }

        AreaLibros.setText(sb.toString());
    }

    public void VolverLibro(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(LibroInterfaz, "/org/example/bibliotecafx/InicioInterfaz.fxml");
    }

    public void LimpiarLibro() {
        TituloLibro.clear();
        ISBNLibro.clear();
        AnyoPublicacionLibro.clear();
        AutorLibro.clear();
        EditorialLibro.clear();
        AreaSocios.clear();
    }


    // Botones Préstamo
    public void RegistroPrestamos(ActionEvent actionEvent) {
        try {
            // Obtener los datos de la interfaz
            String socioIdStr = SocioId.getText();
            String libroIsbn = LibroIsbn.getText();
            LocalDate fechaPrestamo = FechaPrestamo.getValue();
            LocalDate fechaDevolucion = FechaDevolucion.getValue();

            // Verificar que los campos no estén vacíos
            if (socioIdStr.isEmpty() || libroIsbn.isEmpty() || fechaPrestamo == null || fechaDevolucion == null) {
                AreaPrestamos.setText("Por favor, complete todos los campos.\n");
                return;
            }

            // Convertir el ID del socio a entero
            Integer socioId = Integer.parseInt(socioIdStr);

            // Buscar el socio y el libro por los identificadores
            Socio socio = socioDAO.obtenerSocio(socioId);  // Asegúrate de tener el DAO para Socio
            Libro libro = libroDAO.obtenerLibro(libroIsbn);     // Asegúrate de tener el DAO para Libro

            // Verificar que el socio y el libro existen
            if (socio == null) {
                AreaPrestamos.setText("Socio no encontrado.\n");
                return;
            }

            if (libro == null) {
                AreaPrestamos.setText("Libro no encontrado.\n");
                return;
            }

            // Crear el objeto Prestamo
            Prestamo prestamo = new Prestamo();
            prestamo.setSocio(socio);
            prestamo.setLibro(libro);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);

            // Registrar el préstamo en la base de datos
            prestamoDAO.registrarPrestamo(prestamo);

            // Mostrar un mensaje en el área de texto
            AreaPrestamos.setText("Préstamo registrado correctamente.\n");

        } catch (Exception e) {
            AreaPrestamos.setText("Error al registrar el préstamo.\n");
            e.printStackTrace();
        }
    }


    public void ListarLibrosPrestados(ActionEvent actionEvent) {
        List<Prestamo> prestamosActivos = prestamoDAO.listarPrestamosActivos();

        // Limpiar área de texto antes de mostrar los resultados
        AreaPrestamos.clear();

        if (prestamosActivos != null && !prestamosActivos.isEmpty()) {
            // Mostrar los préstamos activos en el área de texto
            for (Prestamo prestamo : prestamosActivos) {
                String info = "ID Prestamo: " + prestamo.getId() + "\n" +
                        "Libro: " + prestamo.getLibro().getTitulo() + "\n" +
                        "Fecha de Préstamo: " + prestamo.getFechaPrestamo() + "\n" +
                        "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n";
                AreaPrestamos.appendText(info + "\n------------------\n");
            }
        } else {
            AreaPrestamos.appendText("No hay préstamos activos.\n");
        }
    }

    public void VolverPrestamos(ActionEvent actionEvent) throws IOException {
        new CargarInterfaz(PrestamoInterfaz, "/org/example/bibliotecafx/InicioInterfaz.fxml");
    }

    public void LimpiarPrestamos(ActionEvent actionEvent) {
        AreaPrestamos.clear();
        SocioId.clear();
        LibroIsbn.clear();
        FechaPrestamo.setValue(null);
        FechaDevolucion.setValue(null);
    }

    public void ListarHistorialSocio(ActionEvent actionEvent) {
        String socioIdStr = SocioId.getText();  // Obtener el ID del socio
        if (!socioIdStr.isEmpty()) {
            try {
                Integer idSocio = Integer.parseInt(socioIdStr);  // Convertir el ID del socio a entero
                List<Prestamo> historialPrestamos = prestamoDAO.obtenerHistorialPrestamosPorSocio(idSocio);

                // Limpiar el área de texto antes de mostrar los resultados
                AreaPrestamos.clear();

                if (historialPrestamos != null && !historialPrestamos.isEmpty()) {
                    // Mostrar los préstamos del historial del socio
                    for (Prestamo prestamo : historialPrestamos) {
                        String info = "ID Prestamo: " + prestamo.getId() + "\n" +
                                "Libro: " + prestamo.getLibro().getTitulo() + "\n" +
                                "Fecha de Préstamo: " + prestamo.getFechaPrestamo() + "\n" +
                                "Fecha de Devolución: " + prestamo.getFechaDevolucion() + "\n";
                        AreaPrestamos.appendText(info + "\n------------------\n");
                    }
                } else {
                    AreaPrestamos.appendText("No hay préstamos para este socio.\n");
                }
            } catch (NumberFormatException e) {
                AreaPrestamos.appendText("Por favor, ingrese un ID de socio válido.\n");
            }
        } else {
            AreaPrestamos.appendText("El campo de ID de socio no puede estar vacío.\n");
        }
    }
}




