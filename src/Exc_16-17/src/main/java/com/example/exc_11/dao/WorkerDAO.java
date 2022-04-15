package com.example.exc_11.dao;

import com.example.exc_11.models.Worker;
import com.sun.istack.NotNull;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 26/11/2017.
 */
public class WorkerDAO implements DAO<Worker, String> {

    /**
     * Connection factory to database.
     */
    private final SessionFactory factory;

    public WorkerDAO(@NotNull final SessionFactory factory) {
        this.factory = factory;
    }

    /**
     * Create new worker in workers table.
     *
     * @param worker for add.
     */
    @Override
    public void create(@NotNull final Worker worker) {
        try (final Session session = factory.openSession()) {

            session.beginTransaction();

            session.save(worker);

            session.getTransaction().commit();
        }
    }

    /**
     * Get worker by model.
     *
     * @param model for select.
     * @return worker with param model.
     */
    @Override
    public Worker read(@NotNull final String model) {
        try (final Session session = factory.openSession()) {

            final Worker result = session.get(Worker.class, model);

            return result != null ? result : new Worker();
        }
    }

    /**
     * Update worker state.
     *
     * @param worker new state.
     */
    @Override
    public void update(@NotNull final Worker worker) {
        try (Session session = factory.openSession()) {

            session.beginTransaction();

            session.update(worker);

            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Long id) {
        System.out.println(id);
        Session session = factory.openSession();

        session.beginTransaction();

        Worker w = new Worker();
        w.setId(Math.toIntExact(id));
        session.delete(w);

        session.getTransaction().commit();
    }

    public List<Worker> findAll() {
        List<Worker> workers = (List<Worker>) factory.openSession().createQuery("From Worker").list();
        return workers;
    }

    public Long getManufacture(Long id) {
        Long mn = (Long) factory.openSession().createQuery("SELECT manufacture From Worker WHERE id=" + id).list().get(0);
        return mn;
    }

    public int getNextId() {
        Session session = factory.openSession();

        Query query = session.createQuery("SELECT MAX(id) FROM Worker ");
        if (query.list().get(0) == null) {
            return 0;
        } else {
            return (int) query.list().get(0) + 1;
        }
    }

    @Override
    public List<Worker> filter(String column) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Worker> cq =
                builder.createQuery(Worker.class);
        Root<Worker> root = cq.from(Worker.class);
        cq.select(root).orderBy(builder.asc(root.get(
                column)));
        Query<Worker> query = session.createQuery(cq);
        return query.list();
    }

    @Override
    public List<Worker> filter(String column, String pattern) {
        Session session = factory.openSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Worker> userCriteriaQuery = criteria.createQuery(Worker.class);
        Root<Worker> userRoot = userCriteriaQuery.from(Worker.class);
        userCriteriaQuery.select(userRoot).where(criteria.equal(userRoot.get(column), pattern));
        Query query = session.createQuery(userCriteriaQuery);
        return query.list();
    }

}
