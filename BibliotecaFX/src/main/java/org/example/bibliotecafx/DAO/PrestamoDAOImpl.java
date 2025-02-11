package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamoDAOImpl implements IPrestamoDAO {

    public PrestamoDAOImpl() {}
    @Override
    public void registrarPrestamo(Prestamo prestamo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> listarPrestamosActivos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamo p WHERE p.fechaDevolucion IS NULL", Prestamo.class)
                    .getResultList();
        }
    }

    @Override
    public List<Prestamo> obtenerHistorialPrestamosPorSocio(Integer idSocio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Prestamo p WHERE p.socio.id = :idSocio", Prestamo.class)
                    .setParameter("idSocio", idSocio)
                    .getResultList();
        }
    }
}