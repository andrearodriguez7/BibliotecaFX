package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class LibroDAOImpl implements ILibroDAO {

    public LibroDAOImpl() {
    }

    @Override
    public void agregarLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("📌 Intentando guardar libro: " + libro);
            session.persist(libro);
            transaction.commit();
            System.out.println("✅ Libro guardado correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("❌ Error al guardar el libro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Libro obtenerLibro(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Buscando el libro por ISBN
            String hql = "FROM Libro l WHERE l.isbn = :isbn";
            return session.createQuery(hql, Libro.class)
                    .setParameter("isbn", isbn)
                    .uniqueResult(); // Devuelve un único resultado o null si no lo encuentra
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void actualizarLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Usamos session.update() para actualizar el libro
            session.update(libro);  // Si el libro ya existe, se actualizará

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(String isbn) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Consulta para obtener los libros con el mismo ISBN
            String hql = "FROM Libro l WHERE l.isbn = :isbn";
            List<Libro> libros = session.createQuery(hql, Libro.class)
                    .setParameter("isbn", isbn)
                    .getResultList();

            if (!libros.isEmpty()) {
                for (Libro libro : libros) {
                    System.out.println("Eliminando libro: " + libro.getTitulo()); // Depuración
                    session.delete(libro);
                }
                transaction.commit();  // Commit de la transacción
                System.out.println("Libros eliminados correctamente.");
            } else {
                System.out.println("No se encontró el libro con ISBN: " + isbn);
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Se obtiene la fecha actual
            LocalDate fechaHoy = LocalDate.now();

            // Consulta HQL con FETCH JOIN para cargar las relaciones de Autor
            String hql = "FROM Libro l LEFT JOIN FETCH l.autor WHERE l NOT IN (SELECT p.libro FROM Prestamo p WHERE p.fechaDevolucion > :fechaHoy)";

            // Se realiza la consulta con el parámetro de fecha actual
            return session.createQuery(hql, Libro.class)
                    .setParameter("fechaHoy", fechaHoy)
                    .getResultList();
        }
    }

    @Override
    public List<Libro> obtenerLibrosPorAutor(String nombreAutor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener libros basados en el nombre del autor
            String hql = "FROM Libro l JOIN l.autor a WHERE a.nombre = :nombreAutor";

            // Ejecutamos la consulta y obtenemos los resultados
            return session.createQuery(hql, Libro.class)
                    .setParameter("nombreAutor", nombreAutor)
                    .getResultList();  // Devuelve una lista de libros
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // En caso de error, devolvemos null
        }
    }

    @Override
    public List<Libro> obtenerLibrosPorTitulo(String tituloLibro) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Consulta HQL para obtener libros basados en el título
            String hql = "FROM Libro l WHERE l.titulo LIKE :tituloLibro";

            // Ejecutamos la consulta y obtenemos los resultados
            return session.createQuery(hql, Libro.class)
                    .setParameter("tituloLibro", "%" + tituloLibro + "%")  // Se usa LIKE para búsquedas parciales
                    .getResultList();  // Devuelve una lista de libros
        } catch (Exception e) {
            e.printStackTrace();
            return null;  // En caso de error, devolvemos null
        }
    }

}