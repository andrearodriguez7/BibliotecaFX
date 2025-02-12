package org.example.bibliotecafx.Gestion;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Application.launch;

public class Interfaz extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/bibliotecafx/InicioInterfaz.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 800);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        //stage.getIcons().add(new Image(getClass().getResource("/imagenes/FotoLibros.png").toString()));
        stage.setTitle("BibliotecaFX");
        stage.setScene(scene);
        stage.show();
    }

   public static void crearBaseDatos() { // Abrir una sesión de Hibernate
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
    }

    public static void main(String[] args) {
        crearBaseDatos();
        launch();
    }
}
