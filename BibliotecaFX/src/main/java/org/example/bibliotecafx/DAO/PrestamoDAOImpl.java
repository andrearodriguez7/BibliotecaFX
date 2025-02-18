package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class PrestamoDAOImpl implements IPrestamoDAO {

    public PrestamoDAOImpl() {}
    @Override
    public void registrarPrestamo(Prestamo prestamo) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(prestamo);  // Guarda el préstamo en la base de datos
            transaction.commit();
            System.out.println("✅ Préstamo registrado correctamente.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.out.println("❌ Error al registrar el préstamo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Prestamo> listarPrestamosActivos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Obtenemos la fecha actual
            LocalDate fechaHoy = LocalDate.now();

            // Buscamos los préstamos donde la fecha de devolución es posterior a la fecha actual o es null
            String hql = "FROM Prestamo p WHERE (p.fechaDevolucion IS NULL OR p.fechaDevolucion > :fechaHoy)";
            return session.createQuery(hql, Prestamo.class)
                    .setParameter("fechaHoy", fechaHoy)
                    .getResultList();  // Retorna una lista de préstamos activos
        }
    }

    @Override
    public List<Prestamo> obtenerHistorialPrestamosPorSocio(Integer idSocio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Verificar si el idSocio se pasa correctamente
            System.out.println("Buscando préstamos para el socio con ID: " + idSocio);

            // Consulta para obtener el historial de préstamos de un socio específico
            String hql = "FROM Prestamo p WHERE p.socio.id = :idSocio";
            return session.createQuery(hql, Prestamo.class)
                    .setParameter("idSocio", idSocio)
                    .getResultList();  // Retorna la lista de préstamos de ese socio
        }
    }
}