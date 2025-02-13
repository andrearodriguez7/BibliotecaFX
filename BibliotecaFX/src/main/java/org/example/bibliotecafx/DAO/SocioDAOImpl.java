package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Socio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl implements ISocioDAO {

    public SocioDAOImpl() {}
    @Override
    public void agregarSocio(Socio socio) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(socio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Socio obtenerSocio(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Socio.class, id);
        }
    }

    @Override
    public List<Socio> buscarPorParametro(String parametro) {
        Transaction transaction = null;
        List<Socio> sociosEncontrados = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Elimina caracteres no alfanuméricos del teléfono si es necesario
            String parametroProcesado = parametro.replaceAll("[^a-zA-Z0-9]", "").trim();  // Elimina todo excepto letras y números

            // Log para depuración
            System.out.println("Buscando con el parámetro procesado: " + parametroProcesado);

            // Si el parámetro parece un número de teléfono (solo contiene dígitos), usa comparación exacta
            boolean esTelefono = parametroProcesado.matches("\\d+");

            String hql;
            Query<Socio> query;

            if (esTelefono) {
                // Búsqueda exacta para el teléfono
                hql = "FROM Socio s WHERE LOWER(s.telefono) = LOWER(:parametro)";
                query = session.createQuery(hql, Socio.class);
                query.setParameter("parametro", parametroProcesado);  // Parámetro sin '%' para exactitud
            } else {
                // Búsqueda parcial para el nombre
                hql = "FROM Socio s WHERE LOWER(s.nombre) LIKE LOWER(:parametro)";
                query = session.createQuery(hql, Socio.class);
                query.setParameter("parametro", "%" + parametroProcesado + "%");  // Parámetro con '%' para búsqueda parcial
            }

            sociosEncontrados = query.list();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("\nError al buscar socios por parámetro: " + e.getMessage());
        }
        return sociosEncontrados;
    }

    @Override
    public void actualizarSocio(Socio socio) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(socio);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarSocio(Integer id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Socio socio = session.get(Socio.class, id);
            if (socio != null) {
                session.remove(socio);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<Socio> listarTodosLosSocios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socio", Socio.class).getResultList();
        }
    }

}
