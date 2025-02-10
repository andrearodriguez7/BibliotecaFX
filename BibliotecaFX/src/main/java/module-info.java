module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires java.naming;
    requires org.hibernate.orm.core;

    opens org.example.bibliotecafx to javafx.fxml;
    opens org.example.bibliotecafx.entities to org.hibernate.orm.core;
    opens org.example.bibliotecafx.Gestion to javafx.fxml;

    exports org.example.bibliotecafx.Gestion to javafx.graphics;


}