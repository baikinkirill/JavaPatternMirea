package com.example.exc_11.dao;

import com.example.exc_11.models.Manufacture;
import com.sun.istack.NotNull;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
public class ManufactureDAO implements DAO<Manufacture, String> {

    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public ManufactureDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Create new manufacture in manufactures table.
     *
     * @param manufacture for add.
     */
    @Override
    public void create(@NotNull final Manufacture manufacture) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(manufacture);

            session.getTransaction().commit();
        }
    }

    /**
     * Get manufacture by model.
     *
     * @param model for select.
     * @return manufacture with param model.
     */
    @Override
    public Manufacture read(@NotNull final String model) {
        try (final Session session = factory.openSession()) {

            final Manufacture result = session.get(Manufacture.class, model);

            return result != null ? result : new Manufacture();
        }
    }

    /**
     * Update manufacture state.
     *
     * @param manufacture new state.
     */
    @Override
    public void update(@NotNull final Manufacture manufacture) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(manufacture);

            session.getTransaction().commit();
        }
    }

    /**
     * Delete manufacture.
     *
     * @param manufacture for delete.
     */
    @Override
    public void delete(@NotNull final Manufacture manufacture) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.delete(manufacture);

            session.getTransaction().commit();
        }
    }

    public List<Manufacture> findAll() {
        List<Manufacture> workers = (List<Manufacture>) factory.openSession().createQuery("From Manufacture").list();
        return workers;
    }

    public int getNextId() {
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT MAX(id) FROM Manufacture ");
        System.out.println(query.list());
        if(query.list().get(0)==null){
            return 0;
        }else{
            return (int) query.list().get(0)+1;
        }
    }
}
