package com.example.exc_11.dao;

import com.example.exc_11.models.Manufacture;
import com.example.exc_11.models.Worker;
import com.sun.istack.NotNull;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    @Override
    public void delete(@NotNull Long id) {
        factory.openSession().createQuery("DELETE FROM Manufacture WHERE id="+id).list();
    }

    public List<Manufacture> findAll() {
        List<Manufacture> manufactures = (List<Manufacture>) factory.openSession().createQuery("From Manufacture").list();
        List<Manufacture> manufactures1 = new ArrayList<Manufacture>();
        for(int i = 0;i<manufactures.size();i++){
            Manufacture buff = manufactures.get(i);
            buff.setWorkers(getWorkers(buff.getId()));
            manufactures1.add(buff);
        }
        return manufactures1;
    }
    public Manufacture findById(Long id) {
        Manufacture manufactures = (Manufacture) factory.openSession().createQuery("From Manufacture WHERE id="+id).list().get(0);
        return manufactures;
    }
    public List<Worker> getWorkers(int id){
        List<Worker> workers = (List<Worker>) factory.openSession().createQuery("From Worker where manufacture="+id).list();
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


    @Override
    public List<Manufacture> filter(String column) {
        Session session = factory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacture> cq =
                builder.createQuery(Manufacture.class);
        Root<Manufacture> root = cq.from(Manufacture.class);
        cq.select(root).orderBy(builder.asc(root.get(
                column)));
        Query<Manufacture> query = session.createQuery(cq);
        return query.list();
    }

    @Override
    public List<Manufacture> filter(String column, String pattern) {
        Session session = factory.openSession();
        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery<Manufacture> userCriteriaQuery = criteria.createQuery(Manufacture.class);
        Root<Manufacture> userRoot = userCriteriaQuery.from(Manufacture.class);
        userCriteriaQuery.select(userRoot).where(criteria.equal(userRoot.get(column), pattern));
        Query query = session.createQuery(userCriteriaQuery);
        return query.list();
    }
}
