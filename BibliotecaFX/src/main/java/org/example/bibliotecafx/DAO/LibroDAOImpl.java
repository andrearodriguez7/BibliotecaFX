package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAOImpl implements ILibroDAO {

    public LibroDAOImpl() {}
    @Override
    public void agregarLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Libro obtenerLibro(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Libro.class, isbn);
        }
    }

    @Override
    public void actualizarLibro(Libro libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(libro);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarLibro(String isbn) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, isbn);
            if (libro != null) {
                session.remove(libro);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Libro> listarLibrosDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Libro l WHERE l NOT IN (SELECT p.libro FROM Prestamo p WHERE p.fechaDevolucion IS NULL)", Libro.class)
                    .getResultList();
        }
    }
}